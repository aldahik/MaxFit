package com.aldahik.workout;

import com.aldahik.exercise.ExerciseService;
import com.aldahik.exercise.dto.ExerciseRequest;
import com.aldahik.exercise.dto.ExerciseResponse;
import com.aldahik.workout.dto.WorkoutRequest;
import com.aldahik.workout.dto.WorkoutResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/{userid}/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<WorkoutResponse> getUsersWorkouts(@PathVariable Integer userid) {
        return workoutService.getUsersWorkouts(userid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse createWorkout(@PathVariable Integer userid, @RequestBody WorkoutRequest request) {
        return workoutService.createWorkout(userid, request);
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
