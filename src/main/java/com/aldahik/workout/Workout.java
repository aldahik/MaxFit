package com.aldahik.workout;

import com.aldahik.exercise.Exercise;
import com.aldahik.user.User;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workoutId;
    private String name;
    private Duration duration;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "workoutId")
    private List<Exercise> exercisesList = new ArrayList<>();

    @ManyToOne
    private User user;

    public Workout() {}

    public Integer getWorkoutId() { return workoutId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Duration getDuration() { return duration; }
    public void setDuration(Duration duration) { this.duration = duration; }
    public List<Exercise> getExercisesList() { return exercisesList; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
