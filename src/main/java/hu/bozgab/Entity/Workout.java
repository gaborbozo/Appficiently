package hu.bozgab.Entity;

import javax.persistence.*;

@Entity
public class Workout {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "workout_id")
    private long workout_id;

    @ManyToOne
    //@JoinColumn(name="id")
    private User user;

    @ManyToOne
    //@JoinColumn(name = "id")
    private Exercise exercise;

    @Column(name = "count")
    private int count;

    @Column(name = "comment")
    private String comment;

    public Workout() {}

    public Workout(Exercise exercise) {this.exercise = exercise; }

    public long getId() {
        return this.id;
    }

    public void setId(long id) { this.id = id; }

    public long getWorkout_id() { return this.workout_id; }

    public void setWorkout_id(long workout_id) { this.workout_id = workout_id; }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) { this.user = user; }

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
