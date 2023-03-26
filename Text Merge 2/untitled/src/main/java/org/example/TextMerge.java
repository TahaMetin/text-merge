package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TextMerge {
    public static void main(String[] args) {

        //TODO start time
        Instant start = Instant.now();

        //TODO take inputs
        List<String> textInputs = new ArrayList<>();
        // exammple set 1
        textInputs.add("Ali eve gel");
        textInputs.add("eve gel sonra");
        textInputs.add("eve gel sonra çarşı");
        textInputs.add("çarşıya git");

        // exammple set 2
        /*
        textInputs.add("Beyaz ev");
        textInputs.add("evin karşısındaki park");
        textInputs.add("karşıdaki parkın salıncağında");
        textInputs.add("salıncakta sallanan çocuk");
        */
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
        //TODO if texts similar merge them and save merge to database
        if( textSimilarity.IsTextsSimilar()){

        }
        //TODO send merged text and elapsed time
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println(timeElapsed);
    }

}
