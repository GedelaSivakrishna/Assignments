import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileHandling {

    public static boolean allVowelsPresent(String str) {
        char[] arr = {'a', 'e', 'i', 'o', 'u'};
        str = str.toLowerCase();
        for(char ch : arr) {
            if(!str.contains(String.valueOf(ch)))
                return false;
        }
        return true;
    }

    public static boolean noVowelsPresent(String str) {
        str = str.toLowerCase();
        if(str.contains(String.valueOf('a')) || str.contains(String.valueOf('e')) || str.contains(String.valueOf('i'))
            || str.contains(String.valueOf('o')) || str.contains(String.valueOf('u')))
            return false;
        return true;
    }
    public static void main(String[] args) {

        // Step 1 - Take the directory / file path input
        System.out.print("Enter File path: ");
        Scanner sc = new Scanner(System.in);
        String filepath = sc.next();
        filepath = filepath.replace("\\", "\\\\");

        /* Step 2 - Analyze the file content 
        *      1) Number of lines in the file
        *      2) Count the number of words in the file.
        *      3) Count the total characters in the file (Excluding Space)
        *      4) Show the frequency of each word excluding articles (a, an, the) and 
        *         auxiliary verbs (is, am, are, was, were, have, had, do, did, does) 
        */

            int line_count = 0;
            int word_count = 0;
            int character_count = 0;
            int auxiliary_verbs_count = 0;
            Set<String> vowelWords = new HashSet<>();
            Set<String> noVowelWords = new HashSet<>();
            LinkedHashMap<String, Integer> sortedWordMap = new LinkedHashMap<>();


        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            String line;
            Map<String, Integer> wordmap = new HashMap<>();
            Set<String> set = new HashSet<>();
            set.addAll(Arrays.asList("a", "an", "the", "is", "am", "was", "were", "have", "had", "be", "can", "could"
            , "do", "does", "did", "may", "might", "must", "should", "shall", "will", "would"));
            while((line = bufferedReader.readLine()) != null) {
                ++line_count;
                line = line.replaceAll("[,.]", "");
                String[] words = line.split("\\s+");
                for(String word : words) {
                    character_count += word.length();
                    if(word.contains("'")) {
                        int idx = word.indexOf('\'');
                        character_count -= 1;
                        word = word.substring(0, idx);
                    }
                    // Add all vowel words to the set
                    if(allVowelsPresent(word)) {
                        vowelWords.add(word);
                    }
                    // Add all words without any vowels
                    if(noVowelsPresent(word)) {
                        noVowelWords.add(word);
                    }
                    String temp = word; // Storing the word in temp variable because 
                    // variables which are used in lamda expression shouldn't be modified after being assigned..
                    if(set.stream().anyMatch(w -> w.equalsIgnoreCase(temp))) {
                        auxiliary_verbs_count++;
                    }
                    else {
                        wordmap.put(word, wordmap.getOrDefault(word, 0) + 1);
                    }
                }
                word_count += words.length;
            }
            // Sort the words based on their occurence
            List<Map.Entry<String, Integer>> list = new ArrayList<>(wordmap.entrySet());

            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            for(Map.Entry<String, Integer> entry : list) {
                sortedWordMap.put(entry.getKey(), entry.getValue());
            }

            // System.out.println("Total lines = " + line_count);
            // System.out.println("Total words = " + word_count);
            // System.out.println("Total characters = " + character_count);
            // System.out.println("Total auxiliary verb words = " + auxiliary_verbs_count);
            // sortedWordMap.forEach((key, value) -> System.out.println(key + " " + value));
            // vowelWords.forEach(w -> System.out.print(w + " "));
            //     noVowelWords.forEach(w -> System.out.println(w));
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Path not found, please enter correct value");
        }
      
        // Step 3 - Write the insights to the output file
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
            bufferedWriter.write("Total lines = " + line_count);
            bufferedWriter.write("\nTotal Words = " + word_count);
            bufferedWriter.write("\nTotal Characters = " + character_count);
            bufferedWriter.write("\nTotal Auxiliary verbs = " + auxiliary_verbs_count);
            bufferedWriter.write("\n-------------------------------------------------------------------------");
            bufferedWriter.write("\n                 Frequency of the words in the document");
            for(String key : sortedWordMap.keySet()) {
                int freq = sortedWordMap.get(key);
               bufferedWriter.write("\n" + key + " -> " + freq);
            }
            bufferedWriter.write("\n--------------------------------------------------------------------------");
            bufferedWriter.write("\n                Words with all vowels present\n");
            for(String w : vowelWords) {
                bufferedWriter.write(w + ", ");
            }
            bufferedWriter.write("\n--------------------------------------------------------------------------");
            bufferedWriter.write("\n                Words with no vowels present\n");
            for(String w : noVowelWords) {
                bufferedWriter.write(w + ", ");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
    
}
