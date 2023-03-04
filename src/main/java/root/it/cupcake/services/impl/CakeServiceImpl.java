package root.it.cupcake.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.it.cupcake.dao.ICakeDAO;
import root.it.cupcake.model.Cake;
import root.it.cupcake.services.ICakeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CakeServiceImpl implements ICakeService {

    @Autowired
    ICakeDAO cakeDAO;

    @Override
    public List<Cake> getAllCakes() {
        return this.cakeDAO.getAllCakes();
    }

    @Override
    public List<Cake> getFilteredCakes(String filter) {
        List<Cake> allCakes = this.cakeDAO.getAllCakes();
        List<Cake> filteredCakes = new ArrayList<>();
        for (Cake currentCake : allCakes) {
            if (currentCake.getName().toLowerCase().contains(filter.toLowerCase())) {
                filteredCakes.add(currentCake);
            }
        }
        return filteredCakes;
    }

    @Override
    public Cake getCakeById(int id) {
        return this.cakeDAO.getCakeById(id);
    }
}
