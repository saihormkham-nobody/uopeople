import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to Course Enrollment and Grade Management System");

        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a new course");
            System.out.println("2. Enroll a student in a course");
            System.out.println("3. Assign grade to a student");
            System.out.println("4. Calculate overall grade for a student");
            System.out.println("5. Display all courses");
            System.out.println("6. Display enrolled students");
            System.out.println("7. Create a new student");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    enrollStudent(scanner);
                    break;
                case 3:
                    assignGrade(scanner);
                    break;
                case 4:
                    calculateGrade(scanner);
                    break;
                case 5:
                    displayCourses();
                    break;
                case 6:
                    displayStudents();
                    break;
                case 7:
                    createStudent(scanner);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the Course Enrollment and Grade Management System!");
        scanner.close();
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();

        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter maximum capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            CourseManagement.addCourse(code, name, capacity);
            System.out.println("Course added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        try {
            CourseManagement.addStudent(name, id);
            System.out.println("Student created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void enrollStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        try {
            CourseManagement.enrollStudent(studentId, courseCode);
            System.out.println("Student enrolled successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void assignGrade(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Enter grade (0-100): ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        try {
            CourseManagement.assignGrade(studentId, courseCode, grade);
            System.out.println("Grade assigned successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void calculateGrade(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        try {
            double overallGrade = CourseManagement.calculateOverallGrade(studentId);
            System.out.println("Overall grade for student: " + overallGrade);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayCourses() {
        CourseManagement.displayAllCourses();
    }

    private static void displayStudents() {
        CourseManagement.displayAllStudents();
    }
}

class Student {
    private String name;
    private String id;
    private Set<Course> enrolledCourses;
    private Map<String, Double> courseGrades; // Maps course code to grade

    public Student(String name, String id) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }

        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashSet<>();
        this.courseGrades = new HashMap<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }
        this.id = id;
    }

    public Set<Course> getEnrolledCourses() {
        return new HashSet<>(enrolledCourses);
    }

    // Enroll student in a course
    public void enrollInCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        if (enrolledCourses.contains(course)) {
            throw new IllegalArgumentException("Student is already enrolled in this course");
        }

        enrolledCourses.add(course);
    }

    // Assign grade to a course
    public void assignGrade(Course course, double grade) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }

        if (!enrolledCourses.contains(course)) {
            throw new IllegalArgumentException("Student is not enrolled in this course");
        }

        courseGrades.put(course.getCode(), grade);
    }

    // Get grade for a specific course
    public Double getGradeForCourse(String courseCode) {
        return courseGrades.get(courseCode);
    }

    // Get all grades
    public Map<String, Double> getAllGrades() {
        return new HashMap<>(courseGrades);
    }

    // Check if student is enrolled in a specific course
    public boolean isEnrolledIn(Course course) {
        return enrolledCourses.contains(course);
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "'}";
    }
}

class Course {
    private String code;
    private String name;
    private int maxCapacity;
    private Set<Student> enrolledStudents;

    // Static variable to track total enrolled students across all courses
    private static int totalEnrolledStudents = 0;

    public Course(String code, String name, int maxCapacity) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Maximum capacity must be greater than zero");
        }

        this.code = code;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new HashSet<>();
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentEnrollment() {
        return enrolledStudents.size();
    }

    // Static method to get total enrolled students across all courses
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    // Add a student to this course
    public void enrollStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (enrolledStudents.contains(student)) {
            throw new IllegalArgumentException("Student is already enrolled in this course");
        }

        if (enrolledStudents.size() >= maxCapacity) {
            throw new IllegalArgumentException("Course has reached maximum capacity");
        }

        enrolledStudents.add(student);
        totalEnrolledStudents++; // Increment the static counter
    }

    // Remove a student from this course
    public void unenrollStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (!enrolledStudents.contains(student)) {
            throw new IllegalArgumentException("Student is not enrolled in this course");
        }

        enrolledStudents.remove(student);
        totalEnrolledStudents--; // Decrement the static counter
    }

    // Get all enrolled students
    public Set<Student> getEnrolledStudents() {
        return new HashSet<>(enrolledStudents);
    }

    @Override
    public String toString() {
        return "Course{code='" + code + "', name='" + name + "', capacity=" + maxCapacity +
               ", enrolled=" + enrolledStudents.size() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Course course = (Course) obj;
        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}

class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Map<String, Double> overallGrades = new HashMap<>();

    // Add a new course to the system
    public static void addCourse(String code, String name, int maxCapacity) {
        // Check if course with same code already exists
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                throw new IllegalArgumentException("Course with this code already exists");
            }
        }

        Course newCourse = new Course(code, name, maxCapacity);
        courses.add(newCourse);
    }

    // Add a new student to the system
    public static void addStudent(String name, String id) {
        // Check if student with same ID already exists
        for (Student student : students) {
            if (student.getId().equals(id)) {
                throw new IllegalArgumentException("Student with this ID already exists");
            }
        }

        Student newStudent = new Student(name, id);
        students.add(newStudent);
    }

    // Find a student by ID
    private static Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        throw new IllegalArgumentException("Student not found with ID: " + studentId);
    }

    // Find a course by code
    private static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        throw new IllegalArgumentException("Course not found with code: " + courseCode);
    }

    // Enroll a student in a course
    public static void enrollStudent(String studentId, String courseCode) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);

        // Enroll student in course
        if (student.isEnrolledIn(course)) {
            throw new IllegalArgumentException("Student is already enrolled in this course");
        }

        student.enrollInCourse(course);
        course.enrollStudent(student);
    }

    // Assign a grade to a student for a specific course
    public static void assignGrade(String studentId, String courseCode, double grade) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);

        // Check if student is enrolled in the course
        if (!student.isEnrolledIn(course)) {
            throw new IllegalArgumentException("Student is not enrolled in this course");
        }

        // Assign grade
        student.assignGrade(course, grade);

        // Recalculate overall grade
        calculateOverallGrade(studentId);
    }

    // Calculate overall grade for a student
    public static double calculateOverallGrade(String studentId) {
        Student student = findStudent(studentId);
        Map<String, Double> grades = student.getAllGrades();

        if (grades.isEmpty()) {
            overallGrades.put(studentId, 0.0);
            return 0.0;
        }

        double totalGrade = 0.0;
        for (Double grade : grades.values()) {
            totalGrade += grade;
        }

        double overallGrade = totalGrade / grades.size();
        overallGrades.put(studentId, overallGrade);

        return overallGrade;
    }

    // Display all courses
    public static void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("\nAll Courses:");
        System.out.println("--------------------------------------------------");
        System.out.println("Code\tName\t\t\tCapacity\tEnrolled");
        System.out.println("--------------------------------------------------");

        for (Course course : courses) {
            System.out.printf("%-8s%-24s%-16d%d\n",
                    course.getCode(),
                    course.getName(),
                    course.getMaxCapacity(),
                    course.getCurrentEnrollment());
        }

        System.out.println("\nTotal number of enrolled students across all courses: " +
                           Course.getTotalEnrolledStudents());
    }

    // Display all students
    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\nAll Students:");
        System.out.println("--------------------------------------------------");
        System.out.println("ID\tName\t\t\tEnrolled Courses\tOverall Grade");
        System.out.println("--------------------------------------------------");

        for (Student student : students) {
            Double overallGrade = overallGrades.getOrDefault(student.getId(), 0.0);
            System.out.printf("%-8s%-24s%-16d%.2f\n",
                    student.getId(),
                    student.getName(),
                    student.getEnrolledCourses().size(),
                    overallGrade);
        }
    }
}
