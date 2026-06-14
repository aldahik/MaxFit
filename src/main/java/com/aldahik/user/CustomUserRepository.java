package com.aldahik.user;

import java.util.Optional;

public interface CustomUserRepository {
    Optional<User> findByUsername(String username);
}
