package persistence;

import model.FlashcardDeck;
import model.Flashcard;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testInvalidFile() {
        try {
            FlashcardDeck newDeck = new FlashcardDeck("myFirstDeck");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            writer.write(newDeck);
            writer.close();
            fail("IOException was expected");
        } catch (IOException e) {
            //passs
        }
    }

    @Test
    void testWriterEmptyDeck() {
        try {
            FlashcardDeck newDeck = new FlashcardDeck("myFirstDeck");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDeck.json");
            writer.open();
            writer.write(newDeck);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDeck.json");
            newDeck = reader.read();
            assertEquals("myFirstDeck", newDeck.getName());
            assertEquals(0, newDeck.getTotalNumberOfFlashcards());
        } catch (IOException e) {
            fail("Exception");
        }
    }

    @Test
    void testWriterNonEmptyDeck() {
        try {
            FlashcardDeck newDeck = new FlashcardDeck("myFirstDeck");
            newDeck.addNewFlashcard(new Flashcard("i am", "so tired", "Priority"));
            newDeck.addNewFlashcard(new Flashcard("pls", "let me out", "Non-Priority"));
            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyDeck.json");
            writer.open();
            writer.write(newDeck);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyDeck.json");
            newDeck = reader.read();
            assertEquals("myFirstDeck", newDeck.getName());
            assertEquals(2, newDeck.getTotalNumberOfFlashcards());
            checkCard("i am","so tired","Priority", newDeck.getFlashcard(0));
            checkCard("pls","let me out", "Non-Priority", newDeck.getFlashcard(1));
        } catch (IOException e) {
            fail("Exception");
        }
    }


}