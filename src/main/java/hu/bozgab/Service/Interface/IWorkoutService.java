package hu.bozgab.Service.Interface;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.User;
import hu.bozgab.Entity.WorkoutInformation;
import hu.bozgab.Service.WorkoutManager;

import java.util.List;

public interface IWorkoutService {

    void saveExercise(Exercise exercise);

    User getCurrentUser(User user);

    WorkoutInformation findWorkoutInformationById(long id);

    void saveWorkout(WorkoutManager workoutManager, User user);

    List<Exercise> getExercises();
}
