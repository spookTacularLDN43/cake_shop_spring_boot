package root.it.cupcake.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import root.it.cupcake.dao.ICakeDAO;
import root.it.cupcake.model.Cake;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CakeDAOImpl implements ICakeDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Cake> getAllCakes() {
        Session session = this.sessionFactory.openSession();
        Query<Cake> query = session.createQuery("FROM root.it.cupcake.model.Cake");
        List<Cake> cakeList = query.getResultList();
        session.close();
        return cakeList;
    }

    @Override
    public Cake getCakeById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Cake> query = session.createQuery("FROM root.it.cupcake.model.Cake WHERE id= :id");
        query.setParameter("id", id);
        Cake cake = null;
        try {
            cake = query.getSingleResult();
        } catch (NoResultException e){
            System.out.println("Cake not found");
        }
        session.close();
        return cake;
    }

    @Override
    public void addCake(Cake cake) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(cake);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

//    @Override
//    public Cake getCakeByName(String name) {
//        Session session = this.sessionFactory.openSession();
//        Query<Cake> query = session.createQuery("FROM root.it.cupcake.model.Cake WHERE name= :name");
//        query.setParameter("name", name);
//        Cake cake = query.getSingleResult();
//        session.close();
//        return cake;
//    }

}
