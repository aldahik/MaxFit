package com.aldahik.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public User getUserByUsername(String username){
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new IllegalStateException("user not found");
        }

    }
}
