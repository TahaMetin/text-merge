package com.example.textdeneme.backend;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TextMergeManager {
    public String MergeTexts(List<String> textInputs) {

        //TODO start time
        Instant start = Instant.now();

        //TODO save inputs to database
        DatabaseConnection dbConnection = new DatabaseConnection();
        String inputObjectID = dbConnection.InsertTextList(textInputs);


        //TODO tokenize inputs ( word by word or root of a word)
        List<String[]> tokenizedTexts = new ArrayList<>();
        for (String textInput : textInputs) {
            // delete punctions and split from spaces
            tokenizedTexts.add(textInput.replaceAll("[\\p{Punct}]","").split(" "));
        }

        //TODO compare texts

        TextSimilarity textSimilarity = new TextSimilarity(textInputs);


        //TODO send merged text and elapsed time


        if(textSimilarity.IsTextsSimilar()){
            MergeSimilarTexts mergeSimilarTexts = new MergeSimilarTexts(textInputs);
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println(timeElapsed);
            return mergeSimilarTexts.getMergedText();

        }else {
            // send texts not similar info to frontend
            return "metinler benzer değil";
        }



    }

}
