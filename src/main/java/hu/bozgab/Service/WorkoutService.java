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

import java.util.Date;
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
    public WorkoutInformation findWorkoutInformationById(long id) { return workoutInformationRepository.findById(id); }

    @Override
    public List<Exercise> getExercises() { return exerciseRepository.findAll(); }

    @Override
    public List<WorkoutInformation> getWorkouts(User user) {
        return workoutInformationRepository.findAllByUserId(user.getId());
    }

    @Override
    public void saveWorkout(WorkoutManager workoutManager, User user) {

        if (workoutManager.getWorkoutId() != null) {
            workoutRepository.deleteAll(findWorkoutInformationById(workoutManager.getWorkoutId()).getWorkouts());
            workoutInformationRepository.deleteById(workoutManager.getWorkoutId());
        }

        WorkoutInformation workoutInformation = new WorkoutInformation(user, workoutManager.getWorkoutName(), new Date());
        workoutInformationRepository.save(workoutInformation);

        for(Workout workout: workoutManager.getWorkouts()) workout.setWorkoutInformation(workoutInformation);

        workoutRepository.saveAll(workoutManager.getWorkouts());
    }

}
