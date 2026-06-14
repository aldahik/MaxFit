package com.aldahik.workout;

import com.aldahik.exception.ResourceNotFoundException;
import com.aldahik.user.User;
import com.aldahik.user.UserRepository;
import com.aldahik.workout.dto.WorkoutRequest;
import com.aldahik.workout.dto.WorkoutResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<WorkoutResponse> getUsersWorkouts(Integer userid) {
        return workoutRepository.findWorkoutsByUserUserid(userid).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public WorkoutResponse createWorkout(Integer userid, WorkoutRequest request) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userid + " not found"));
        Workout workout = new Workout();
        workout.setName(request.name());
        if (request.durationMinutes() != null) {
            workout.setDuration(Duration.ofMinutes(request.durationMinutes()));
        }
        workout.setUser(user);
        return toResponse(workoutRepository.save(workout));
    }

    private WorkoutResponse toResponse(Workout w) {
        Long minutes = w.getDuration() != null ? w.getDuration().toMinutes() : null;
        return new WorkoutResponse(w.getWorkoutId(), w.getName(), minutes);
    }
}
