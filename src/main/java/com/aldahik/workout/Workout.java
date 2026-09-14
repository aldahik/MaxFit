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

    public void setExercisesList(List<Exercise> exercisesList) {
        this.exercisesList = exercisesList;
    }

    private Duration durationSeconds;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "workoutId")
    private List<Exercise> exercisesList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Workout() {}

    public Integer getWorkoutId() { return workoutId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Duration getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Duration duration) { this.durationSeconds = duration; }
    public List<Exercise> getExercisesList() { return exercisesList; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
