/**
 * Student Record Management System
*/
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class that serves as the entry point for the application
 */
public class main {
    public static void main(String[] args) {
        // Create and start the student management system
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.start();
    }
}

/**
 * Student class represents a student entity with all its attributes and behaviors
 * This demonstrates encapsulation by keeping fields private and providing getter/setter methods
 */
class Student {
    // Private member variables for data encapsulation
    private String id;      // Unique identifier for the student
    private String name;    // Name of the student
    private int age;        // Age of the student
    private double grade;   // Academic grade of the student

    /**
     * Constructor to initialize a Student object with all required data
     *
     * @param id     Unique identifier for the student
     * @param name   Name of the student
     * @param age    Age of the student
     * @param grade  Academic grade of the student
     * @throws IllegalArgumentException if any input values are invalid
     */
    public Student(String id, String name, int age, double grade) {
        // Validate inputs before assigning
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120");
        }
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be between 0.0 and 100.0");
        }

        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Getter methods to provide controlled access to private fields
    /**
     * @return The student's ID
     */
    public String getId() { return id; }

    /**
     * @return The student's name
     */
    public String getName() { return name; }

    /**
     * @return The student's age
     */
    public int getAge() { return age; }

    /**
     * @return The student's grade
     */
    public double getGrade() { return grade; }

    // Setter methods to provide controlled modification of private fields
    /**
     * @param name The new name for the student
     * @throws IllegalArgumentException if name is empty or null
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        this.name = name;
    }

    /**
     * @param age The new age for the student
     * @throws IllegalArgumentException if age is not in valid range
     */
    public void setAge(int age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120");
        }
        this.age = age;
    }

    /**
     * @param grade The new grade for the student
     * @throws IllegalArgumentException if grade is not in valid range
     */
    public void setGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be between 0.0 and 100.0");
        }
        this.grade = grade;
    }

    /**
     * Override the toString method to provide a formatted string representation of a Student
     * This method demonstrates polymorphism through method overriding
     *
     * @return Formatted string with all student details
     */
    @Override
    public String toString() {
        return "ID: " + id +
               " | Name: " + name +
               " | Age: " + age +
               " | Grade: " + grade;
    }
}

/**
 * StudentRepository class handles the storage and retrieval of student records
 * This demonstrates the Single Responsibility Principle by separating data management
 * from other concerns like UI or business logic
 */
class StudentRepository {
    private ArrayList<Student> students;       // Collection to store student objects
    private static int totalStudents = 0;      // Static variable to track total number of students

    /**
     * Constructor that initializes an empty student collection
     */
    public StudentRepository() {
        students = new ArrayList<>();
    }

    /**
     * Adds a new student to the repository if the ID doesn't already exist
     *
     * @param student Student object to be added
     * @return true if student was added successfully, false if ID already exists
     */
    public boolean addStudent(Student student) {
        // Check if a student with the same ID already exists
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return false;  // Student with this ID already exists
            }
        }
        students.add(student);
        totalStudents++;      // Increment the static counter
        return true;
    }

    /**
     * Searches for a student by their ID
     *
     * @param id The ID to search for
     * @return Student object if found, null otherwise
     */
    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;  // Student not found
    }

    /**
     * Returns all students in the repository
     *
     * @return ArrayList containing all student records
     */
    public ArrayList<Student> getAllStudents() {
        return students;
    }

    /**
     * Gets the total count of students added to the system
     *
     * @return Total number of students
     */
    public int getTotalStudents() {
        return totalStudents;
    }
}

/**
 * StudentManagementSystem class provides the user interface and controls the program flow
 * This demonstrates separation of concerns by handling only UI and user interaction
 */
class StudentManagementSystem {
    private Scanner scanner;             // For reading user input
    private StudentRepository repository; // For storing and retrieving student data

    /**
     * Constructor initializes the scanner and repository
     */
    public StudentManagementSystem() {
        scanner = new Scanner(System.in);
        repository = new StudentRepository();
    }

    /**
     * Main method that starts the application and controls program flow
     */
    public void start() {
        System.out.println("=== Student Record Management System ===");
        boolean running = true;

        // Main program loop
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            // Handle user's choice using a switch statement
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    viewStudentDetails();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();  // Close the scanner to prevent resource leaks
    }

    /**
     * Displays the main menu options to the user
     */
    private void displayMenu() {
        System.out.println("\n1. Add a new student");
        System.out.println("2. Update student information");
        System.out.println("3. View student details");
        System.out.println("4. View all students");
        System.out.println("5. Exit");
    }

    /**
     * Handles the process of adding a new student
     * Collects student information and adds them to the repository
     */
    private void addStudent() {
        System.out.println("\n--- Adding New Student ---");

        try {
            // Collect student information from user
            String id = getStringInput("Enter student ID: ");
            String name = getStringInput("Enter student name: ");
            int age = getIntInput("Enter student age: ");
            double grade = getDoubleInput("Enter student grade (0.0-100.0): ");

            // Create a new student object with the input data
            Student student = new Student(id, name, age, grade);

            // Attempt to add the student and notify user of the result
            if (repository.addStudent(student)) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Error: Student with ID " + id + " already exists.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Student creation failed. Please try again.");
        }
    }

    /**
     * Handles the process of updating an existing student's information
     * Provides options to update specific or all fields
     */
    private void updateStudent() {
        System.out.println("\n--- Updating Student Information ---");
        String id = getStringInput("Enter student ID to update: ");

        // Find the student in the repository
        Student student = repository.findStudentById(id);
        if (student == null) {
            System.out.println("Error: Student with ID " + id + " not found.");
            return;
        }

        // Show current details before updating
        System.out.println("Current details: " + student);

        // Present update options
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Grade");
        System.out.println("4. All information");

        int choice = getIntInput("Enter your choice: ");

        try {
            // Process the selected update option
            switch (choice) {
                case 1:
                    student.setName(getStringInput("Enter new name: "));
                    break;
                case 2:
                    student.setAge(getIntInput("Enter new age: "));
                    break;
                case 3:
                    student.setGrade(getDoubleInput("Enter new grade (0.0-100.0): "));
                    break;
                case 4:
                    student.setName(getStringInput("Enter new name: "));
                    student.setAge(getIntInput("Enter new age: "));
                    student.setGrade(getDoubleInput("Enter new grade (0.0-100.0): "));
                    break;
                default:
                    System.out.println("Invalid choice. Update cancelled.");
                    return;
            }
            System.out.println("Student information updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Update failed. Please try again with valid data.");
        }
    }

    /**
     * Displays the details of a specific student identified by ID
     */
    private void viewStudentDetails() {
        System.out.println("\n--- Viewing Student Details ---");
        String id = getStringInput("Enter student ID: ");

        // Find and display student information
        Student student = repository.findStudentById(id);
        if (student == null) {
            System.out.println("Error: Student with ID " + id + " not found.");
            return;
        }

        System.out.println(student);
    }

    /**
     * Displays all students in the system with a total count
     */
    private void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        ArrayList<Student> students = repository.getAllStudents();

        // Check if there are any students in the system
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        // Display all students in the repository
        for (Student student : students) {
            System.out.println(student);
        }

        // Show total count of students
        System.out.println("\nTotal number of students: " + repository.getTotalStudents());
    }

    /**
     * Helper method to get string input from user with proper prompt
     * Validates that the input is not empty
     *
     * @param prompt Message to display to the user
     * @return User's validated string input
     */
    private String getStringInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty. Please try again.");
            } else {
                return input;
            }
        }
    }

    /**
     * Helper method to get integer input with error handling
     * Validates that the input is a valid integer within acceptable range
     *
     * @param prompt Message to display to the user
     * @return User's validated integer input
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());

                // Additional validation can be added here for specific fields
                if (prompt.toLowerCase().contains("age")) {
                    if (value < 1 || value > 120) {
                        System.out.println("Error: Age must be between 1 and 120. Please try again.");
                        continue;
                    }
                } else if (prompt.toLowerCase().contains("choice")) {
                    if (value < 1 || value > 5) {
                        System.out.println("Error: Please enter a number between 1 and 5.");
                        continue;
                    }
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Helper method to get double input with error handling
     * Validates that the input is a valid double within acceptable range
     *
     * @param prompt Message to display to the user
     * @return User's validated double input
     */
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());

                // Additional validation for grade inputs
                if (prompt.toLowerCase().contains("grade")) {
                    if (value < 0.0 || value > 100.0) {
                        System.out.println("Error: Grade must be between 0.0 and 100.0. Please try again.");
                        continue;
                    }
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }
    }
}