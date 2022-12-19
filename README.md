# My Personal Project

## Description

This application is going to be a flashcard app. Users will be able to input both sides of the flashcards,
(questions and answers) and will be able to flag certain cards to review later. This application is intended
for students who may be studying for exams and such. This is a project of interest to me as recently Quizlet 
has become a paid application so in this way I can make a free version of it for myself and others to use.

## User Stories
- As a user, I would like to add new flashcards to a deck
- As a user, I would like to prioritize certain cards
- As a user, I would like see how many prioritize cards I have.
- As a user, I would see the total number of flashcards in my deck.
- As a user, I want to be reminded to be given the option to save my flashcard deck (or not)
- As a user, I want to be able to load my previously made deck from file.

# Instructions for Grader
- You can generate the first required event related to adding Xs to a Y by using the "create
new card" button.
- You can generate the second required event related to Xs and Ys using the "clear
all cards" button to clear all cards from the current deck loaded.
- You can locate my visual component by looking at the menu and seeing all the buttons with icons
- You can save the state of my application by using the "Save Current Deck" button
- You can reload the state of my application using the "Load Deck" button



## Citation
Methods and tests in classes: JsonReader, JsonReaderTest, JsonTest, JsonWriter,
JsonWriterTest, and Main are referenced and adapted from :
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git 

Icons from freeicons.io

ActionListner button credited from: https://stackoverflow.com/questions/2352727/closing-jframe-with-button-click

Event and EventLog referenced from: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

## Phase 4 : Task 2
Thu Dec 01 20:31:32 PST 2022
Added new card.

Thu Dec 01 20:31:34 PST 2022
Added new card.

Thu Dec 01 20:31:37 PST 2022
Added new card.

Thu Dec 01 20:31:40 PST 2022
Added new card.

Thu Dec 01 20:31:40 PST 2022
Loaded cards

Thu Dec 01 20:31:46 PST 2022
Saved cards.

Thu Dec 01 20:31:50 PST 2022
Deleted all cards from deck

Thu Dec 01 20:31:51 PST 2022
Saved cards.

## Phase 4 : Task 3
What I'd like to refactor in my project is the structure of the GUI. I wasn't really a fan of working with so many
nested classes as it was difficult to find the specific class i was looking for by just scrolling. I would've liked 
to make them their own classes or at least the ones that were more than just save and load buttons.