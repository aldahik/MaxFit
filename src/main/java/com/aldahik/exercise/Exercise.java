package com.aldahik.exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Exercise {
    @Id
    private int exercise_id;
    private String name;
    ExerciseType type;
    Integer sets;
    Integer reps;

    Integer rir;
    String notes;
    public Exercise() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }

    public Integer getSets() {
        return sets;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getRir() {
        return rir;
    }

    public void setRir(Integer rir) {
        this.rir = rir;
    }


    public Exercise(String name, Integer sets, Integer reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }

}