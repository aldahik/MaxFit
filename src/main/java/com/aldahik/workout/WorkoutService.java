package com.aldahik.workout;

import com.aldahik.exercise.Exercise;
import com.aldahik.exercise.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseService exerciseService;


    public WorkoutService(WorkoutRepository workoutRepository, ExerciseService exerciseService) {
        this.workoutRepository = workoutRepository;
        this.exerciseService = exerciseService;
    }

    public List<Workout> getUsersWorkouts(int userid) {
        return workoutRepository.findWorkoutsByUserUserid(userid);
    }

    public List<Exercise> getWorkout(int workoutId){
        return workoutRepository.findWorkoutByWorkoutId(workoutId);
    }
}
