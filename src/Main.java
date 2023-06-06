import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, String> wordMap = new TreeMap<>();

        String filePath = "resources/data/slang.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length >= 2) {
                    String word = parts[0];
                    String meaning = parts[1];
                    wordMap.put(word, meaning);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search key: ");
        String searchKey = scanner.nextLine();
        scanner.close();

        // Perform a relative search
        NavigableMap<String, String> result = wordMap.subMap(searchKey, true, searchKey + Character.MAX_VALUE, true);

        System.out.println("Search results:");
        for (String word : result.keySet()) {
            System.out.println("Word: " + word + ", Meaning: " + result.get(word));
        }
    }
}