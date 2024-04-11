package levy.dictionary;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnglishDictionaryTest {

    @Test
    public void testGetDefinition() {
        try {
            // given
            EnglishDictionary dictionary = new EnglishDictionary();

            // when
            List<String> definitionsApple = dictionary.getDefinitions("apple");
            List<String> definitionsBanana = dictionary.getDefinitions("banana");
            List<String> definitionsCar = dictionary.getDefinition("car");
            List<String> definitionsNonExistent = dictionary.getDefinition("nonexistent");

            // then
            assertEquals(1, definitionsApple.size());
            assertEquals("a round fruit with red or green skin and white flesh", definitionsApple.get(0));

            assertEquals(1, definitionsBanana.size());
            assertEquals("a long curved fruit with a yellow skin", definitionsBanana.get(0));

            assertEquals(1, definitionsCar.size());
            assertEquals("a four-wheeled motor vehicle used for transportation", definitionsCar.get(0));

            assertEquals(0, definitionsNonExistent.size());
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}


