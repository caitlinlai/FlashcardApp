package model;

import org.json.JSONObject;
import persistence.Writable;

//represents a flashcard with a definition and answer
public class Flashcard implements Writable {

    private String definition;
    private String answer;
    private String status;

    //EFFECTS: Constructs a new flash card with an ID, definition and answer
    public Flashcard(String definition, String answer, String status) {
        this.definition = definition;
        this.answer = answer;
        this.status = status;
    }

    //EFFECTS: returns definition
    public String getDefinition() {
        return definition;
    }

    //EFFECTS: returns answer
    public String getAnswer() {
        return answer;
    }

    //MODIFIES: this
    //EFFECTS: replaces current definition with a new definition
    public void changeDefinition(String newDef) {
        this.definition = newDef;
    }

    //MODIFIES: this
    //EFFECTS: replaces current answer with a new answer
    public void changeAnswer(String newAns) {
        this.answer = newAns;
    }

    //EFFECTS: returns status of flashcard
    public String getStatus() {
        return status;
    }

    //REQUIRES: status to be either priority or non-priority
    //MODIFIES: this
    //EFFECTS: switches status of flashcard
    public void changeStatus() {
        if (status.equals("Priority")) {
            status = "Non-Priority";
        } else {
            status = "Priority";
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("definition", definition);
        json.put("answer", answer);
        json.put("status", status);
        return json;
    }

}
