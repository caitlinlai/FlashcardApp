package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//testing methods for flashcard deck class
class FlashcardDeckTest {

    private FlashcardDeck testDeck;
    private FlashcardDeck testDeck2;
    private Flashcard f1;
    private Flashcard f2;
    private Flashcard f3;

    @BeforeEach
    void testDeckConstructor() {
        this.testDeck = new FlashcardDeck("deck1");
        this.testDeck2 = new FlashcardDeck("deck2");
        this.f1 = new Flashcard("Hatsune Miku", "Is love", "Priority");
        this.f2 = new Flashcard("Miku", "is life", "Non-Priority");
        this.f3 = new Flashcard("i have", "no life", "Priority");

        testDeck.addNewFlashcard(f1);
        testDeck.addNewFlashcard(f2);
        testDeck.addNewFlashcard(f3);

        testDeck2.addNewFlashcard(f2);
        testDeck2.addNewFlashcard(f2);
        testDeck2.addNewFlashcard(f2);

    }


    @Test

    void testAddNewFlashcard() {
        testDeck.addNewFlashcard(f1);
        assertEquals(4, testDeck.getTotalNumberOfFlashcards());

    }

    @Test
    void testAddMultipleFlashcards(){
        testDeck.addNewFlashcard(f1);
        testDeck.addNewFlashcard(f2);
        testDeck.addNewFlashcard(f3);
        assertEquals(6,testDeck.getTotalNumberOfFlashcards());

    }

    @Test
    void getFlashcardDefinition() {
        assertEquals("Hatsune Miku", testDeck.getFlashcardDefinition(0));
        assertEquals("Miku", testDeck.getFlashcardDefinition(1));
        assertEquals("i have", testDeck.getFlashcardDefinition(2));
    }


    @Test
    void testDeleteFlashcard(){
        testDeck.deleteFlashcard(f1);
        assertEquals(2, testDeck.getTotalNumberOfFlashcards());
    }

    @Test
    void testDeleteMultipleFlashcards() {
        testDeck.deleteFlashcard(f1);
        testDeck.deleteFlashcard(f2);
        testDeck.deleteFlashcard(f3);
        assertEquals(0, testDeck.getTotalNumberOfFlashcards());
    }

    @Test
    void testDeleteTheSame(){
        testDeck.deleteFlashcard(f1);
        testDeck.deleteFlashcard(f1);
        assertEquals(2, testDeck.getTotalNumberOfFlashcards());
    }

    @Test
    void testGetFlashcardAnswer(){
        assertEquals("Is love", testDeck.getFlashcardAnswer(0));
        assertEquals("is life", testDeck.getFlashcardAnswer(1));
        assertEquals("no life", testDeck.getFlashcardAnswer(2));
    }



    @Test
    void testPriorityDeck(){
        assertEquals(2, testDeck.getPriorityFlashcards().getTotalNumberOfFlashcards());
    }

    @Test

    void testEmptyPriorityDeck(){
        assertEquals(0, testDeck2.getPriorityFlashcards().getTotalNumberOfFlashcards());
    }

    @Test

    void testGetName() {
        assertEquals("deck1", testDeck.getName());
    }

    @Test

    void testChangeName() {
        testDeck.changeDeckName("newDeck");
        assertEquals("newDeck", testDeck.getName());
    }

    @Test
    void testGetStatus() {
        assertEquals("Non-Priority", testDeck.getFlashcardStatus(1));
    }






}
