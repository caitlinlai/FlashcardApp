package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//testing methods for flashcard class
class FlashcardTest {

    protected Flashcard flashcard1;
    protected Flashcard flashcard2;

    @BeforeEach
    void testConstructor() {
        this.flashcard1 = new Flashcard("Amino Acid",
                "Molecules that combine to form proteins",
                "Priority");
        this.flashcard2 = new Flashcard("Mitochondria",
                "The powerhouse of the cell",
                "Non-Priority");
    }

    @Test
    void testChangeDefinition(){
        flashcard1.changeDefinition("Protein");
        assertEquals("Protein", flashcard1.getDefinition());
    }

    @Test
    void testChangeSameDefinitionMultiple(){
        flashcard1.changeDefinition("Protein");
        assertEquals("Protein", flashcard1.getDefinition());
        flashcard1.changeDefinition("Beef");
        assertEquals("Beef", flashcard1.getDefinition());
    }

    @Test
    void testChangeAnswer(){
        flashcard1.changeAnswer("Large biomolecules and macromolecules made up of amino acids");
        assertEquals("Large biomolecules and macromolecules made up of amino acids", flashcard1.getAnswer());
        flashcard2.changeAnswer("not the powerhouse");
        assertEquals("not the powerhouse", flashcard2.getAnswer());
    }

    @Test
    void testChangeStatus(){
        flashcard1.changeStatus();
        assertEquals("Non-Priority", flashcard1.getStatus());
        flashcard2.changeStatus();
        assertEquals("Priority", flashcard2.getStatus());
    }

}
