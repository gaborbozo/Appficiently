package hu.bozgab.Service;

import hu.bozgab.Entity.Exercise;
import hu.bozgab.Entity.User;
import hu.bozgab.Entity.Workout;
import hu.bozgab.Entity.WorkoutInformation;
import hu.bozgab.Repository.ExerciseRepository;
import hu.bozgab.Repository.UserRepository;
import hu.bozgab.Repository.WorkoutRepository;
import hu.bozgab.Repository.WorkoutInformationRepository;
import hu.bozgab.Service.Interface.IWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService implements IWorkoutService {

    private WorkoutRepository workoutRepository;

    private WorkoutInformationRepository workoutInformationRepository;

    private ExerciseRepository exerciseRepository;

    private UserRepository userRepository;

    @Autowired
    WorkoutService(WorkoutRepository workoutRepository, WorkoutInformationRepository workoutInformationRepository, ExerciseRepository exerciseRepository, UserRepository userRepository){
        this.workoutRepository = workoutRepository;
        this.workoutInformationRepository = workoutInformationRepository;
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
    public User getCurrentUser(long id) { return userRepository.findById(id); }

    @Override
    public WorkoutInformation findWorkoutInformationById(long id) { return workoutInformationRepository.findById(id); }

    @Override
    public void insertWorkout(WorkoutInformation workoutInformation) {
        List<Workout> workouts = workoutInformation.getWorkouts();

        workoutInformationRepository.save(workoutInformation);

        for(Workout item: workouts) {
            item.setWorkoutInformation(workoutInformation);
            workoutRepository.save(item);
        }
    }
}
