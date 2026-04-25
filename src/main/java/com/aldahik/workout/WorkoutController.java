package com.aldahik.workout;

import com.aldahik.exercise.Exercise;
import com.aldahik.exercise.ExerciseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users/{userid}/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getUsersWorkoutList(@PathVariable Integer userid) {
        return workoutService.getUsersWorkouts(userid);
    }

    @RequestMapping("{workoutId}/exercises")
    @GetMapping
    public List<Exercise> getExercises(@PathVariable Integer workoutId) {
        return workoutService.getWorkout(workoutId);
    }

}
