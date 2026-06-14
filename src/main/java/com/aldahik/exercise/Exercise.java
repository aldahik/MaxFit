package com.aldahik.exercise;

import jakarta.persistence.*;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private ExerciseType type;
    private Integer sets;
    private Integer reps;
    private Integer rir;
    private String notes;

    public Exercise() {}

    public Exercise(String name, Integer sets, Integer reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ExerciseType getType() { return type; }
    public void setType(ExerciseType type) { this.type = type; }
    public Integer getSets() { return sets; }
    public void setSets(Integer sets) { this.sets = sets; }
    public Integer getReps() { return reps; }
    public void setReps(Integer reps) { this.reps = reps; }
    public Integer getRir() { return rir; }
    public void setRir(Integer rir) { this.rir = rir; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
