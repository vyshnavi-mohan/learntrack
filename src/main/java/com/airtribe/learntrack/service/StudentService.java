
package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;

public class StudentService {

    // Store students in memory
    private ArrayList<Student> students = new ArrayList<>();


    public Student addStudent(String firstName, String lastName,
                              String email, String batch) {

        InputValidator.validateRequiredText(firstName, "First name");
        InputValidator.validateRequiredText(lastName, "Last name");
        InputValidator.validateRequiredText(email, "Email");
        InputValidator.validateRequiredText(batch, "Batch");

        int studentId = IdGenerator.getNextStudentId();

        Student student = new Student(
                studentId, firstName, lastName, email, batch
        );

        students.add(student);
        return student;
    }

    public Student addStudent(String firstName, String lastName,
                              String batch) {

        InputValidator.validateRequiredText(firstName, "First name");
        InputValidator.validateRequiredText(lastName, "Last name");
        InputValidator.validateRequiredText(batch, "Batch");

        int studentId = IdGenerator.getNextStudentId();

        Student student = new Student(
                studentId, firstName, lastName, batch
        );

        students.add(student);
        return student;
    }

    // Get all students
    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Search student by ID
    public Student findStudentById(int studentId) {

        for (Student student : students) {

            if (student.getId() == studentId) {
                return student;
            }
        }

        return null;
    }

    // Update student details
    public boolean updateStudent(int studentId,
                                 String firstName,
                                 String lastName,
                                 String email,
                                 String batch) {

        Student student = findStudentById(studentId);

        if (student == null) {
            return false;
        }

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);

        return true;
    }

    // Deactivate student instead of deleting
    public boolean deactivateStudent(int studentId) {

        Student student = findStudentById(studentId);

        if (student == null) {
            return false;
        }

        student.setActive(false);

        return true;
    }

    // Remove student permanently
    public boolean removeStudent(int studentId) {

        Student student = findStudentById(studentId);

        if (student == null) {
            return false;
        }

        students.remove(student);

        return true;
    }
}
