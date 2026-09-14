package com.aldahik.workout;

import com.aldahik.user.User;
import com.aldahik.user.UserRepository;
import com.aldahik.workout.dto.WorkoutRequest;
import com.aldahik.workout.dto.WorkoutResponse;
import com.aldahik.workout.dto.WorkoutSummaryResponse;
import com.aldahik.workout.mapper.WorkoutMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.userRepository = userRepository;
    }

    public List<WorkoutSummaryResponse> getUsersWorkouts(int userId) {
        return workoutRepository.findWorkoutsByUserUserId(userId)
                .stream()
                .map(workoutMapper::toSummaryResponse)
                .toList();
    }

    @Transactional
    public WorkoutResponse createWorkout(Integer userId, WorkoutRequest workoutRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user " + userId + "not found"));

        Workout workout = workoutMapper.toWorkout(workoutRequest);
        workout.setUser(user);
        return workoutMapper.toResponse(workoutRepository.save(workout));
    }
}
