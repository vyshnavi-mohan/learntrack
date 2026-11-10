
package com.airtribe.learntrack.util;

public class IdGenerator {

    // Static counters shared across the application
    private static int studentIdCounter = 100;
    private static int courseIdCounter = 200;
    private static int enrollmentIdCounter = 300;

    // Generate the next Student ID
    public static int getNextStudentId() {
        return ++studentIdCounter;
    }

    // Generate the next Course ID
    public static int getNextCourseId() {
        return ++courseIdCounter;
    }

    // Generate the next Enrollment ID
    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }
}
