package com.aldahik.workout;

import com.aldahik.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    List<Workout> findWorkoutsByUserUserid(Integer userUserid);

    List<Exercise> findWorkoutByWorkoutId(int workoutId);
}
