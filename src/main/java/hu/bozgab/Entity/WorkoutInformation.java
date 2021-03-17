package hu.bozgab.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class WorkoutInformation {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "modified")
    private Date modified;

    @OneToMany(mappedBy = "workoutInformation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Workout> workouts = new ArrayList<>();

    public WorkoutInformation() {}

    public WorkoutInformation(User user, String name, Date modified) {
        this.user  = user;
        this.name = name;
        this.modified = modified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) { this.user = user; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getModified() { return modified; }

    public void setModified(Date modified) { this.modified = modified; }

    public List<Workout> getWorkouts() { return workouts; }

    public void setWorkouts(List<Workout> workouts) { this.workouts = workouts; }
}
