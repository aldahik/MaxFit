package com.aldahik.user;

import com.aldahik.exception.ResourceNotFoundException;
import com.aldahik.user.dto.UserRequest;
import com.aldahik.user.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserById(Integer id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
    }

    public UserResponse getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User '" + username + "' not found"));
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User(request.username(), request.firstname(), request.lastname(), request.age());
        return toResponse(userRepository.save(user));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getUserid(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getAge());
    }
}
