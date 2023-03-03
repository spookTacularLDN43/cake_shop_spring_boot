package root.it.cupcake.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.it.cupcake.dao.IUserDAO;
import root.it.cupcake.model.User;
import root.it.cupcake.model.view.ChangePassData;
import root.it.cupcake.model.view.UserRegistrationData;
import root.it.cupcake.services.IUserService;
import root.it.cupcake.session.SessionObject;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionObject sessionObject;

    @Override
    public boolean registerUser(UserRegistrationData userRegistrationData) {
        User userFromDatabase = this.userDAO.getUserByLogin(userRegistrationData.getLogin());
        if (userFromDatabase != null) {
            return false;
        }
        User user = new User();
        user.setName(userRegistrationData.getName());
        user.setSurname(userRegistrationData.getSurname());
        user.setLogin(userRegistrationData.getLogin());
        user.setPassword(DigestUtils.md5Hex(userRegistrationData.getPassword()));
        this.userDAO.persistUser(user);
        return true;
    }

    @Override
    public User authenticate(User user) {
        User userFormDatabase = this.userDAO.getUserByLogin(user.getLogin());
        if (userFormDatabase != null && userFormDatabase.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            return userFormDatabase;
        }
        return null;
    }

    @Override
    public User updateUserData(User user) {
        User currentUser = this.sessionObject.getUser();
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        this.userDAO.updateUser(currentUser);
        return currentUser;
    }

    @Override
    public User updateUserPass(ChangePassData changePassData) {
        User user = new User();
        user.setLogin(this.sessionObject.getUser().getLogin());
        user.setPassword(changePassData.getPass());
        User authUser = this.authenticate(user);
        if (authUser == null) {
            return null;
        }
        authUser.setPassword(changePassData.getNewPass());

        this.userDAO.updateUser(authUser);

        return authUser;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public void persistUser(User user) {
        this.userDAO.persistUser(user);
    }

    @Override
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

}
