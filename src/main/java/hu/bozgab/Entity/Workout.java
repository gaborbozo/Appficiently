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
    private Exercise exercise;
    //@JoinColumn(name = "id")
    @Column(name = "count")
    private int count;
}
