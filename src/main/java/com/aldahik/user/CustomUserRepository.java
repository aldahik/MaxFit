package com.aldahik.user;


public interface CustomUserRepository {
    User findByUsername(String username);
}
