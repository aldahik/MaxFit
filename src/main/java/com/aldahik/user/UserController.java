package com.aldahik.user;

import com.aldahik.workout.WorkoutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService, WorkoutService workoutService) {
        this.userService = userService;
    }

    @GetMapping("/{userid}")
    public User getUserById(@PathVariable Integer userid){
        return userService.getUserById(userid);
    }

    @GetMapping("/name/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
}
