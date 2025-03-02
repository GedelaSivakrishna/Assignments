package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fallback")
public class FallbackController {

    @GetMapping("employee")
    public ResponseEntity<String> employeeServiceFallbackHandler() {
        String response = "Employee service is unavailable. Please try again after sometime";
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("performance")
    public ResponseEntity<String> performanceServiceFallbackHandler() {
        String response = "Performance service is unavailable. Please try again after sometime";
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }


}
