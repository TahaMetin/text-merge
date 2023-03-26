package org.example;
import java.util.*;

public class TextSimilarity {

    private List<SimilarityData> similarityDataListC,similarityDataListJ = new ArrayList<>();
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
        System.out.println("\ncosine similarity : ");
        for (int i = 0; i < textInputs.size(); i++){
            SimilarityData maxSimilarity = new SimilarityData();
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

        // jaccard similarity in char level
        System.out.println("\njaccard similarity in char level: ");
        for (int i = 0; i < textInputs.size(); i++) {
            SimilarityData maxSimilarity = new SimilarityData();
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
        return false;
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
}


