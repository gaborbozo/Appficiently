package hu.bozgab.Service.Interface;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.User;
import hu.bozgab.Entity.Workout;

import java.util.List;

public interface IWorkoutService {

    void saveExercise(Exercise exercise);

    List<Exercise> getAllExercises();

    void saveWorkout(Workout workout);

    User getCurrentUser(long id);

    Long getMaxWorkoutId();

    List<Workout> getAllWorkouts(long id);
}
