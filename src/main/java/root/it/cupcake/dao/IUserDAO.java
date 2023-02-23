package root.it.cupcake.dao;

import root.it.cupcake.model.User;

public interface IUserDAO {
    User getUserByLogin (String login);
    void persistUser(User user);
    void updateUser(User user);
}
