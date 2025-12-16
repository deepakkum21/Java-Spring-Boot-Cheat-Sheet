package com.deepak.exception;

public class CourseServiceException extends RuntimeException {
    public CourseServiceException(String message) {
        super(message);
    }
}
