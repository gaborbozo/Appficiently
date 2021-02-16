package hu.bozgab.Service.Interface;

import hu.bozgab.Entity.User;

public interface UserService {
    public User findByEmail(String email);

    public User findByName(String name);

    public void registerUser(User user);
}
