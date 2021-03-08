package hu.bozgab.Entity;

import javax.persistence.*;

@Entity
public class Workout {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "workout_id")
    private Long workout_id;

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

    public Workout(String comment) {this.comment = comment; }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) { this.id = id; }

    public Long getWorkout_id() {
        return this.workout_id;
    }

    public void setWorkout_id(Long workout_id) { this.workout_id = workout_id; }

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
