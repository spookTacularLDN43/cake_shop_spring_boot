package root.it.cupcake.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import root.it.cupcake.dao.IUserDAO;
import root.it.cupcake.model.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM root.it.cupcake.model.User WHERE login= :login");
        query.setParameter("login", login);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("User not found");
        }
        session.close();
        return user;
    }

    @Override
    public void persistUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM root.it.cupcake.model.User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM root.it.cupcake.model.User WHERE id= :id");
        query.setParameter("id", id);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("User with id " + id +" not found");
        }
        session.close();
        return user;
    }
}
