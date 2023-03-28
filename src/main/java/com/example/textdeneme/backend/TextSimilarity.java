package com.example.textdeneme.backend;
import java.util.*;

public class TextSimilarity {

    private List<SimilarityData> similarityDataListC= new ArrayList<>();
    private List<SimilarityData> similarityDataListJ = new ArrayList<>();
    private List<SimilarityData> similarityDataListCWF3L = new ArrayList<>();

    TextSimilarity(List<String> textInputs) {
/*
        List<String> textInputs = new ArrayList<>();
        // exammple set 1
        textInputs.add("Ali eve gel");
        textInputs.add("eve gel sonra");
        textInputs.add("eve gel sonra çarşı");
        textInputs.add("çarşıya git");
*/
        // cosine similarity
        //System.out.println("\ncosine similarity : ");
        for (int i = 0; i < textInputs.size(); i++){
            SimilarityData maxSimilarity = new SimilarityData();
            maxSimilarity.setSimilarityScore(0.0);
            for (int j = 0; j < textInputs.size(); j++) {
                if (i == j) continue;

                Map<String, Integer> wordFreqMap1 = getWordFrequency(textInputs.get(i));
                Map<String, Integer> wordFreqMap2 = getWordFrequency(textInputs.get(j));

                double similarityScore = getCosineSimilarity(wordFreqMap1, wordFreqMap2);
                if(similarityScore > maxSimilarity.getSimilarityScore()){
                    maxSimilarity.setSimilarityScore(similarityScore);
                    maxSimilarity.setTextNo1(i);
                    maxSimilarity.setTextNo2(j);
                }
                //System.out.println("The similarity score between " + i +" and " +j +" is: " + similarityScore);
            }
            similarityDataListC.add(maxSimilarity);
        }

        // cosine similarity with first 3 letter
        //System.out.println("\ncosine similarity with first 3 letter : ");
        for (int i = 0; i < textInputs.size(); i++){
            SimilarityData maxSimilarity = new SimilarityData();
            maxSimilarity.setSimilarityScore(0.0);
            for (int j = 0; j < textInputs.size(); j++) {
                if (i == j) continue;

                Map<String, Integer> wordFreqMap1 = getWordFrequency(firstThreeLetters(textInputs.get(i)));
                Map<String, Integer> wordFreqMap2 = getWordFrequency(firstThreeLetters(textInputs.get(j)));

                double similarityScore = getCosineSimilarity(wordFreqMap1, wordFreqMap2);
                if(similarityScore > maxSimilarity.getSimilarityScore()){
                    maxSimilarity.setSimilarityScore(similarityScore);
                    maxSimilarity.setTextNo1(i);
                    maxSimilarity.setTextNo2(j);
                }
                //System.out.println("The similarity with first 3 letter score between  " + i +" and " +j +" is: " + similarityScore);
            }
            similarityDataListCWF3L.add(maxSimilarity);
        }

        // jaccard similarity in char level
        //System.out.println("\njaccard similarity in char level: ");
        for (int i = 0; i < textInputs.size(); i++) {
            SimilarityData maxSimilarity = new SimilarityData();
            maxSimilarity.setSimilarityScore(0.0);
            for (int j = 0; j < textInputs.size(); j++) {
                if (i == j) continue;

                Set<Character> charSet1 = getCharSet(textInputs.get(i));
                Set<Character> charSet2 = getCharSet(textInputs.get(j));

                double similarityScore = getJaccardSimilarity(charSet1, charSet2);
                if(similarityScore > maxSimilarity.getSimilarityScore()){
                    maxSimilarity.setSimilarityScore(similarityScore);
                    maxSimilarity.setTextNo1(i);
                    maxSimilarity.setTextNo2(j);
                }
                //System.out.println("The similarity score between " + i + " and " + j + " is: " + similarityScore);
            }
            similarityDataListJ.add(maxSimilarity);
        }
    }
    public boolean IsTextsSimilar(){
        //TODO max similarity datalarını kullanarak textlerin benzer olup olmadığını belirle

        boolean passC= false,passJ= false,passCWF3L= false;
        boolean highScorePassC= false,highScorePassJ= false,highScorePassCWF3L= false;
        /*
        System.out.println("----------------------------------------------------");

        for ( int i = 0; i< similarityDataListC.size(); i++) {
            System.out.println("-----------");
            System.out.println("C : " + similarityDataListC.get(i).getSimilarityScore());
            System.out.println("J : " + similarityDataListJ.get(i).getSimilarityScore());
            System.out.println("CWF3L : " + similarityDataListCWF3L.get(i).getSimilarityScore());
        }
        */
        double C = CalculateSimilarityRatio(similarityDataListC);
        double J = CalculateSimilarityRatio(similarityDataListJ);
        double CWF3L = CalculateSimilarityRatio(similarityDataListCWF3L);
        System.out.println("similarityRatio (C,J,CWF3L): " + C  +" "+ J + " " +CWF3L );
        double SimilaritRatioMean =( C + J + CWF3L )/3 ;
        if(0.35 < C) highScorePassC = true;
        if(0.65 < J) highScorePassJ = true;
        if(0.6 < CWF3L)  highScorePassCWF3L = true;

        if(0.35 < C) passC = true;
        if(0.5 < J) passJ = true;
        if(0.5 < CWF3L)  passCWF3L = true;

        if(0.41 < SimilaritRatioMean ) return true;
        if(highScorePassC || highScorePassJ || highScorePassCWF3L) return true;
        if((passJ && passCWF3L) || passC) return true;
        return false;
    }

    private double CalculateSimilarityRatio(List<SimilarityData> list){
        double sum = 0;
        for (SimilarityData l:
             list) {
            sum += l.getSimilarityScore();
        }
        return sum / list.size();
    }
public static Set<Character> getCharSet(String text) {
        Set<Character> charSet = new HashSet<>();

        for (int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);
        if (Character.isLetterOrDigit(c)) {
        charSet.add(c);
        }
        }

        return charSet;
        }

public static double getJaccardSimilarity(Set<Character> set1, Set<Character> set2) {
        Set<Character> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<Character> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
        }

    public static Map<String, Integer> getWordFrequency(String text) {
        Map<String, Integer> wordFreqMap = new HashMap<>();
        String[] words = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        for (String word : words) {
            if (wordFreqMap.containsKey(word)) {
                wordFreqMap.put(word, wordFreqMap.get(word) + 1);
            } else {
                wordFreqMap.put(word, 1);
            }
        }

        return wordFreqMap;
    }

    public static double getCosineSimilarity(Map<String, Integer> wordFreqMap1, Map<String, Integer> wordFreqMap2) {
        Set<String> uniqueWords = new HashSet<>();
        uniqueWords.addAll(wordFreqMap1.keySet());
        uniqueWords.addAll(wordFreqMap2.keySet());

        double dotProduct = 0;
        double magnitude1 = 0;
        double magnitude2 = 0;

        for (String word : uniqueWords) {
            int freq1 = wordFreqMap1.getOrDefault(word, 0);
            int freq2 = wordFreqMap2.getOrDefault(word, 0);
            dotProduct += freq1 * freq2;
            magnitude1 += freq1 * freq1;
            magnitude2 += freq2 * freq2;
        }

        return dotProduct / (Math.sqrt(magnitude1) * Math.sqrt(magnitude2));
    }

    private String firstThreeLetters(String input) {
        StringBuilder sb = new StringBuilder();
        String[] words = input.split(" ");
        for (String word : words) {
            if (word.length() >= 3) {
                sb.append(word.substring(0, 3));
            } else {
                sb.append(word);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}


