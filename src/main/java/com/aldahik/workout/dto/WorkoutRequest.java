package com.aldahik.workout.dto;

import com.aldahik.exercise.dto.ExerciseRequest;
import jakarta.validation.constraints.*;

import java.util.List;
public record WorkoutRequest(@NotBlank String name, @Positive Long durationSeconds, List<ExerciseRequest> exerciseList) {
}
