package hu.bozgab.Repository;

import hu.bozgab.Entity.Exercise;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableJpaRepositories
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    List<Exercise> findAll();

}
