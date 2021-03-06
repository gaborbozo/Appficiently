package hu.bozgab.Repository;

import hu.bozgab.Entity.Workout;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutRepository extends CrudRepository<Workout,Long> {

}
