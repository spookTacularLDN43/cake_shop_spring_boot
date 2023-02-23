package root.it.cupcake.dao;

import root.it.cupcake.model.Cake;

import java.util.List;

public interface ICakeDAO {
    List<Cake> getAllCakes();

    Cake getCakeById(int id);

    void addCake(Cake cake);

    //Cake getCakeByName(String name);

}
