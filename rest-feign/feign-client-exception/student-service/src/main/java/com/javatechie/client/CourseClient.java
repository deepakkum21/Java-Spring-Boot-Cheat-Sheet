package com.deepak.client;

import com.deepak.config.FeignClientConfig;
import com.deepak.dto.Course;
import com.deepak.dto.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "course-client", url = "${application.services.course.url}", configuration = FeignClientConfig.class)
public interface CourseClient {

    @GetMapping
    List<Course> courses();
    // create a proxy for the CourseClient
    // RestTemplate -> build the request
    // URL , GET , NO , List<COURSE>

    @GetMapping("/{id}")
    Course course(@PathVariable int id, @RequestHeader(name = "X-Request-Source") String sourceSystem);

    @PostMapping("/{id}/ratings")
    String submitRating(@PathVariable int id, @RequestBody Rating rating);

}