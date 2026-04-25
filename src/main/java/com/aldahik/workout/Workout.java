package com.aldahik.workout;

import com.aldahik.user.User;
import com.aldahik.exercise.Exercise;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

@Entity
public class Workout{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workoutId;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "workoutId")
    private List<Exercise> exercisesList = new ArrayList<>();
    private Duration workout_duration;
    @ManyToOne
    private User user;

    public Workout(Integer workoutId) {
        this.workoutId = workoutId;
    }

    public Workout() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}