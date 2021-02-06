package hu.bozgab.Service.Interface;

import hu.bozgab.Entity.User;

public interface UserIdentify {
    User findByEmail(String email);

    User findByName(String name);
}
