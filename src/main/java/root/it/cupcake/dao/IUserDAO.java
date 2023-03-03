package root.it.cupcake.dao;

import root.it.cupcake.model.User;

import java.util.List;

public interface IUserDAO {
    User getUserByLogin(String login);

    void persistUser(User user);

    void updateUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);
}
