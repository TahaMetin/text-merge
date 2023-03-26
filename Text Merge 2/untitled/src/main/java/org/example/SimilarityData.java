package org.example;

public class SimilarityData {
    private int textNo1;
    private int textNo2;
    private Double similarityScore;

    public SimilarityData(int textNo1, int textNo2, double similarityScore) {
        this.textNo1 = textNo1;
        this.textNo2 = textNo2;
        this.similarityScore = similarityScore;
    }
    public SimilarityData() {
    }
    public int getTextNo1() {
        return textNo1;
    }

    public void setTextNo1(int textNo1) {
        this.textNo1 = textNo1;
    }

    public int getTextNo2() {
        return textNo2;
    }

    public void setTextNo2(int textNo2) {
        this.textNo2 = textNo2;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }
}
