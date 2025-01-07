package dao;

import model.Coche;

import java.util.List;

public interface CocheDAO {

    void creaCoche(Coche coche);
    Coche buscarCocheId(int id);
    List<Coche> listadoCoches();
    void updateCoche(Coche coche);
    void deleteCoche(int id);

}
