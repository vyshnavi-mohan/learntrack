
package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;

import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;

import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);

    // Service objects
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();

    private final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        Main application = new Main();
        application.start();
    }

    // Main application
    public void start() {

        boolean running = true;

        System.out.println("\n==================================");
        System.out.println("       WELCOME TO LEARNTRACK");
        System.out.println("==================================");

        while (running) {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Exit");

            int choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    studentMenu();
                    break;

                case 2:
                    courseMenu();
                    break;

                case 3:
                    enrollmentMenu();
                    break;

                case 4:
                    running = false;
                    System.out.println("Thank you for using LearnTrack!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // =========================================
    // STUDENT MANAGEMENT
    // =========================================

    private void studentMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== STUDENT MANAGEMENT ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Deactivate Student");
            System.out.println("6. Back to Main Menu");

            int choice = readInteger("Enter your choice: ");

            try {
                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        viewStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        updateStudent();
                        break;

                    case 5:
                        deactivateStudent();
                        break;

                    case 6:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InvalidInputException |
                     EntityNotFoundException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addStudent() {

        System.out.println("\n--- ADD STUDENT ---");

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Batch: ");
        String batch = scanner.nextLine();

        Student student = studentService.addStudent(
                firstName, lastName, email, batch
        );

        System.out.println(
                "Student added successfully! ID: " + student.getId()
        );
    }

    private void viewStudents() {

        System.out.println("\n--- STUDENT LIST ---");

        if (studentService.getAllStudents().isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : studentService.getAllStudents()) {

            System.out.println(
                    student.getId() + " | "
                            + student.getDisplayName() + " | "
                            + student.getEmail() + " | "
                            + student.getBatch() + " | "
                            + (student.isActive() ? "Active" : "Inactive")
            );
        }
    }

    private void searchStudent() {

        int id = readInteger("Enter student ID: ");

        Student student = studentService.findStudentById(id);

        if (student == null) {
            throw new EntityNotFoundException(
                    "Student with ID " + id + " not found."
            );
        }

        System.out.println("\nStudent Details:");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getDisplayName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Batch: " + student.getBatch());
        System.out.println("Status: "
                + (student.isActive() ? "Active" : "Inactive"));
    }

    private void updateStudent() {

        int id = readInteger("Enter student ID to update: ");

        Student student = studentService.findStudentById(id);

        if (student == null) {
            throw new EntityNotFoundException(
                    "Student with ID " + id + " not found."
            );
        }

        System.out.print("New first name: ");
        String firstName = scanner.nextLine();

        System.out.print("New last name: ");
        String lastName = scanner.nextLine();

        System.out.print("New email: ");
        String email = scanner.nextLine();

        System.out.print("New batch: ");
        String batch = scanner.nextLine();

        studentService.updateStudent(
                id, firstName, lastName, email, batch
        );

        System.out.println("Student updated successfully!");
    }

    private void deactivateStudent() {

        int id = readInteger("Enter student ID to deactivate: ");

        Student student = studentService.findStudentById(id);

        if (student == null) {
            throw new EntityNotFoundException(
                    "Student with ID " + id + " not found."
            );
        }

        studentService.deactivateStudent(id);

        System.out.println("Student deactivated successfully!");
    }

    // =========================================
    // COURSE MANAGEMENT
    // =========================================

    private void courseMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== COURSE MANAGEMENT ==========");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Update Course");
            System.out.println("5. Deactivate Course");
            System.out.println("6. Back to Main Menu");

            int choice = readInteger("Enter your choice: ");

            try {
                switch (choice) {

                    case 1:
                        addCourse();
                        break;

                    case 2:
                        viewCourses();
                        break;

                    case 3:
                        searchCourse();
                        break;

                    case 4:
                        updateCourse();
                        break;

                    case 5:
                        deactivateCourse();
                        break;

                    case 6:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InvalidInputException |
                     EntityNotFoundException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addCourse() {

        System.out.println("\n--- ADD COURSE ---");

        System.out.print("Course name: ");
        String courseName = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        int duration = readInteger("Duration in weeks: ");

        Course course = courseService.addCourse(
                courseName, description, duration
        );

        System.out.println(
                "Course added successfully! ID: " + course.getId()
        );
    }

    private void viewCourses() {

        System.out.println("\n--- COURSE LIST ---");

        if (courseService.getAllCourses().isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courseService.getAllCourses()) {

            System.out.println(
                    course.getId() + " | "
                            + course.getCourseName() + " | "
                            + course.getDescription() + " | "
                            + course.getDurationInWeeks() + " weeks | "
                            + (course.isActive() ? "Active" : "Inactive")
            );
        }
    }

    private void searchCourse() {

        int id = readInteger("Enter course ID: ");

        Course course = courseService.findCourseById(id);

        if (course == null) {
            throw new EntityNotFoundException(
                    "Course with ID " + id + " not found."
            );
        }

        System.out.println("\nCourse Details:");
        System.out.println("ID: " + course.getId());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Duration: "
                + course.getDurationInWeeks() + " weeks");
        System.out.println("Status: "
                + (course.isActive() ? "Active" : "Inactive"));
    }

    private void updateCourse() {

        int id = readInteger("Enter course ID to update: ");

        Course course = courseService.findCourseById(id);

        if (course == null) {
            throw new EntityNotFoundException(
                    "Course with ID " + id + " not found."
            );
        }

        System.out.print("New course name: ");
        String courseName = scanner.nextLine();

        System.out.print("New description: ");
        String description = scanner.nextLine();

        int duration = readInteger("New duration in weeks: ");

        courseService.updateCourse(
                id, courseName, description, duration
        );

        System.out.println("Course updated successfully!");
    }

    private void deactivateCourse() {

        int id = readInteger("Enter course ID to deactivate: ");

        Course course = courseService.findCourseById(id);

        if (course == null) {
            throw new EntityNotFoundException(
                    "Course with ID " + id + " not found."
            );
        }

        courseService.deactivateCourse(id);

        System.out.println("Course deactivated successfully!");
    }

    // =========================================
    // ENROLLMENT MANAGEMENT
    // =========================================

    private void enrollmentMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n========== ENROLLMENT MANAGEMENT ==========");
            System.out.println("1. Enroll Student");
            System.out.println("2. View All Enrollments");
            System.out.println("3. Search Enrollment");
            System.out.println("4. View Student Enrollments");
            System.out.println("5. Cancel Enrollment");
            System.out.println("6. Back to Main Menu");

            int choice = readInteger("Enter your choice: ");

            try {
                switch (choice) {

                    case 1:
                        enrollStudent();
                        break;

                    case 2:
                        viewEnrollments();
                        break;

                    case 3:
                        searchEnrollment();
                        break;

                    case 4:
                        viewStudentEnrollments();
                        break;

                    case 5:
                        cancelEnrollment();
                        break;

                    case 6:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InvalidInputException |
                     EntityNotFoundException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void enrollStudent() {

        System.out.println("\n--- ENROLL STUDENT ---");

        int studentId = readInteger("Enter student ID: ");
        int courseId = readInteger("Enter course ID: ");

        Enrollment enrollment = enrollmentService.enrollStudent(
                studentId, courseId
        );

        System.out.println(
                "Enrollment successful! ID: " + enrollment.getId()
        );
    }

    private void viewEnrollments() {

        System.out.println("\n--- ENROLLMENT LIST ---");

        if (enrollmentService.getAllEnrollments().isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        for (Enrollment enrollment :
                enrollmentService.getAllEnrollments()) {

            Student student = studentService.findStudentById(
                    enrollment.getStudentId()
            );

            Course course = courseService.findCourseById(
                    enrollment.getCourseId()
            );

            System.out.println(
                    "Enrollment ID: " + enrollment.getId()
                            + " | Student: "
                            + (student != null
                            ? student.getDisplayName() : "Unknown")
                            + " | Course: "
                            + (course != null
                            ? course.getCourseName() : "Unknown")
                            + " | Date: " + enrollment.getEnrollmentDate()
                            + " | Status: " + enrollment.getStatus()
            );
        }
    }

    private void searchEnrollment() {

        int id = readInteger("Enter enrollment ID: ");

        Enrollment enrollment =
                enrollmentService.findEnrollmentById(id);

        if (enrollment == null) {
            throw new EntityNotFoundException(
                    "Enrollment with ID " + id + " not found."
            );
        }

        System.out.println("\nEnrollment Details:");
        System.out.println("Enrollment ID: " + enrollment.getId());
        System.out.println("Student ID: " + enrollment.getStudentId());
        System.out.println("Course ID: " + enrollment.getCourseId());
        System.out.println("Date: " + enrollment.getEnrollmentDate());
        System.out.println("Status: " + enrollment.getStatus());
    }

    private void viewStudentEnrollments() {

        int studentId = readInteger("Enter student ID: ");

        Student student = studentService.findStudentById(studentId);

        if (student == null) {
            throw new EntityNotFoundException(
                    "Student with ID " + studentId + " not found."
            );
        }

        System.out.println(
                "\nEnrollments for " + student.getDisplayName()
        );

        boolean found = false;

        for (Enrollment enrollment :
                enrollmentService.getEnrollmentsByStudentId(studentId)) {

            Course course = courseService.findCourseById(
                    enrollment.getCourseId()
            );

            System.out.println(
                    "Enrollment ID: " + enrollment.getId()
                            + " | Course: "
                            + (course != null
                            ? course.getCourseName() : "Unknown")
                            + " | Status: " + enrollment.getStatus()
            );

            found = true;
        }

        if (!found) {
            System.out.println("No enrollments found.");
        }
    }

    private void cancelEnrollment() {

        int id = readInteger("Enter enrollment ID to cancel: ");

        boolean cancelled =
                enrollmentService.cancelEnrollment(id);

        if (!cancelled) {
            throw new EntityNotFoundException(
                    "Enrollment with ID " + id + " not found."
            );
        }

        System.out.println("Enrollment cancelled successfully!");
    }

    // =========================================
    // INPUT HANDLING
    // =========================================

    private int readInteger(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }
}
