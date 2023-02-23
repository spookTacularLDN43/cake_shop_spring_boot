package root.it.cupcake.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import root.it.cupcake.dao.ICakeDAO;
import root.it.cupcake.model.Cake;

import java.util.List;

@Repository
public class CakeDAOImpl implements ICakeDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Cake> getAllCakes() {

        return null;
    }

    @Override
    public Cake getCakeById(int id) {
        return null;
    }

    @Override
    public Cake getCakeByName(String name) {
        Session session = this.sessionFactory.openSession();
        Query<Cake> query = session.createQuery("FROM root.it.cupcake.model.Cake WHERE name= :name");
        query.setParameter("name", name);
        Cake cake = query.getSingleResult();
        session.close();
        return cake;
    }

    @Override
    public void updateCake(Cake cake) {

    }

    @Override
    public void persistCake(Cake cake) {

    }
}
