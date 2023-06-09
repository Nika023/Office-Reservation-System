# Office-Reservation-System

This Java backend-only project provides a simple solution to manage an office reservation schedule. User or employee can see the existing offices and their details. After registration and login, they can choose and reserve the office on the exact day and time. The app checks the availability at the requested time. They then receive a confirmation email with the details. If they change their mind, the reservation can be deleted. Via other endpoints, they also can see their own reservations or reservations for desired offices.

# Technologies

The application is secured with JSON Web Token with use of Spring Security. All data are saved in MySQL database. Code is formated according to Google Stylesheet and gradle checkstyle is used.
Git and GitHub were used throughout the whole project. 

# API endpoints
- /register
- /login
- /offices
- /office/{id}
- /new-reservation
- /my-reservations
- /reservation/{id}

# Dependecies
- Java Development Kit - JDK 8
- Spring Boot Data JPA
- Spring Security
- Spring Web
- Java Mail
