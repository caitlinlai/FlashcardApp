package persistence;

import model.Event;
import model.EventLog;
import model.Flashcard;
import model.FlashcardDeck;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a reader to read flashcards from JSON data stored in the file
public class JsonReader {
    String source;

    // EFFECTS: constructs a new reader
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads file and returns it
    // throws IOException when errors occur
    public FlashcardDeck read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDeck(jsonObject);

    }

    //EFFECTS: reads file as string and returns as string
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses flashcard deck from JSON object and returns
    private FlashcardDeck parseDeck(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        FlashcardDeck fd = new FlashcardDeck(name);
        addCards(fd, jsonObject);
        EventLog.getInstance().logEvent(new Event("Loaded cards"));
        return fd;
    }

    // MODIFIES: deck
    // EFFECTS: parses flashcards from JSON object and adds them to the deck
    private void addCards(FlashcardDeck deck, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flashcard");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(deck, nextCard);
        }
    }

    // MODIFIES: deck
    // EFFECTS: parses flashcard from JSON object and adds it to workroom
    private void addCard(FlashcardDeck deck, JSONObject jsonObject) {
        String definition = jsonObject.getString("definition");
        String answer = jsonObject.getString("answer");
        String status = jsonObject.getString("status");
        Flashcard flashcard = new Flashcard(definition, answer, status);
        deck.addNewFlashcard(flashcard);
    }
}