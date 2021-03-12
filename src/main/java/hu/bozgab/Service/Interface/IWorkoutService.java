package hu.bozgab.Service.Interface;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.User;
import hu.bozgab.Entity.WorkoutInformation;

import java.util.List;

public interface IWorkoutService {

    void saveExercise(Exercise exercise);

    List<Exercise> getAllExercises();

    User getCurrentUser(long id);

    WorkoutInformation findWorkoutInformationById(long id);

    void insertWorkout(WorkoutInformation workoutInformation);
}
