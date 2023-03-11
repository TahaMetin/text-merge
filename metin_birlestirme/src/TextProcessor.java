import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.CosineSimilarity;


public class TextProcessor {

    public static void main(String[] args) {
        String text1 = "Bu bir örnek metindir.";
        String text2 = "Bu da bir örnek metindir.";

        // Metinleri tokenize etme ve stop words'leri temizleme
        List<String> tokens1 = Arrays.asList(text1.split("\\s+"));
        List<String> tokens2 = Arrays.asList(text2.split("\\s+"));
        List<String> stopWords = Arrays.asList("ve", "ya", "ama", "gibi", "da", "bir");
        tokens1 = tokens1.stream().filter(token -> !stopWords.contains(token)).collect(Collectors.toList());
        tokens2 = tokens2.stream().filter(token -> !stopWords.contains(token)).collect(Collectors.toList());

        Map<CharSequence, Integer> map1 = convertListToMap(tokens1);
        Map<CharSequence, Integer> map2 = convertListToMap(tokens2);

        // Metinler arasındaki benzerliği hesaplama
        double similarity = new CosineSimilarity().cosineSimilarity(map1, map2);

        if (similarity > 0.8) {
            // Metinleri birleştirme
            List<String> tokens = new ArrayList<String>();
            tokens.addAll(tokens1);
            tokens.addAll(tokens2);

            // Kelime frekanslarını hesaplama ve sık kullanılan kelimeleri seçme
            String[] words = tokens.toArray(new String[tokens.size()]);
            Map<String, Integer> freqs = new HashMap<String, Integer>();
            for (String word : words) {
                freqs.put(word, freqs.getOrDefault(word, 0) + 1);
            }
            String[] commonWords = freqs.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10)
                    .map(Map.Entry::getKey)
                    .toArray(String[]::new);

            // Yeni metni oluşturma
            String newText = StringUtils.join(commonWords, " ");
            System.out.println(newText);
        }
    }

    public static Map<CharSequence, Integer> convertListToMap(List<String> list) {
        Map<CharSequence, Integer> map = list.stream()
                .collect(Collectors.toMap(s -> s, s -> s.length()));
        return map;
    }
}