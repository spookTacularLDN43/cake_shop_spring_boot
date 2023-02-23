package root.it.cupcake.dao;

import root.it.cupcake.model.Cake;

import java.util.List;

public interface ICakeDAO {
    List<Cake> getAllCakes();

    Cake getCakeById(int id);

    Cake getCakeByName(String name);

    void updateCake(Cake cake);

    void persistCake(Cake cake);
}
