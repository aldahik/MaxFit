package com.aldahik.workout.dto;

public record WorkoutSummaryResponse(Integer userId, Integer workoutId, String name, Long durationSeconds) {
}
