package com.aldahik.exercise;

import com.aldahik.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Workout, Integer> {
    List<Exercise> findExerciseByWorkoutId(Integer WorkoutId);
}
