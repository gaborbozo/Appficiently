package hu.bozgab.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Exercise {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "activity")
    private String activity;

    @OneToMany(mappedBy = "exercise")
    List<Workout> workouts;

    public Exercise(){}

    public Exercise(String activity) {this.activity = activity;}

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public String getActivity() { return this.activity; }

    public void setActivity(String activity) { this.activity = activity; }

    public List<Workout> getWorkouts(){ return this.workouts; }

    public void setWorkouts(List<Workout> workouts){ this.workouts = workouts; }
}
