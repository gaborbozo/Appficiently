package hu.bozgab.Service;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Repository.ExerciseRepository;
import hu.bozgab.Repository.WorkoutRepository;
import hu.bozgab.Service.Interface.IWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WorkoutService implements IWorkoutService {

    private WorkoutRepository workoutRepository;

    private ExerciseRepository exerciseRepository;

    @Autowired
    WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository){
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public void saveExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }


}
