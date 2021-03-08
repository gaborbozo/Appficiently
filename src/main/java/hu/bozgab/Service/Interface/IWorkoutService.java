package hu.bozgab.Service.Interface;

import hu.bozgab.Entity.Exercise;

import java.util.List;

public interface IWorkoutService {

    void saveExercise(Exercise exercise);

    List<Exercise> getAllExercises();
}
