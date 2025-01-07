package dao;

import database.DataBaseConnection;
import model.Pasajeros;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroImpl implements dao.PasajerosDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public void crearPasajero(Pasajeros pasajero) {
        String sentenciaCreate = "INSERT INTO pasajeros (nombre, edad, peso) VALUES (?, ?, ?)";
        try {
            connection = new DataBaseConnection().getConnection();
            preparedStatement = connection.prepareStatement(sentenciaCreate);
            preparedStatement.setString(1,pasajero.getNombre());
            preparedStatement.setInt(2,pasajero.getEdad());
            preparedStatement.setDouble(3,pasajero.getPeso());
            preparedStatement.executeUpdate();

            try {
                resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    pasajero.setId(resultSet.getInt(1));
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pasajeros consultaPasajeroId(int id) {
        String sentenciaConsulta="SELECT * FROM pasajeros WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sentenciaConsulta);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Pasajeros(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("edad"),
                        resultSet.getDouble("peso")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pasajeros> listadoPasajeros() {
        List<Pasajeros> pasajeros = new ArrayList<>();
        String sentenciaListado = "SELECT * FROM pasajeros";
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sentenciaListado);
            while (resultSet.next()) {
                pasajeros.add(new Pasajeros(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("edad"),
                        resultSet.getDouble("peso")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajeros;
    }

    @Override
    public void update(Pasajeros pasajero) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void asignarAsiento(int pasajeroId, int cocheId) {

    }

    @Override
    public void eliminarAsiento(int pasajeroId) {

    }
}
