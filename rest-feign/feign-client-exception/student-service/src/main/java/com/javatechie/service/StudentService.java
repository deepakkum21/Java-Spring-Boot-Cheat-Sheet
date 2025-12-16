package com.deepak.service;

import com.deepak.client.CourseClient;
import com.deepak.dto.Course;
import com.deepak.dto.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    // @Autowired
    // private CourseRestClientService courseClient;

    @Autowired
    private CourseClient courseClient;

    public Course searchCourse(int courseId, String sourceSystem) {
        return courseClient.course(courseId, sourceSystem);
    }

    public List<Course> getAllCourses() {
        return courseClient.courses();
    }

    public String submitRating(int courseId, Rating rating) {
        return courseClient.submitRating(courseId, rating);
    }
}