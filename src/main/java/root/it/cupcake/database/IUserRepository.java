package root.it.cupcake.database;

import root.it.cupcake.model.User;

public interface IUserRepository {
    User authenticate(User user);

    User updateUserData(User user);

    User updateUserPass(User user);

    boolean checkIfLoginExists(String login);

    void addUser(User user);
}
