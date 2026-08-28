package com.aldahik.maxfit.services;

import com.aldahik.user.User;
import com.aldahik.user.UserRepository;
import com.aldahik.workout.Workout;
import com.aldahik.workout.WorkoutRepository;
import com.aldahik.workout.WorkoutService;
import com.aldahik.workout.dto.WorkoutRequest;
import com.aldahik.workout.dto.WorkoutResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Duration;
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
    @InjectMocks
    private WorkoutService workoutService;

    @Test
    void getUsersWorkouts_shouldReturnWorkoutResponseList() {
        User user = new User("test", "test", "test", 1);
        Workout workout = new Workout();
        workout.setUser(user);
        workout.setDuration(Duration.ofMinutes(1L));
        workout.setName("test");
        given(workoutRepository.findWorkoutsByUserUserid(user.getUserid())).willReturn(List.of(workout));
        List<WorkoutResponse> result = workoutService.getUsersWorkouts(user.getUserid());
        assertThat(result.size()).isEqualTo(1);
        assertEquals(workout.getName(), result.get(0).name());
        assertEquals(workout.getDuration(), Duration.ofMinutes(result.get(0).durationMinutes()));
        verify(workoutRepository).findWorkoutsByUserUserid(user.getUserid());
    }

    @Test
    void createWorkout_shouldReturnWorkoutResponseIfUserExists(){
        User user = new User("test", "test", "test", 1);
        Workout workout = new Workout();
        workout.setUser(user);
        workout.setName("test");
        workout.setDuration(Duration.ofMinutes(1L));
        WorkoutRequest workoutRequest = new WorkoutRequest(workout.getName(), 1L);
        given(userRepository.findById(user.getUserid())).willReturn(Optional.of(user));
        given(workoutRepository.save(any(Workout.class))).willReturn(workout);
        WorkoutResponse result = workoutService.createWorkout(user.getUserid(), workoutRequest);
        assertEquals(workoutRequest.name(), result.name());
        assertEquals(workoutRequest.durationMinutes(), result.durationMinutes());
        verify(userRepository).findById(user.getUserid());
        verify(workoutRepository).save(any(Workout.class));
    }
}
