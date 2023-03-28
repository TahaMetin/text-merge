package com.example.textdeneme.controller;

import com.example.textdeneme.backend.DatabaseConnection;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveTextController {

    @PostMapping("/save-text")
    public String handleRequest(@RequestParam("merged-text") String metin) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.InsertMergedText(metin);

        return metin;
    }
}