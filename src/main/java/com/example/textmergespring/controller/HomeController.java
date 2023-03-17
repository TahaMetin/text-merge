package com.example.textmergespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HomeController {


    /*@GetMapping("/")
    public String home() {
        return "index.html";
    }*/



    /*@PostMapping("/")
    public String processForm() throws IOException {
        // Burada yapılacak işlemleri yazabilirsiniz.
        // Örneğin, veritabanına bir kayıt ekleme işlemi yapabilirsiniz.



        return "index"; // İşlem tamamlandıktan sonra yönlendirilecek sayfanın adı.
    }*/

    @PostMapping("/")
    public String hello(@RequestParam("merged-text") String input) {
        // "input" adı verilen metin kutusuna "Hello World!" yazar
        input = "Hello World!";
        return input;
    }



}


