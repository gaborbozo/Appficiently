package hu.bozgab.Repository;

import hu.bozgab.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();



    User findById(long id);
}

