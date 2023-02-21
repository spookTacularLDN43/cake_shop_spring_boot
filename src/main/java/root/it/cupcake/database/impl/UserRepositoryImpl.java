package root.it.cupcake.database.impl;

import org.springframework.stereotype.Component;
import root.it.cupcake.database.IUserRepository;
import root.it.cupcake.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements IUserRepository {

    private final List<User> userList = new ArrayList<>();

    public UserRepositoryImpl() {
        this.userList.add(new User("Maja", "Laskowska", "admin", "admin"));
    }

    @Override
    public User authenticate(User user) {
        for (User userFromDB : this.userList) {
            if (userFromDB.getLogin().equals(user.getLogin())) {
                if (userFromDB.getPassword().equals(user.getPassword())) {
                    return userFromDB;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public User updateUserData(User user) {
        for (User userFromDB : this.userList) {
            if (userFromDB.getLogin().equals(user.getLogin())) {
                userFromDB.setName(user.getName());
                userFromDB.setSurname(user.getSurname());
                return userFromDB;
            }
        }
        return null;
    }

    @Override
    public User updateUserPass(User user) {
        for (User userFromDB:this.userList) {
            if(userFromDB.getLogin().equals(user.getLogin())){
                userFromDB.setPassword(user.getPassword());
                return userFromDB;
            }
        }
        return null;
    }
}
