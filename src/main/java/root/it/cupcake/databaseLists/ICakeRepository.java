package root.it.cupcake.databaseLists;

import root.it.cupcake.model.Cake;

import java.util.List;

public interface ICakeRepository {
    List<Cake> getAllCakes();
    List<Cake> getFilteredCakes(String filter);
    Cake getCakeByName(String name);
}
