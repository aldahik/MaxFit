package com.aldahik.exercise;

import com.aldahik.exception.ResourceNotFoundException;
import com.aldahik.exercise.dto.ExerciseRequest;
import com.aldahik.exercise.dto.ExerciseResponse;
import com.aldahik.workout.Workout;
import com.aldahik.workout.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExerciseService {

    private final WorkoutRepository workoutRepository;

    public ExerciseService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Transactional(readOnly = true)
    public List<ExerciseResponse> getExercisesForWorkout(Integer workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout " + workoutId + " not found"));
        return workout.getExercisesList().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ExerciseResponse addExercise(Integer workoutId, ExerciseRequest request) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout " + workoutId + " not found"));
        Exercise exercise = new Exercise();
        exercise.setName(request.name());
        exercise.setType(request.type());
        exercise.setSets(request.sets());
        exercise.setReps(request.reps());
        exercise.setRir(request.rir());
        exercise.setNotes(request.notes());
        workout.getExercisesList().add(exercise);
        workoutRepository.save(workout);
        return toResponse(exercise);
    }

    private ExerciseResponse toResponse(Exercise e) {
        return new ExerciseResponse(e.getId(), e.getName(), e.getType(), e.getSets(), e.getReps(), e.getRir(), e.getNotes());
    }
}
