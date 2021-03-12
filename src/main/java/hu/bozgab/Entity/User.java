package hu.bozgab.Entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique=true, nullable=false )
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "registered")
    private Date registered;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> roles = new HashSet<Role>();

    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;

    public User(){}

    public User(String name, String email, String password){
        this.username = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() { return registered; }

    public void setRegistered(Date registered) { this.registered = registered; }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRoles(String roleName) {
        if (this.roles == null || this.roles.isEmpty()) this.roles = new HashSet<>();
        this.roles.add(new Role(roleName));
    }

    public List<Workout> getWorkouts(){ return this.workouts; }

    public void setWorkouts(List<Workout> workouts){ this.workouts = workouts; }
}
