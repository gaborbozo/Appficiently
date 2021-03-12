package hu.bozgab.Repository;

import hu.bozgab.Entity.Workout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout,Long> {

    @Query("SELECT max(item.workout_id) FROM Workout item")
    Long findMaxWorkoutId();

    List<Workout> findAllByUser_id(long id);

}
