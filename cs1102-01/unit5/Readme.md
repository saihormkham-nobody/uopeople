# Course Enrollment and Grade Management System

## Classes

### Main
The main class acts as a controller handle the user input.

### Student
Represents a student entity with personal information, course enrollments, and grades.

### Course
Represents a course that students can enroll in.

### CourseManagement
Central management class that coordinates operations between courses and students.

## Static Features
The system uses static methods and variables in several ways:

1. **In the Course class:**
   * `totalEnrolledStudents`: A static counter that tracks the total number of enrollments across all Course instances.
   * When a student enrolls in or unenrolls from a course, this counter is updated.

2. **In the CourseManagement class:**
   * `courses`, `students`, and `overallGrades`: Static collections that maintain system-wide data.
   * All methods are static to provide global access to system functions without requiring class instantiation.

## How to Run the Program

1. Compile the program:
```
javac main.java
```

2. Run the program:
```
java main
```