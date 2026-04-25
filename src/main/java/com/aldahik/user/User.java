package com.aldahik.user;

import com.aldahik.workout.Workout;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
   @Id
    public Integer userid;
    public String username;
    private String firstname;
    private String lastname;
    private Integer age;
    @OneToMany(mappedBy = "user")
    private List<Workout> workoutsList;

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User(String username, String firstname, String lastname, Integer age) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    void addWorkout(Workout workout){
        workoutsList.add(workout);
    }
}