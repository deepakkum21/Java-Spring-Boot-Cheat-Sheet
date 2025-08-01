package com.javatechie.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.dto.Course;
import com.javatechie.dto.Rating;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private  List<Course> COURSES = new ArrayList<>();

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("courses.json").getInputStream()) {
            Course[] courses = mapper.readValue(is, Course[].class);
            COURSES = new ArrayList<>(Arrays.asList(courses));
        } catch (IOException e) {
            e.printStackTrace(); // In real use, log it properly
        }
    }


    public Course getCourseById(int id) {
        return COURSES.stream()
                .filter(course -> course.getId() == id)
                .findFirst().orElseThrow(()-> new RuntimeException("Course not found with id: " + id));
    }

    public List<Course> getAllCourses() {
        return COURSES;
    }

    public boolean addRating(int courseId, Rating rating) {
        Course course = getCourseById(courseId);
        if (course != null) {
            course.getRatings().add(rating);
            return true;
        }
        return false;
    }

}