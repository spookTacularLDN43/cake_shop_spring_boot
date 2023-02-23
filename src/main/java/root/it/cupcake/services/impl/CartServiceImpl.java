package root.it.cupcake.services.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.it.cupcake.dao.ICakeDAO;
import root.it.cupcake.model.Cake;
import root.it.cupcake.services.ICartService;
import root.it.cupcake.session.SessionObject;

@Service
public class CartServiceImpl implements ICartService {
    @Resource
    SessionObject sessionObject;

    @Autowired
    ICakeDAO cakeDAO;

    @Override
    public void addToBasket(int cakeId) {
        for (Cake cake : this.sessionObject.getCart()) {
            if (cake.getId() == cakeId) {
                cake.setPieces(cake.getPieces() + 1);
                return;
            }
        }

        Cake cake = this.cakeDAO.getCakeById(cakeId);
        cake.setPieces(1);
        this.sessionObject.getCart().add(cake);
    }

    @Override
    public double calculateBill() {
        double bill = 0;
        for (Cake cake : this.sessionObject.getCart()) {
            bill = bill + cake.getPrice() * cake.getPieces();
        }
        return bill;
    }
}
