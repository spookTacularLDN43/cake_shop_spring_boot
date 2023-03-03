package root.it.cupcake.services;

import root.it.cupcake.model.User;
import root.it.cupcake.model.view.ChangePassData;
import root.it.cupcake.model.view.UserRegistrationData;

import java.util.List;

public interface IUserService {
    boolean registerUser(UserRegistrationData userRegistrationData);

    User authenticate(User user);

    User updateUserData(User user);

    User updateUserPass(ChangePassData changePassData);

    List<User> getAllUsers();

    User getUserById(int id);

    void persistUser(User user);

    void updateUser(User user);
}
