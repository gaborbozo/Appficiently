package hu.bozgab.Service;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.User;
import hu.bozgab.Entity.Workout;
import hu.bozgab.Repository.ExerciseRepository;
import hu.bozgab.Repository.UserRepository;
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

    private UserRepository userRepository;

    @Autowired
    WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, UserRepository userRepository){
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    @Override
    public User getCurrentUser(long id) { return userRepository.findById(id); }

    @Override
    public Long getMaxWorkoutId() { return workoutRepository.findMaxWorkoutId(); }

    @Override
    public List<Workout> getAllWorkouts(long id) { return workoutRepository.findAllByUser_id(id); }
}
