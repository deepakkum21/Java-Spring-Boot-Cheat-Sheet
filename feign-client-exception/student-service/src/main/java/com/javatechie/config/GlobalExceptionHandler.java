package com.javatechie.config;

import feign.FeignException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(FeignException.BadRequest.class)
//    public ResponseEntity<String> handleBadRequest(FeignException.BadRequest ex) {
//        return ResponseEntity.badRequest()
//                .body(ex.contentUTF8());
//    }
//
//
//    @ExceptionHandler(FeignException.InternalServerError.class)
//    public ResponseEntity<String> handleServerError(FeignException.InternalServerError ex) {
//        return ResponseEntity.internalServerError()
//                .body(ex.contentUTF8());
//    }
//
//    @ExceptionHandler(FeignException.ServiceUnavailable.class)
//    public ResponseEntity<String> handleServiceUnavailable(FeignException.ServiceUnavailable ex) {
//        return ResponseEntity.internalServerError()
//                .body(ex.contentUTF8());
//    }
//
//    @ExceptionHandler(RetryableException.class)
//    public ResponseEntity<String> handleRetryableException(RetryableException ex) {
//        return ResponseEntity.internalServerError()
//                .body(ex.contentUTF8());
//    }
//
//    @ExceptionHandler(FeignException.class)
//    public ResponseEntity<String> handleFeignException(FeignException ex) {
//        int status = ex.status();
//        String message = ex.contentUTF8();
//
//        return switch (status) {
//            case 400 -> ResponseEntity.badRequest().body(message);
//            case 500 -> ResponseEntity.internalServerError().body(message);
//            case 503 -> ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
//            default -> ResponseEntity.status(status).body("Unexpected error: " + message);
//        };
//
//    }
//
//
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(ex.getMessage());
    }


}
