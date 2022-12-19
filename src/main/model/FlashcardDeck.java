package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


//represents a deck of flashcards with 0 or more flashcards in it.
public class FlashcardDeck implements Writable {
    private ArrayList<Flashcard> flashcardDeck;
    private String name;

    //EFFECTS: Constructs a new deck of flashcards
    public FlashcardDeck(String name) {
        this.name = name;
        this.flashcardDeck = new ArrayList<>();

    }

    //MODIFIES: this
    //EFFECTS: adds a new flashcard to a flashcard deck
    public void addNewFlashcard(Flashcard flashcard) {
        this.flashcardDeck.add(flashcard);
        EventLog.getInstance().logEvent(new Event("Added new card."));
    }

    public void deleteAllCards() {
        int index = flashcardDeck.size() - 1;
        while (index >= 0) {
            flashcardDeck.remove(index);
            index--;
        }
        EventLog.getInstance().logEvent(new Event("Deleted all cards from deck"));
    }

    //EFFECTS: returns the name of the flashcard deck
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: changes the name of the flashcard deck
    public void changeDeckName(String newName) {
        name = newName;
    }

    //REQUIRES: i >= 0
    //EFFECTS: returns the definition to a flashcard at the given index.
    public String getFlashcardDefinition(int i) {
        return this.flashcardDeck.get(i).getDefinition();
    }

    //REQUIRES: i >= 0
    //EFFECTS: returns the answer to a flashcard at the given index.
    public String getFlashcardAnswer(int i) {
        return this.flashcardDeck.get(i).getAnswer();
    }

    public String getFlashcardStatus(int i) {
        return this.flashcardDeck.get(i).getStatus();
    }



    //EFFECTS: return the size of a flashcard
    public int getTotalNumberOfFlashcards() {
        return this.flashcardDeck.size();
    }

    //REQUIRES: a non-empty flashcard deck and that specific flashcard
    //MODIFIES: this
    //EFFECTS: deletes a given flashcard from the flashcard deck
    public void deleteFlashcard(Flashcard flashcard) {
        if (flashcardDeck.contains(flashcard)) {
            flashcardDeck.remove(flashcard);
        }
    }

    //EFFECTS: produces new list of only priority flashcards
    public FlashcardDeck getPriorityFlashcards() {
        FlashcardDeck flashcardDeck = new FlashcardDeck("priority");
        for (Flashcard flashcard : this.flashcardDeck) {
            if (flashcard.getStatus().equals("Priority")) {
                flashcardDeck.addNewFlashcard(flashcard);
            }
        }
        return flashcardDeck;

    }

    //EFFECTS: returns the flashcard at index i in the deck
    public Flashcard getFlashcard(int i) {
        return this.flashcardDeck.get(i);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("flashcard", cardsToJson());
        return json;
    }

    // EFFECTS: returns things in this flashcard as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flashcard f : flashcardDeck) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }



}