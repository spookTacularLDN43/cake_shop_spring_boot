package root.it.cupcake.database;

import root.it.cupcake.model.Cake;

import java.util.List;

public interface ICakeRepository {
    List<Cake> getAllCakes();
}
