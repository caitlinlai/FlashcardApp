package ui;

import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//the gui of the app
public class GUI {
    private static final String JSON_STORE = "./data/deck.json";
    private FlashcardDeck newDeck;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame frame;
    private Flashcard newCard;
    ImageIcon i6 = new ImageIcon("/Users/caitlinlai/IdeaProjects/project_a2v5q/icons/exit.png");
    ImageIcon i7 = new ImageIcon("/Users/caitlinlai/IdeaProjects/project_a2v5q/icons/delete.png");
    ImageIcon i1 = new ImageIcon("/Users/caitlinlai/IdeaProjects/project_a2v5q/icons/add.png");
    ImageIcon i2 = new ImageIcon("/Users/caitlinlai/IdeaProjects/project_a2v5q/icons/view.png");
    ImageIcon i5 = new ImageIcon("/Users/caitlinlai/IdeaProjects/project_a2v5q/icons/priority.png");
    ImageIcon i3 = new ImageIcon("/Users/caitlinlai/IdeaProjects/project_a2v5q/icons/save.png");
    ImageIcon i4 = new ImageIcon("/Users/caitlinlai/IdeaProjects/project_a2v5q/icons/load.png");



    //constructs the gui.
    public GUI() {
        JFrame frame;

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        newDeck = new FlashcardDeck("default deck");

        FlashcardDeck f = new FlashcardDeck("default deck");
        frame = new JFrame();

        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        panel.setLayout(new GridLayout(4, 2));

        buttons(panel);

        createFrame(frame, panel);
    }

    //EFFECTS: creates JFrame.
    private void createFrame(JFrame frame, JPanel panel) {
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: constructs all menu buttons
    private void buttons(JPanel panel) {
        JButton b1 = new JButton(new CreateNewCard());
        panel.add(b1);

        JButton b2 = new JButton(new ViewDeck());
        panel.add(b2);

        JButton b5 = new JButton(new ViewPriority());
        panel.add(b5);

        JButton b3 = new JButton(new SaveDeck());
        panel.add(b3);

        JButton b4 = new JButton(new LoadDeck());
        panel.add(b4);

        JButton b6 = new JButton(new ExitApp());
        panel.add(b6);

        JButton b7 = new JButton(new ClearDeck());
        panel.add(b7);
    }

    //EFFECTS: button to close current window and return to menu.
    private void exitButton(JPanel panel) {
        JButton menu = new JButton("Back to menu");
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panel.add(menu);
    }


    //represents the functions to clear all flashcards from the current deck.
    public class ClearDeck extends AbstractAction {


        //constructs ClearDeck.
        ClearDeck() {
            super("Clear all cards in deck",i7);
        }


        //EFFECTS: Displays all cards in the deck and asks user if they really want to clear
        //         everything.
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            JPanel panel = new JPanel();
            panel.add(new JLabel("<html> You have " + newDeck.getTotalNumberOfFlashcards()
                    + " cards in this deck<br/><br/>"
                    + "Would you like to delete all cards in your current deck right now?"
                    + "if you save after this action you cannot undo this."));
            JButton backToHome = new JButton("Go Back");
            backToHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            panel.add(backToHome);
            panel.add(new JButton(new DeleteAll()));

            createFrame(frame, panel);

        }
    }

    //allows users to delete all cards after confirmation
    public class DeleteAll extends AbstractAction {

        //EFFECTS: constructs deleteDeck
        DeleteAll() {
            super("Delete All");
        }

        //MODIFIES: newDeck
        //EFFECTS: Deletes all cards from deck
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            JPanel panel = new JPanel();

            panel.add(new JLabel("All cards deleted."));

            newDeck.deleteAllCards();

            exitButton(panel);

            createFrame(frame, panel);
        }

    }

    //allows users to load a saved json deck
    public class LoadDeck extends AbstractAction {

        //EFFECTS: constructs loadDEck
        LoadDeck() {
            super("Load Deck",i4);
        }

        //EFFECTS: loads json file.
        @Override
        public void actionPerformed(ActionEvent evt) {
            frame = new JFrame();
            JPanel panel = new JPanel();
            JLabel output = new JLabel();
            try {
                newDeck = jsonReader.read();
                output = new JLabel("Loaded " + newDeck.getName() + " from " + JSON_STORE);
            } catch (IOException e) {
                output = new JLabel("Unable to read from file: " + JSON_STORE);
            }
            panel.add(output);

            createFrame(frame, panel);
        }
    }

    //allows users to exit the app.
    public class ExitApp extends AbstractAction {
        JFrame frame;

        //EFFECTS: constructs ExitApp
        ExitApp() {
            super("Exit App", i6);
        }

        //EFFECTS: prompts users to save before exiting, then redirects to save
        //         or they can just exit.
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            JPanel panel = new JPanel();
            panel.add(new JLabel("Would you like to save before exiting?"));
            panel.add(new JButton(new SaveDeck()));

            JButton backToHome = new JButton("Exit application");
            backToHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Event next : EventLog.getInstance()) {
                        System.out.println(next.toString());
                    }
                    System.exit(0);
                }
            });

            panel.add(backToHome);
            createFrame(frame, panel);
        }

    }

    //allows users to save deck.
    public class SaveDeck extends AbstractAction {

        //EFFECTS: constructs saveDeck
        SaveDeck() {
            super("Save Current Deck",i3);
        }

        //EFFECTS: saves deck.
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            JPanel panel = new JPanel();
            JLabel output = new JLabel();
            try {
                jsonWriter.open();
                jsonWriter.write(newDeck);
                jsonWriter.close();
                output = new JLabel("Saved deck to: " + JSON_STORE);

            } catch (FileNotFoundException ee) {
                output = new JLabel("Unable to write to file: " + JSON_STORE);
            }
            panel.add(output);


            createFrame(frame, panel);
        }

    }

    //dsiplays all cards in deck
    public class ViewDeck extends AbstractAction {
        JPanel panel;

        //EFFECTS: constructs viewdeck
        ViewDeck() {
            super("View entire deck",i2);
        }

        //EFFECTS: shows on screen all cards
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            panel = new JPanel();
            panel.add(new JLabel("<html> You have " + newDeck.getTotalNumberOfFlashcards()
                    + " cards in this deck<br/><br/></html>"));


            int i = newDeck.getTotalNumberOfFlashcards() - 1;
            while (i >= 0) {
                panel.add(new JLabel("<html>Definition: " + newDeck.getFlashcardDefinition(i)
                        + "<br/> Answer: " + newDeck.getFlashcardAnswer(i) + "<br/> Status: "
                        + newDeck.getFlashcardStatus(i) + "<br/></html>"));
                i--;
            }

            exitButton(panel);


            createFrame(frame, panel);
        }
    }

    //allows users to view all cards flagged priority
    public class ViewPriority extends AbstractAction {

        FlashcardDeck priCards;

        //EFFECTS: constructs viewpriority
        ViewPriority() {
            super("View priority cards",i5);
        }

        //EFFECTS: displays all priority cards
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            JPanel panel = new JPanel();
            panel.add(new JLabel("<html><br/>You have " + newDeck.getPriorityFlashcards().getTotalNumberOfFlashcards()
                    + " priority cards in this deck<br/><br/></html>"));


            priCards = newDeck.getPriorityFlashcards();
            int i = priCards.getTotalNumberOfFlashcards() - 1;
            while (i >= 0) {
                panel.add(new JLabel("<html>Definition: " + priCards.getFlashcardDefinition(i)
                        + "<br/> Answer: " + priCards.getFlashcardAnswer(i) + "<br/> Status: "
                        + priCards.getFlashcardStatus(i) + "<br/></html>"));
                i--;
            }

            exitButton(panel);

            createFrame(frame, panel);
        }
    }


    //allows users to create new cards
    public class CreateNewCard extends AbstractAction {
        JTextField f1;
        JTextField f2;
        JCheckBox cb;

        //MODIFIES: newDeck
        //EFFECTS: creates a new card.
        CreateNewCard() {
            super("Create new card",i1);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame = new JFrame();
            JPanel panel = new JPanel();
            newCard = new Flashcard("def", "ans", "Non-Priority");
            panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
            panel.setLayout(new GridLayout(4, 2));
            panel.add(new JLabel("Enter a new definition: "));
            f1 = new JTextField();
            panel.add(f1);
            panel.add(new JLabel("Enter the answer: "));
            f2 = new JTextField();
            panel.add(f2);
            cb = new JCheckBox("Priority", false);
            panel.add(cb);
            JButton but = new JButton(new Redirect());
            but.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });

            panel.add(but);

            createFrame(frame, panel);

        }


        //redirects users back to main page
        public class Redirect extends AbstractAction {

            //EFFECTS: constructs redirect
            Redirect() {
                super("Add");
            }

            //MODIFIES: newDeck
            //EFFECTS: adds newCard to new newDeck, and exits to home.
            @Override
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame();
                newCard.changeAnswer(f2.getText());
                newCard.changeDefinition(f1.getText());
                if (cb.isSelected()) {
                    newCard.changeStatus();
                }
                newDeck.addNewFlashcard(newCard);

                JPanel panel = new JPanel();
                panel.add(new JLabel("Added card."));

                exitButton(panel);

                createFrame(frame, panel);


            }

        }
    }
}
