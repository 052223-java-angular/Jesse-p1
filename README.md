# P1

## Introduction


The music library management system is a full-stack application that allows users to manage their music collection, search for new music, 
and create playlists. Users can add songs, and artists to their library, search for music based on criteria, and create playlists. 
The application will be developed using Spring Boot for the backend and Angular for the frontend.


## User Stories

- **As a user**, I want to register/login to my account
- **As a user**, I want to manage my music library add songs, albums, and artists to my library.
- **As a user**, I want to search for songs or albums based on title, artist, or genre.
- **As a user**, I want to create and manage playlists by adding or removing songs
- **As a user**, I want to discuss music on a forum with other users
- **As a user**, I want to view my profile and presented statistics based on my history

## MVP (Minimum Viable Product)

- User registration and login
- Browsing and searching for music
- Adding music to library
- Create playlists
- Modify play lists
- Profile page with statistics
- Discuss music with other users

## Stretch Goals

- Ability to discover new music through recommendations based on my listening history.
- Steam/ control music through connected available devices
- As a user, I want to view trending songs, popular artists, or top charts.
- As a user, follow other users and playlists

## Tech Stacks

- **Java**: The main programming language used for building the application.
- **PostgreSQL**: Used as the database to store user, product, and order data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Log4j**: A logging utility for debugging purposes.
- **JDBC (Java Database Connectivity)**: An API for connecting and executing queries on the database.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and PowerMock**: Used for unit and integration testing.
- **Postman**: Used for unit and integration testing.
- **Angular**: Develop the frontend using Angular, including components, services, and routing
- **Public API**: Spotify API
- **Spring Boot**: Build RESTful APIs using Spring Boot to handle backend operations
- **Git and GitHub**: Used for version control.

## Requirements

- **Clean Codebase**: All code should be clean and well-documented. The repository should not include any unnecessary files or folders such as the `target/`, `.DS_Store`, etc. All files and directories should be appropriately named and organized.

- **Database Design**: The database should be designed following the principles of the 3rd Normal Form (3NF) to ensure data integrity and efficiency. An Entity Relationship Diagram (ERD) should be included in the documentation.

- **Secure**: All sensitive user data such as passwords must be securely hashed before storing it in the database. The application should not display any sensitive information in error messages.

- **Error Handling**: The application should handle potential errors gracefully and provide clear and helpful error messages to the users.

- **Testing**: The application should have a high test coverage. Unit tests and integration tests should be implemented using JUnit, Mockito, and PowerMock.

- **Version Control**: The application should be developed using a version control system, preferably Git, with regular commits denoting progress.

- **Documentation**: The repository should include a README file with clear instructions on how to run the application. Code should be well-commented to allow for easy understanding and maintenance.

- **Scalable**: The design of the application should be scalable, allowing for easy addition of new features or modifications in the future.

