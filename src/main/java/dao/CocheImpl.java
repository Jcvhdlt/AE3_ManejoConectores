package dao;

import database.DataBaseConnection;
import model.Coche;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocheImpl implements CocheDAO {
    private Connection connection = new DataBaseConnection().getConnection();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;




    @Override
    public void creaCoche(Coche coche) {
        String sentenciaCreate="INSERT INTO coche (modelo,plazas) VALUES (?, ?)";
        try  {
            preparedStatement = connection.prepareStatement(sentenciaCreate, Statement.RETURN_GENERATED_KEYS);
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
            preparedStatement = connection.prepareStatement(sentenciaBusqueda);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
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
             resultSet = statement.executeQuery(sentenciaList);
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
            preparedStatement = connection.prepareStatement(sentenciaUpdate);
            preparedStatement.setString(1, coche.getModelo());
            preparedStatement.setInt(2, coche.getPlazas());
            preparedStatement.setInt(3, coche.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCoche(int id) {
        String sentenciaDelete = "DELETE FROM coche WHERE id = ?";
        try  {
            preparedStatement = connection.prepareStatement(sentenciaDelete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
