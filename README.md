# Capstone Project
# MarkProject: DailyMark
## Introduction

DailyMark is a life tracker inspired by my hobby of recording everything in my life. Whether it's the journey of a new plant growing from seed to peppers, tracking my body changes after each workout session, or monitoring my personal health, DailyMark serves as a centralized platform to log and track these aspects of my life.


## Overview of the Application
DailyMark, is a web-based platform designed to help users track and manage their daily tasks and milestones efficiently. The application leverages Spring Boot for the backend, Thymeleaf for server-side rendering of HTML, and integrates a calendar view to keep track of tasks and marks. Users can sign up, log in, view, add, update, and delete their marks for specific dates, enhancing productivity and organization.
### Login page:
![login_page.png](src%2Fscreenshots%2Flogin_page.png)
### Calendar View: 
![Calendar view.png](src%2Fscreenshots%2FCalendar%20view.png)
### Calander View with Side Bar
![Calander View with Side Bar.png](src%2Fscreenshots%2FCalander%20View%20with%20Side%20Bar.png)
## Business Use Cases
1. *Personal Task Management*: Users can create, view, and manage their daily tasks and milestones, ensuring they stay organized and productive.
2. *User Registration*: New users can sign up by setting up their username and password, ensuring secure and personalized access.
3. *Interactive Calendar*: The application provides an interactive calendar to visualize tasks and events, helping users to plan their schedules effectively.
4. *Secure Access*: User authentication ensures that only authorized users can access and manage their marks.

## Technical Perspective
### Overview for technology Perspective
### Techonoly Used:
1. Java
2. Spring Boot
3. Spring Security
4. Spring Data JPA
5. Thymeleaf (for server-side templating)
6. MySQL (as the database)
7. SLF4J and Logback (for logging)
8. Maven (for dependency management)
9. HTML, CSS and JavaScript(for frontend)

From a high-level technical perspective, DailyMark integrates several key technologies and frameworks to deliver its functionality:

### Frontend

- **Thymeleaf Templates**: Utilizes Thymeleaf templates for dynamic server-side rendering of HTML pages.
- **HTML, CSS, and JavaScript**: The user interface is built with these core web technologies.
- **FullCalendar Library**: Used for the interactive calendar view.
- **Custom Styling and Scripts**: Ensure a responsive and interactive user experience.
- **Asynchronous Data Fetching**: AJAX calls are used to fetch data asynchronously, allowing for real-time updates and interactions without page reloads.

### Backend

- **Spring Boot**: The backend services are implemented using Spring Boot.
    
    - **Dependency Injection**: Utilized to manage service dependencies, ensuring a modular and testable codebase.
    - **Spring Data JPA**: Used for database interactions. It simplifies the implementation of data access layers by reducing the boilerplate code required for database operations.
    - **Spring Security**: Can be integrated to manage authentication and authorization, ensuring secure access to the application.

### Database

- **MySQL**: The application uses a MySQL database to store user data, tasks, and other relevant information.
- **JPA (Java Persistence API)**: Used for Object-Relational Mapping (ORM), ensuring seamless interaction between the application and the database.

## Lessons Learned
During the development of DailyMark, several key lessons were learned:

1. Effective Use of Spring Boot: Leveraging the powerful features of Spring Boot for building scalable and maintainable applications.
2. Thymeleaf Integration: Using Thymeleaf for server-side rendering allowed for a seamless integration of backend logic with frontend templates.
3. Security Implementation: Implementing Spring Security provided a robust framework for managing authentication and authorization, ensuring the application's security.
4. AJAX: Enhanced user experience by implementing AJAX for asynchronous data fetching and updating.
## Future Features
<li><a href="#">Profile</a></li>Users can edit their profile, including changing their avatar, password, and username, ensuring their information is always up to date.
            <li><a href="#">Notification</a></li>Implement a real-time notification system to alert users of upcoming tasks or deadlines.
            <li><a href="#">Friend Circle</a></li> Allow users add other users(friends).
            <li><a href="#">Share</a></li> Allow users to share tasks and collaborate with others, enhancing teamwork and productivity.
            <li><a href="#">Analysis Marks</a></li>Provide detailed reports and analytics on user productivity and task completion rates.
            <li><a href="#">Search Marks</a></li> Search Marks by keywords, date, categories etc.
            <li><a href="#">History</a></li> Check all the records
            <li><a href="#">Download Mobile Version</a></li>Develop a mobile application to allow users to manage their tasks on the go.
            <li><a href="#">Delete Account</a></li> Allow users delete their account.
            
