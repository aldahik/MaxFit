package com.aldahik.maxfit.controllers;
import com.aldahik.exception.ResourceNotFoundException;
import com.aldahik.user.UserController;
import com.aldahik.user.UserService;
import com.aldahik.user.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private UserService userService;


    @Test
    void getUserById_ShouldReturnUserResponseWhenUserExists() throws Exception {
        UserResponse userResponse = new UserResponse(1, "test", "test", "test", 1);

        given(userService.getUserById(1)).willReturn(userResponse);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath(("$.userid")).value(1))
                .andExpect(jsonPath(("$.firstname")).value("test"))
                .andExpect(jsonPath(("$.lastname")).value("test"))
                .andExpect(jsonPath(("$.age")).value(1));

        verify(userService).getUserById(1);
    }

    @Test
    void getUserById_shouldThrowExceptionWhenUserDoesNotExist() throws Exception {
        given(userService.getUserById(999)).willThrow(new ResourceNotFoundException("User " + 999 + " not found"));

        mockMvc.perform(get("/api/users/999")).andExpect(status().isNotFound());
    }

}