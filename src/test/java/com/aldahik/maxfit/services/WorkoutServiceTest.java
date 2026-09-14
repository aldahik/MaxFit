package com.aldahik.maxfit.services;

import com.aldahik.user.User;
import com.aldahik.user.UserRepository;
import com.aldahik.workout.Workout;
import com.aldahik.workout.WorkoutRepository;
import com.aldahik.workout.WorkoutService;
import com.aldahik.workout.dto.WorkoutRequest;
import com.aldahik.workout.dto.WorkoutResponse;
import com.aldahik.workout.dto.WorkoutSummaryResponse;
import com.aldahik.workout.mapper.WorkoutMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class WorkoutServiceTest {
    @Mock
    private WorkoutRepository workoutRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private WorkoutMapper workoutMapper;
    @InjectMocks
    private WorkoutService workoutService;

    @Test
    void getUsersWorkouts_shouldReturnWorkoutResponseList() {
        User user = new User("test", "test", "test", 1);
        int userId = 1;
        Workout workout = new Workout();
        workout.setUser(user);
        workout.setDurationSeconds(Duration.ofSeconds(1L));
        workout.setName("test");
        given(workoutRepository.findWorkoutsByUserUserId(userId)).willReturn(List.of(workout));
        given(workoutMapper.toSummaryResponse(workout)).willReturn(new WorkoutSummaryResponse(userId, workout.getWorkoutId(), "test", 1L));
        List<WorkoutSummaryResponse> result = workoutService.getUsersWorkouts(userId);
        assertThat(result.size()).isEqualTo(1);
        assertEquals(workout.getName(), result.get(0).name());
        assertEquals(workout.getDurationSeconds(), Duration.ofSeconds(result.get(0).durationSeconds()));
        verify(workoutRepository).findWorkoutsByUserUserId(userId);
    }

    @Test
    void createWorkout_shouldReturnWorkoutResponseIfUserExists(){
        User user = new User("test", "test", "test", 1);
        int userId = 1;
        Workout workout = new Workout();
        workout.setUser(user);
        workout.setName("test");
        workout.setDurationSeconds(Duration.ofSeconds(1L));
        WorkoutRequest workoutRequest = new WorkoutRequest(workout.getName(), 1L, Collections.emptyList());
        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(workoutMapper.toResponse(workout))
                .willReturn(new WorkoutResponse(
                        userId, workout.getWorkoutId(), "test", 1L, List.of()));
        given(workoutMapper.toWorkout(workoutRequest)).willReturn(workout);
        given(workoutRepository.save(any(Workout.class))).willReturn(workout);
        WorkoutResponse result = workoutService.createWorkout(userId, workoutRequest);
        assertEquals(workoutRequest.name(), result.name());
        assertEquals(workoutRequest.durationSeconds(), result.durationSeconds());
        verify(userRepository).findById(userId);
        verify(workoutRepository).save(any(Workout.class));
    }
}
