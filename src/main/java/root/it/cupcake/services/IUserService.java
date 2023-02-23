package root.it.cupcake.services;

import root.it.cupcake.model.User;
import root.it.cupcake.model.view.ChangePassData;
import root.it.cupcake.model.view.UserRegistrationData;

public interface IUserService {
    boolean registerUser(UserRegistrationData userRegistrationData);

    User authenticate(User user);

    User updateUserData(User user);

    User updateUserPass(ChangePassData changePassData);
}
