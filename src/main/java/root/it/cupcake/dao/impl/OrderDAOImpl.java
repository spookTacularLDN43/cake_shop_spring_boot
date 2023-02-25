package root.it.cupcake.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import root.it.cupcake.dao.IOrderDAO;
import root.it.cupcake.model.Order;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        }catch (Exception e){
            if(transaction !=null)
                transaction.rollback();
        }finally {
            session.close();
        }
    }
}
