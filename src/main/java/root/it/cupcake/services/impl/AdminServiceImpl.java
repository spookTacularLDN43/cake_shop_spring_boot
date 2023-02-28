package root.it.cupcake.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.it.cupcake.dao.ICakeDAO;
import root.it.cupcake.model.Cake;
import root.it.cupcake.services.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    ICakeDAO cakeDAO;

    @Override
    public void addCake(Cake cake) {
    this.cakeDAO.updateCake(cake);
    }
}
