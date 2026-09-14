package com.aldahik.workout.dto;

import com.aldahik.exercise.dto.ExerciseResponse;
import java.util.List;

public record WorkoutResponse(Integer userId, Integer workoutId, String name, Long durationSeconds, List<ExerciseResponse> exerciseList) {
}
