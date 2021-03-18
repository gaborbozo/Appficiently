package hu.bozgab.Service.Interface;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.User;
import hu.bozgab.Entity.WorkoutInformation;
import hu.bozgab.Service.WorkoutManager;

import java.util.List;

public interface IWorkoutService {

    void saveExercise(Exercise exercise);

    void saveWorkout(WorkoutManager workoutManager, User user);

    WorkoutInformation findWorkoutInformationById(long id);

    List<Exercise> getExercises();

    List<WorkoutInformation> getWorkouts(User user);
}
