package root.it.cupcake.dao.impl;

import org.springframework.stereotype.Repository;
import root.it.cupcake.dao.IUserDAO;
import root.it.cupcake.model.User;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public void persistUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
