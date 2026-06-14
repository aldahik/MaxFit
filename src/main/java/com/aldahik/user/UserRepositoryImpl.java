package com.aldahik.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public class UserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> findByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
}
