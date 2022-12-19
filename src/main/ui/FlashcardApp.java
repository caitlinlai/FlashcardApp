
package ui;

import model.FlashcardDeck;
import model.Flashcard;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class FlashcardApp {
    private static final String STORE_DECK = "./data/deck.json";
    private FlashcardDeck newDeck;
    private Scanner input;
    private Flashcard newCard;
    private String status;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: constructs new flashcard deck and runs app.
    public FlashcardApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        newDeck = new FlashcardDeck("Default deck lol");
        jsonWriter = new JsonWriter(STORE_DECK);
        jsonReader = new JsonReader(STORE_DECK);
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runApp() {
        boolean again = true;
        String command = null;

        initialize();

        while (again) {
            menu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("Would you like to save your current cards?(y/n): ");
                if (input.next().equals("y")) {
                    saveDeck();
                } else {
                    System.out.println("Not saved. Goodbye!");
                    again = false;
                }
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: takes user inputs as commands
    private void processCommand(String command) {
        if (command.equals("a")) {
            createACard();
        } else if (command.equals("u")) {
            studyCards();
        } else if (command.equals("v")) {
            viewCards();
        } else if (command.equals("s")) {
            saveDeck();
        } else if (command.equals("l")) {
            loadDeck();
        } else if (command.equals("r")) {
            renameDeck();
        } else {
            System.out.println("Invalid input.");
        }
    }

    //EFFECTS: initializes variables
    private void initialize() {
        newDeck = new FlashcardDeck("new deck");
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    //EFFECTS: displays a menu of actions a user can do
    private void menu() {
        System.out.println("\n Would you like to study or add new cards?");
        System.out.println("\t a -> create and add a new card");
        System.out.println("\t u -> study cards");
        System.out.println("\t v -> view amount of cards");
        System.out.println("\t s -> save deck");
        System.out.println("\t l -> load deck");
        System.out.println("\t r -> rename current deck");
        System.out.println("\t q -> exit");

    }

    //MODIFIES: this
    //EFFECTS: creates a new flashcard and adds it to a deck
    private void createACard() {
        newCard = new Flashcard("definition", "answer", "Priority");

        System.out.print("Enter a new definition: ");
        input.nextLine();
        newCard.changeDefinition(input.nextLine());

        System.out.print("Enter an answer for this definition: ");
        newCard.changeAnswer(input.nextLine());

        System.out.print("Is this flashcard a priority?(y/n): ");
        if (input.next().equals("n")) {
            newCard.changeStatus();
        }

        System.out.print("\n Created new flashcard with:");
        System.out.print("\n Definition: " + newCard.getDefinition());
        System.out.print("\n Answer: " + newCard.getAnswer());
        System.out.print("\n Status: " + newCard.getStatus());
        System.out.println("\n");

        newDeck.addNewFlashcard(newCard);
    }

    // MODIFIES: this
    // EFFECTS: allows users to study cards in deck,
    //          if no cards in deck, tells user
    //          if there are cards, goes through all cards and shows the defintions and requests
    //          user input.
    private void studyCards() {
        int counter = newDeck.getTotalNumberOfFlashcards();
        int index = 0;
        if (newDeck.getTotalNumberOfFlashcards() == 0) {
            System.out.println("You have no cards!");
        } else {
            while (counter > 0) {
                System.out.println("Your definition is: " + newDeck.getFlashcardDefinition(index));
                System.out.print("Enter your answer here: ");
                String i = input.next();

                if (i.equals(newDeck.getFlashcardAnswer(index))) {
                    System.out.println("Correct answer!");
                } else {
                    System.out.println("Wrong answer, study more");
                }
                index++;
                counter--;
            }
        }

    }

    //EFFECTS: tells the user how many cards and priority cards are in the deck.
    private void viewCards() {
        System.out.println("\n You have " + newDeck.getTotalNumberOfFlashcards() + " cards in this deck");
        System.out.println("\n You have "
                + newDeck.getPriorityFlashcards().getTotalNumberOfFlashcards() + " priority cards in this deck");
    }

    // EFFECTS: saves the workroom to file
    private void saveDeck() {
        try {
            jsonWriter.open();
            jsonWriter.write(newDeck);
            jsonWriter.close();
            System.out.println("Saved " + newDeck.getName() + " to " + STORE_DECK);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + STORE_DECK);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadDeck() {
        try {
            newDeck = jsonReader.read();
            System.out.println("Loaded " + newDeck.getName() + " from " + STORE_DECK);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + STORE_DECK);
        }
    }

    //MODIFIES: this
    //EFFECTS: replaces name of flashcard deck
    private void renameDeck() {
        System.out.println("Your deck is currently named: " + newDeck.getName());
        System.out.println("What would you like to rename it as?: ");
        String newName = input.next();
        newDeck.changeDeckName(newName);
        System.out.println("Your deck is currently named: " + newDeck.getName());
    }





}
