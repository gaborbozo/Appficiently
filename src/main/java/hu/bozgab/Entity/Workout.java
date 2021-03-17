package hu.bozgab.Entity;

import javax.persistence.*;

@Entity
public class Workout {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne
    private WorkoutInformation workoutInformation;

    @ManyToOne
    private Exercise exercise;

    @Column(name = "count")
    private int count;

    @Column(name = "comment")
    private String comment;

    public Workout() {
        this.count = 1;
    }

    public Workout(Exercise exercise) {
        this.count = 1;
        this.exercise = exercise;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) { this.id = id; }

    public WorkoutInformation getWorkoutInformation() { return this.workoutInformation; }

    public void setWorkoutInformation(WorkoutInformation workoutInformation) { this.workoutInformation = workoutInformation; }

    public Exercise getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise) { this.exercise = exercise; }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) { this.count = count; }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) { this.comment = comment; }
}
