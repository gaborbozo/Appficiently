package hu.bozgab.Service;

import hu.bozgab.Entity.Workout;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WorkoutManager {

    private List<Workout> workouts;

    private String workoutName;

    private Long workoutId;

    public WorkoutManager(){
        this.workouts = new ArrayList<>();
        workoutName = "Új Edzés";
        workoutId = null;
    }

    public List<Workout> getWorkouts() { return this.workouts; }

    public void setWorkouts(List<Workout> workouts) { this.workouts = workouts; }

    public void addWorkout(Workout workout, int id) { workouts.add(id , workout); }

    public void saveWorkout(Workout workout, int id) { workouts.set(id, workout); }

    public void removeWorkout(int id) { workouts.remove(id); }

    public String getWorkoutName() { return this.workoutName; }

    public void setWorkoutName(String workoutName) { this.workoutName = workoutName; }

    public Long getWorkoutId() { return this.workoutId; }

    public void setWorkoutId(Long workoutId) { this.workoutId = workoutId; }
}
