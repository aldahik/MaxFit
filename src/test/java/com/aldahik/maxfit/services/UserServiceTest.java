package com.aldahik.maxfit.services;
import com.aldahik.exception.ResourceNotFoundException;
import com.aldahik.user.User;
import com.aldahik.user.UserRepository;
import com.aldahik.user.UserService;
import com.aldahik.user.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void getUserById_shouldReturnUserResponseWhenUserExist() {
        User user = new User("test", "test", "test", 1);

        given(userRepository.findById(user.getUserid())).willReturn(Optional.of(user));

        UserResponse result = userService.getUserById(user.getUserid());
        assertEquals(user.getUserid(), result.userid());
        assertEquals(user.getUsername(), result.username());
        assertEquals(user.getFirstname(), result.firstname());
        assertEquals(user.getLastname(), result.lastname());
        assertEquals(user.getAge(), result.age());

        verify(userRepository).findById((user.getUserid()));
    }

    @Test
    void getUserById_shouldThrowExceptionWhenUserDoesNotExist(){
        given(userRepository.findById(999)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,() -> userService.getUserById(999));
        verify(userRepository).findById((999));
    }

}
