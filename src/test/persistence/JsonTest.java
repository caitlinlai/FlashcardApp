package persistence;

import model.Flashcard;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkCard(String definition, String answer, String status, Flashcard f) {
        assertEquals(definition, f.getDefinition());
        assertEquals(answer, f.getAnswer());
        assertEquals(status, f.getStatus());

    }
}
