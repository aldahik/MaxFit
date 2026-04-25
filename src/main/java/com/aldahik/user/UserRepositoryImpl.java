package com.aldahik.user;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User findByUsername(String username){
        String jpql = "SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
