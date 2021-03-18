package hu.bozgab.Service.Interface;

import hu.bozgab.Entity.User;

public interface IUserService {
    User findByEmail(String email);

    User findByUsername(String name);

    void registerUser(User user);

    Boolean setNewPassword(User user, String password);
}
