package com.example.textdeneme.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/*@RestController
public class AddTextController {
    @PostMapping("/add-text")
    public String handleRequest(@RequestParam("text-1") String text1,
                                @RequestParam("text-2") String text2,
                                @RequestParam(value = "text-optional", required = false) String textoptional) {

        String result = text1 + " " + text2;
        if (textoptional != null) {
            result = result + " " + textoptional;
        }

        return result;
    }
}*/

@RestController
public class AddTextController {
    private String previousOptionalText = "";
    @PostMapping("/add-text")
    public String handleRequest(@RequestParam("text-1") String text1,
                                @RequestParam("text-2") String text2,
                                @RequestParam(value = "text-optional", required = false) String textoptional) {

        if (textoptional != null) {
            previousOptionalText = previousOptionalText + " - " + textoptional;
        }


        String result = text1 + " - " + text2 + previousOptionalText;
        return result;
    }
}
