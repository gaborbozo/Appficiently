package hu.bozgab.Repository;

import hu.bozgab.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();

    User findByEmail(String email);

    User findByUsername(String username);

    User findById(long id);
}

