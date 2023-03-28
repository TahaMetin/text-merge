package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSimilarTexts {
    private String mergedText;
    MergeSimilarTexts ( List<String> textInputs ){

        List<String> words = new ArrayList<>(Arrays.asList(textInputs.get(0).split(" ")));
        StringBuilder result = new StringBuilder(String.join(" ", words));
        for (int i = 1; i < textInputs.size(); i++) {
            List<String> currentWords = new ArrayList<>(Arrays.asList(textInputs.get(i).split(" ")));
            currentWords.removeAll(words);
            words.addAll(currentWords);
            result.append(" ").append(currentWords.get(currentWords.size() - 1));
        }
        System.out.println(result);
        mergedText = result.toString();
    }
    public String getMergedText() {
        return mergedText;
    }
}
