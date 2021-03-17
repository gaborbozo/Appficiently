package hu.bozgab.Repository;

import hu.bozgab.Entity.Workout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout,Long> {

    void deleteAllById(Long id);

}
