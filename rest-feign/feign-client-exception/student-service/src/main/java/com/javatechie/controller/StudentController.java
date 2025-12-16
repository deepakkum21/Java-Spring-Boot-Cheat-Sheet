package com.deepak.controller;

import com.deepak.dto.Course;
import com.deepak.dto.Rating;
import com.deepak.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/search/{courseId}")
    public Course search(@PathVariable int courseId, @RequestParam("sourceSystem") String sourceSystem) {
        // hardcode // pathVariable // requestParam
        return studentService.searchCourse(courseId, sourceSystem);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return studentService.getAllCourses();
    }

    @PostMapping("/courses/{courseId}/ratings")
    public String submitRating(@PathVariable int courseId, Rating rating) {
        return studentService.submitRating(courseId, rating);
    }
}