package persistence;

import model.Flashcard;
import model.FlashcardDeck;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNoFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FlashcardDeck newDeck = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDeck.json");
        try {
            FlashcardDeck newDeck = reader.read();
            assertEquals("myFirstDeck", newDeck.getName());
            assertEquals(0, newDeck.getTotalNumberOfFlashcards());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNonEmptyDeck() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyDeck.json");
        try {
            FlashcardDeck newDeck = reader.read();
            assertEquals("myFirstDeck", newDeck.getName());;
            assertEquals(2,newDeck.getTotalNumberOfFlashcards());
            checkCard("i am","so tired", "Priority", newDeck.getFlashcard(0));
            checkCard("can i go", "play games","Non-Priority", newDeck.getFlashcard(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
