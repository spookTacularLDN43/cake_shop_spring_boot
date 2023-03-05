package root.it.cupcake.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import root.it.cupcake.dao.IOrderDAO;
import root.it.cupcake.model.Order;

import java.util.List;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM root.it.cupcake.model.Order WHERE user_id= :user_id");
        query.setParameter("user_id", userId);
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM root.it.cupcake.model.Order");
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }
}
