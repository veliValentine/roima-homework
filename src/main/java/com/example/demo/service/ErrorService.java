package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    public ResponseEntity<String> http400(String message) {
        return errorStatus(message, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> http500(String message) {
        return errorStatus(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> errorStatus(String message, HttpStatus status){
        return new ResponseEntity<>("error: " + message, status);
    }
}
