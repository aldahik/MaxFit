# Workout Tracker API (Work in progress)

A backend API for tracking workouts, exercises, and training sessions.
Built with Java Spring Boot and PostgreSQL, following a layered architecture (Controller → Service → Repository).

---

## Features

* Create and manage users
* Create workouts for users
* Add exercises to workouts
* Track workout details (duration, exercises, sets, reps, etc.)
* RESTful API design

---

## API Endpoints (Current)

### Users

* `POST /api/users` → create a user
* `GET /api/users/{id}` → get a user

### Workouts

* `POST /api/users/{userId}/workouts` → create workout
* `GET /api/users/{userId}/workouts` → get all workouts for a user (summary)
* `GET /api/workouts/{id}` → get full workout (includes exercises)

---

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
