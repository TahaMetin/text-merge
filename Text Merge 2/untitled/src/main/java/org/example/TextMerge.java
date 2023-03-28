package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
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
/*        textInputs.add("Beyaz ev");
        textInputs.add("evin karşısındaki park");
        textInputs.add("karşıdaki parkın salıncağında");
        textInputs.add("salıncakta sallanan çocuk");
*/
/*
        ArrayList<String> example1 = new ArrayList<String>();
        example1.add("Ayşe okula git");
        example1.add("okula git sonra");
        example1.add("okula git sonra kantinde buluşalım");
       example1.add("kantinden yemeğini al");

        ArrayList<String> example2 = new ArrayList<String>();
        example2.add("Tahir markete git");
        example2.add("marketten süt al");
        example2.add("sütü al eve gel");
        example2.add("eve gel sonra kahve içelim");

        ArrayList<String> example3 = new ArrayList<String>();
        example3.add("Hakan spor yap");
        example3.add("spor yap sonra duş al");
        example3.add("duş aldıktan sonra kahvaltı yap");
        example3.add("kahvaltıdan sonra kitap oku");

        ArrayList<String> example4 = new ArrayList<String>();
        example4.add("Fulya sinemaya git");
        example4.add("sinemadan sonra yemek yiyelim");
        example4.add("yemek yiyelim sonra barlarda takılalım");
        example4.add("barlardan eve yürüyerek dön");

        ArrayList<String> example5 = new ArrayList<String>();
        example5.add("Emre kütüphaneye git");
        example5.add("kütüphanede kitap seç");
        example5.add("kitapları ödünç al");
        example5.add("eve gelip kitap oku");
*/
/*
        ArrayList<String> examples = new ArrayList<String>();
        examples.add("Ayşe okula git");
        examples.add("okula git sonra");
        examples.add("okula git sonra kantinde buluşalım");
        examples.add("kantinden yemeğini al");
        examples.add("Tahir markete git");
        examples.add("marketten süt al");
        examples.add("sütü al eve gel");
        examples.add("eve gel sonra kahve içelim");
        examples.add("Hakan spor yap");
        examples.add("spor yap sonra duş al");
        examples.add("duş aldıktan sonra kahvaltı yap");
        examples.add("kahvaltıdan sonra kitap oku");
        examples.add("Fulya sinemaya git");
        examples.add("sinemadan sonra yemek yiyelim");
        examples.add("yemek sonrası barlarda takılalım");
        examples.add("barlardan eve yürüyerek dön");
        examples.add("Emre kütüphaneye git");
        examples.add("kütüphanede kitap seç");
        examples.add("kitapları ödünç al");
        examples.add("eve gelip kitap oku");

        Collections.shuffle(examples);

        ArrayList<String> example1 = new ArrayList<String>(examples.subList(0, 4));
        ArrayList<String> example2 = new ArrayList<String>(examples.subList(4, 8));
        ArrayList<String> example3 = new ArrayList<String>(examples.subList(8, 12));
        ArrayList<String> example4 = new ArrayList<String>(examples.subList(12, 16));
        ArrayList<String> example5 = new ArrayList<String>(examples.subList(16, 20));

        int i =0;
        for (String s :
                examples) {
            if(i++ % 4 == 0) System.out.println();
            System.out.println(s);
        }
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
        /*
        TextSimilarity textSimilarity1 = new TextSimilarity(example1);
        TextSimilarity textSimilarity2 = new TextSimilarity(example2);
        TextSimilarity textSimilarity3 = new TextSimilarity(example3);
        TextSimilarity textSimilarity4 = new TextSimilarity(example4);
        TextSimilarity textSimilarity5 = new TextSimilarity(example5);
*/

/*
        if( textSimilarity1.IsTextsSimilar()) System.out.println("1 true||||||||||||||||||||||||||||||||||||||||||");
        if(textSimilarity2.IsTextsSimilar() ) System.out.println("2 true||||||||||||||||||||||||||||||||||||||||||");
        if(textSimilarity3.IsTextsSimilar() ) System.out.println("3 true||||||||||||||||||||||||||||||||||||||||||");
        if(textSimilarity4.IsTextsSimilar()) System.out.println("4 true||||||||||||||||||||||||||||||||||||||||||");
        if(textSimilarity5.IsTextsSimilar()) System.out.println("5 true||||||||||||||||||||||||||||||||||||||||||");
*/

        TextSimilarity textSimilarity = new TextSimilarity(textInputs);


        //TODO if texts similar merge them and save merge to database
        if(textSimilarity.IsTextsSimilar()){
            MergeSimilarTexts mergeSimilarTexts = new MergeSimilarTexts(textInputs);
        }else {
            // send texts not similar info to frontend
        }

        //TODO send merged text and elapsed time
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println(timeElapsed);
    }

}
