
package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;

public class CourseService {

    private ArrayList<Course> courses = new ArrayList<>();


    public Course addCourse(String courseName,
                            String description,
                            int durationInWeeks) {

        InputValidator.validateRequiredText(courseName, "Course name");
        InputValidator.validateRequiredText(description, "Description");
        InputValidator.validatePositiveNumber(durationInWeeks, "Duration");

        int courseId = IdGenerator.getNextCourseId();

        Course course = new Course(
                courseId,
                courseName,
                description,
                durationInWeeks
        );

        courses.add(course);
        return course;
    }


    // Get all courses
    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    // Find course using ID
    public Course findCourseById(int courseId) {

        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }

        return null;
    }

    // Update course details
    public boolean updateCourse(int courseId,
                                String courseName,
                                String description,
                                int durationInWeeks) {

        Course course = findCourseById(courseId);

        if (course == null) {
            return false;
        }

        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(durationInWeeks);

        return true;
    }

    // Deactivate course
    public boolean deactivateCourse(int courseId) {

        Course course = findCourseById(courseId);

        if (course == null) {
            return false;
        }

        course.setActive(false);
        return true;
    }

    // Permanently remove course
    public boolean removeCourse(int courseId) {

        Course course = findCourseById(courseId);

        if (course == null) {
            return false;
        }

        courses.remove(course);
        return true;
    }
}
