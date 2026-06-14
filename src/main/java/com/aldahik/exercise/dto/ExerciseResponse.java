package com.aldahik.exercise.dto;

import com.aldahik.exercise.ExerciseType;

public record ExerciseResponse(Integer id, String name, ExerciseType type, Integer sets, Integer reps, Integer rir, String notes) {}
