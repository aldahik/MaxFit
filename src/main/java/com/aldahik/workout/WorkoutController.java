package com.aldahik.workout;

import com.aldahik.exercise.ExerciseService;
import com.aldahik.exercise.dto.ExerciseRequest;
import com.aldahik.exercise.dto.ExerciseResponse;
import com.aldahik.workout.dto.WorkoutRequest;
import com.aldahik.workout.dto.WorkoutResponse;
import com.aldahik.workout.dto.WorkoutSummaryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/{userId}/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<WorkoutSummaryResponse> getUsersWorkouts(@PathVariable Integer userId) {
        return workoutService.getUsersWorkouts(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse createWorkout(@PathVariable Integer userId, @Valid @RequestBody WorkoutRequest request) {
        return workoutService.createWorkout(userId, request);
    }

    @GetMapping("/{workoutId}/exercises")
    public List<ExerciseResponse> getExercises(@PathVariable Integer workoutId) {
        return exerciseService.getExercisesForWorkout(workoutId);
    }

    @PostMapping("/{workoutId}/exercises")
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse addExercise(@PathVariable Integer workoutId, @RequestBody ExerciseRequest request) {
        return exerciseService.addExercise(workoutId, request);
    }
}
