package dao;

import model.Pasajeros;

import java.util.List;

public interface PasajerosDAO {
    void crearPasajero(Pasajeros pasajero);
    Pasajeros consultaPasajeroId(int id);
    List<Pasajeros> listadoPasajeros();
    void update(Pasajeros pasajero);
    void delete(int id);
    void asignarAsiento(int pasajeroId, int cocheId);
    void eliminarAsiento(int pasajeroId);
}
