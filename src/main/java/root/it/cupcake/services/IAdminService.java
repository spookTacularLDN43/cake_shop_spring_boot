package root.it.cupcake.services;

import root.it.cupcake.model.Cake;

public interface IAdminService {

    void addCake (Cake cake);
    void updateCake (Cake cake);
    void deleteCake(Cake cake);
}
