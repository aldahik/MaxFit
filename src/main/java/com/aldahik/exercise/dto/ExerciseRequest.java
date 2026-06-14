package com.aldahik.exercise.dto;

import com.aldahik.exercise.ExerciseType;

public record ExerciseRequest(String name, ExerciseType type, Integer sets, Integer reps, Integer rir, String notes) {}
