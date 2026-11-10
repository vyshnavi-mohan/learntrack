
package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;

public class EnrollmentService {

    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    private StudentService studentService;
    private CourseService courseService;

    // Constructor injection
    public EnrollmentService(StudentService studentService,
                             CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // Enroll a student into a course

    public Enrollment enrollStudent(int studentId, int courseId) {

        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (student == null) {
            throw new EntityNotFoundException(
                    "Student with ID " + studentId + " not found."
            );
        }

        if (!student.isActive()) {
            throw new InvalidInputException(
                    "Cannot enroll an inactive student."
            );
        }

        if (course == null) {
            throw new EntityNotFoundException(
                    "Course with ID " + courseId + " not found."
            );
        }

        if (!course.isActive()) {
            throw new InvalidInputException(
                    "Cannot enroll in an inactive course."
            );
        }

        int enrollmentId = IdGenerator.getNextEnrollmentId();

        Enrollment enrollment = new Enrollment(
                enrollmentId, studentId, courseId
        );

        enrollments.add(enrollment);

        return enrollment;
    }

    // Get all enrollments
    public ArrayList<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

    // Find enrollment by ID
    public Enrollment findEnrollmentById(int enrollmentId) {

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == enrollmentId) {
                return enrollment;
            }
        }

        return null;
    }

    // Cancel an enrollment
    public boolean cancelEnrollment(int enrollmentId) {

        Enrollment enrollment = findEnrollmentById(enrollmentId);

        if (enrollment == null) {
            return false;
        }

        enrollment.setStatus("CANCELLED");

        return true;
    }

    // Get enrollments for a particular student
    public ArrayList<Enrollment> getEnrollmentsByStudentId(int studentId) {

        ArrayList<Enrollment> studentEnrollments = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }

        return studentEnrollments;
    }
}
