package com.aldahik.exercise;

import com.aldahik.workout.Workout;
import com.aldahik.workout.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getWorkoutExercises(Integer workoutId) {
        return exerciseRepository.findExerciseByWorkoutId(workoutId);
    }

}
