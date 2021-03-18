package hu.bozgab.Repository;

import hu.bozgab.Entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();

    User findByEmail(String email);

    User findByUsername(String username);

    User findById(long id);

    @Modifying
    @Query("update User user set user.password = :password where user.id = :userId")
    int modifyPassword(@Param("userId") long userId, @Param("password") String password);
}

