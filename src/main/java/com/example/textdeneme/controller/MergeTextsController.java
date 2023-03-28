package com.example.textdeneme.controller;

import com.example.textdeneme.backend.TextMergeManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class MergeTextsController {
     @PostMapping("/merge-text")
    public String handleRequest(@RequestParam("result") String input) throws IOException {
         long startTime = System.currentTimeMillis();

         // burada input değerini kullanarak yapılması gereken işlemler yapılır

         List<String> list = Arrays.asList(input.split(" - "));

         TextMergeManager textMergeManager = new TextMergeManager();

         long endTime = System.currentTimeMillis();
         long elapsedTime = endTime - startTime;
         return textMergeManager.MergeTexts(list);
    }
}
