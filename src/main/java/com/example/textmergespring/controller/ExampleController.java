package com.example.textmergespring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExampleController {

    @PostMapping("/")
    public void handlePostRequest(@RequestBody ExampleData data) {
        // Process the data as needed
        System.out.println("Received data: " + data);
    }
}

