package dao;

import database.DataBaseConnection;
import model.Coche;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocheImpl implements dao.CocheDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;




    @Override
    public void creaCoche(Coche coche) {
        String sentenciaCreate="INSERT INTO coche (modelo,plazas) VALUES (?, ?)";
        try  {
            connection = new DataBaseConnection().getConnection();
            preparedStatement = connection.prepareStatement(sentenciaCreate);
            preparedStatement.setString(1, coche.getModelo());
            preparedStatement.setInt(2, coche.getPlazas());
            preparedStatement.executeUpdate();

            try {
                ResultSet autoId = preparedStatement.getGeneratedKeys(); {
                    if (autoId.next()){
                        coche.setId(autoId.getInt(1));
                        System.out.println("Id asignado al vehiculo:" +coche.getId());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Coche buscarCocheId(int id) {
        String sentenciaBusqueda = "SELECT * FROM coche WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sentenciaBusqueda);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Coche(
                        resultSet.getInt("id"),
                        resultSet.getString("modelo"),
                        resultSet.getInt("plazas")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Coche> listadoCoches() {
        List<Coche> coches = new ArrayList<>();
        String sentenciaList = "SELECT * FROM coche";
        try {
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sentenciaList);
            while (resultSet.next()) {
                coches.add(new Coche(
                        resultSet.getInt("id"),
                        resultSet.getString("modelo"),
                        resultSet.getInt("plazas")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coches;
    }

    @Override
    public void updateCoche(Coche coche) {
        String sentenciaUpdate = "UPDATE coche SET modelo = ?, plazas = ? WHERE id = ?";
        try  {
            PreparedStatement statement = connection.prepareStatement(sentenciaUpdate);
            statement.setString(1, coche.getModelo());
            statement.setInt(2, coche.getPlazas());
            statement.setInt(3, coche.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCoche(int id) {
        String sentenciaDelete = "DELETE FROM coche WHERE id = ?";
        try  {
            PreparedStatement statement = connection.prepareStatement(sentenciaDelete);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
