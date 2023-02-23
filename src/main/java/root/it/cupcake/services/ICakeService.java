package root.it.cupcake.services;

import root.it.cupcake.model.Cake;

import java.util.List;

public interface ICakeService {
    List<Cake> getAllCakes();

    List<Cake> getFilteredCakes(String filter);
}
