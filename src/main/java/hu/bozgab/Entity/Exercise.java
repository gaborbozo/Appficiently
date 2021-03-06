package hu.bozgab.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Exercise {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "exercise")
    private String exercise;

    @OneToMany(mappedBy = "exercise")
    List<Workout> workouts;


    public Exercise(){}

    public Exercise(String exercise) {this.exercise = exercise;}

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public String getExercise() { return this.exercise; }

    public void setExercise(String exercise) { this.exercise = exercise; }

    public List<Workout> getWorkouts(){ return this.workouts; }

    public void setWorkouts(List<Workout> workouts){ this.workouts = workouts; }
}
