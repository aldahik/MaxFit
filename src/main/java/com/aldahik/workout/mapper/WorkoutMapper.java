package com.aldahik.workout.mapper;

import com.aldahik.exercise.Exercise;
import com.aldahik.exercise.dto.ExerciseRequest;
import com.aldahik.exercise.dto.ExerciseResponse;
import com.aldahik.workout.Workout;
import com.aldahik.workout.dto.WorkoutRequest;
import com.aldahik.workout.dto.WorkoutResponse;
import com.aldahik.workout.dto.WorkoutSummaryResponse;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Component
public class WorkoutMapper {
    private Long toSeconds(Duration duration){
        return duration == null ? null : duration.getSeconds();
    }
    public WorkoutResponse toResponse(Workout workout){
        return new WorkoutResponse(
                workout.getUser().getUserId(),
                workout.getWorkoutId(),
                workout.getName(),
                toSeconds(workout.getDurationSeconds()),
                workout.getExercisesList().stream().map(this::toExerciseResponse).toList()
        );
    }

    public WorkoutSummaryResponse toSummaryResponse(Workout workout){
        return new WorkoutSummaryResponse(
                workout.getUser().getUserId(),
                workout.getWorkoutId(),
                workout.getName(),
                toSeconds(workout.getDurationSeconds())
        );
    }

    public ExerciseResponse toExerciseResponse(Exercise exercise){
        return new ExerciseResponse(exercise.getId(), exercise.getName(), exercise.getType(), exercise.getSets(), exercise.getReps(), exercise.getRir(), exercise.getNotes());
    }

    public Exercise toExercise(ExerciseRequest exerciseRequest){
        return new Exercise(exerciseRequest.name(), exerciseRequest.type(), exerciseRequest.sets(), exerciseRequest.reps(), exerciseRequest.rir(), exerciseRequest.notes());
    }

    public Workout toWorkout(WorkoutRequest workoutRequest){
        Workout workout = new Workout();
        workout.setName(workoutRequest.name());
        if (workoutRequest.durationSeconds() != null){
            workout.setDurationSeconds(Duration.ofSeconds(workoutRequest.durationSeconds()));
        }

        List<ExerciseRequest> exercises = Optional
                .ofNullable(workoutRequest.exerciseList())
                .orElse(List.of());

        workout.setExercisesList(exercises.stream()
                        .map(this::toExercise)
                        .toList());

        return workout;
    }

}