package levy.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnglishDictionary {

    private Map<String, List<Definition>> dictionary;

    public EnglishDictionary() throws CsvValidationException, IOException {
        dictionary = new HashMap<>();

        // Load data from CSV file
        try (InputStream in = getClass().getResourceAsStream("/englishDictionary.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(in))) {

            String[] record;

            while ((record = reader.readNext()) != null) {
                if (record.length >= 2) {
                    String word = record[0].trim().toLowerCase();
                    String[] parts = record[1].split(",");
                    String partOfSpeech = parts[0].trim();
                    String definition = record[1].substring(record[1].indexOf(",") + 1).trim();

                    // Create a Definition object
                    Definition def = new Definition(partOfSpeech, definition);

                    // Add word and its definition(s) to the dictionary map
                    if (dictionary.containsKey(word)) {
                        dictionary.get(word).add(def);
                    } else {
                        List<Definition> definitions = new ArrayList<>();
                        definitions.add(def);
                        dictionary.put(word, definitions);
                    }
                }
            }
        }
    }

    /**
     * @param word to look up.
     * @return a List of definitions, or an empty list if the word doesn't exist.
     */
    public List<Definition> getDefinitions(String word) {
        // Convert word to lowercase for case-insensitive search
        word = word.trim().toLowerCase();
        return dictionary.getOrDefault(word, new ArrayList<>());
    }

    /**
     * Definition class to represent a word's definition with its part of speech.
     */
    public static class Definition {
        private String partOfSpeech;
        private String definition;

        public Definition(String partOfSpeech, String definition) {
            this.partOfSpeech = partOfSpeech;
            this.definition = definition;
        }

        public String getPartOfSpeech() {
            return partOfSpeech;
        }

        public String getDefinition() {
            return definition;
        }
    }
}

