# Task Manager

### Overview

The Task Manager application is designed to help users efficiently manage tasks and associated notes. Built using Java, Gradle, and Spring Boot, this application offers a range of APIs for seamless task management.
### Features

    API Integration: The application includes 6+ RESTful APIs for creating, reading, updating, and deleting tasks.
    Task Management: Users can easily add, update, and delete tasks, making it simple to keep track of what needs to be done.
    Notes Functionality: Each task can have associated notes, allowing users to add more details and track progress effectively.
    Optimized Build Process: Leveraging Gradle for dependency management and build automation leads to improved efficiency and reduced build times.
    Performance: Built on Spring Boot, the application benefits from a simplified development process and enhanced performance.

## Technologies Used

    Java: The primary programming language used for application development.
    Spring Boot: A framework for building production-ready applications quickly and efficiently.
    Gradle: A powerful build tool for dependency management and automation.
    REST APIs: Implemented for communication between the frontend and backend.

## Installation
### Prerequisites
    JDK 17 or higher
    Gradle

## Steps

   ### 1. Clone the Repository:
      git clone <repository-url>
      cd task-manager

   ### 2. Build the Project:
      ./gradlew build

   ### 3. Run the Application:
      ./gradlew bootRun
      
## Usage

    Once the application is running, you can interact with the APIs using tools like Postman or cURL. Here are some example endpoints:

      Add a Task: POST /tasks
      Get Task by ID: GET /tasks/{id}
      Update a Task: PUT /tasks/{id}
      Delete a Task: DELETE /tasks/{id}
      Get All Tasks: GET /tasks

## Contributing 
    Contributions are welcome! Please follow these steps:
  
    Fork the repository.
    Create a new branch (git checkout -b feature-branch).
    Make your changes and commit them (git commit -m 'Add new feature').
    Push to the branch (git push origin feature-branch).
    Open a pull request.

## Acknowledgments

    Thanks to the Spring Boot community for their fantastic resources and support.
    Special thanks to Gradle for making dependency management easy and efficient.
