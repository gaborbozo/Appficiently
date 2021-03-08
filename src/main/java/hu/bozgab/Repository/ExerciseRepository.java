package hu.bozgab.Repository;

import hu.bozgab.Entity.Exercise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    List<Exercise> findAll();
}
