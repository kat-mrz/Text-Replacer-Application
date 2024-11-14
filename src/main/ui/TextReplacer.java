package ui;

import model.ReplacePair;
import model.ReplacementManager;
import model.BodyText;

import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TextReplacer {
    private static final String JSON_STORE1 = "./data/replacementmanager.json";
    private static final String JSON_STORE2 = "./data/bodytext.json";
    private JsonWriter jsonWriter1;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader1;
    private JsonReader jsonReader2;
    private ReplacementManager repMan;
    private Scanner input;
    private BodyText bt;
    private boolean isCaseSensitive;
    private ReplacePair rp;

    // EFFECTS: instantiates the TextReplacer application
    public TextReplacer() throws FileNotFoundException {
        init();

        newBodyText(bt);
        runMenu();

    }

    // MODIFIES: this
    // EFFECTS: initializes scanner and list of replace pairs
    public void init() {
        this.input = new Scanner(System.in);
        this.repMan = new ReplacementManager();
        this.isCaseSensitive = false;
        this.rp = null;
        this.jsonWriter1 = new JsonWriter(JSON_STORE1);
        this.jsonReader1 = new JsonReader(JSON_STORE1);
        this.jsonWriter2 = new JsonWriter(JSON_STORE2);
        this.jsonReader2 = new JsonReader(JSON_STORE2);
    }

    // MODIFIES: this
    // EFFECTS: adds a new body text
    public void newBodyText(BodyText bt) {
        System.out.println("Enter your body text below this line.");
        String text = this.input.nextLine();
        this.bt = new BodyText(text);
    }

    // MODIFIES: this
    // EFFECTS: adds a new replacePair to the list of existing replacePairs
    public void newReplacePair() {
        System.out.println("Enter the word you would like to replace below this line.");
        String replacee = this.input.nextLine();
        System.out.println("Enter the word you would like to replace it with below this line.");
        String replacer = this.input.nextLine();

        this.rp = new ReplacePair(replacee, replacer);
        repMan.setPossibleRepPair(rp);
        if (isCaseSensitive == false) {
            bt.replaceIgnoreCase(repMan.getPossibleRepPair());
        } else {
            bt.replace(repMan.getPossibleRepPair());
        }

        if (bt.getContainsReplacer(repMan.getPossibleRepPair()) == true) {
            repMan.addRepPair(rp);
        } else {
            System.out.println("This body text does not contain your replacee word and was not able to replace.");
        }

    }

    // EFFECT: displays and processes user's menu input
    public void runMenu() {
        printMenu();
        String choice = this.input.nextLine();
        if (choice.equalsIgnoreCase("v")) {
            System.out.println("Here is your body text:\n" + bt.getText());
            runMenu();
        } else if (choice.equalsIgnoreCase("a")) {
            newReplacePair();
            runMenu();
        } else if (choice.equalsIgnoreCase("h")) {
            viewHistory();
            runMenu();
        } else if (choice.equalsIgnoreCase("s")) {
            saveReplacementManager();
            runMenu();
        } else if (choice.equalsIgnoreCase("l")) {
            loadReplacementManager();
            runMenu();
        } else if (choice.equalsIgnoreCase("c")) {
            if (choice.equalsIgnoreCase("c")) {
                toggle();
            }
            runMenu();
        }
    }

    public void printMenu() {
        System.out.println("Enter 'v' to view current body text.");
        System.out.println("Enter 'a' to add a word to replace.");
        System.out.println("Enter 'h' to view your change history.");
        System.out.println("Enter 's' to save your body text and history.");
        System.out.println("Enter 'l' to load your body text and history.");
        System.out.println("Enter 'c' to toggle case sensitivity on/off.");
    }

    // MODIFIES: this
    // EFFECTS: displays all replacer words and prompts user to edit a selected
    // word.
    public void viewHistory() {
        if (repMan.getRepPairs().size() == 0) {
            System.out.println("No words have been replaced. Redirecting to menu.");
        } else {
            for (ReplacePair currentRepPair : repMan.getRepPairs()) {
                System.out.println("Replaced " + currentRepPair.getReplacee()
                        + " with " + currentRepPair.getReplacer() + ".");
            }
        }

        runMenu();
    }

    public void toggle() {
        if (isCaseSensitive == false) {
            isCaseSensitive = true;
            System.out.println("Case sensitivity is now on!");
        } else {
            isCaseSensitive = false;
            System.out.println("Case sensitivity is now off!");
        }
    }

    // EFFECTS: saves the replacement manager to file
    private void saveReplacementManager() {
        try {
            jsonWriter1.open();
            jsonWriter2.open();
            jsonWriter1.repManWrite(repMan);
            jsonWriter2.bodyTextWrite(bt);
            jsonWriter1.close();
            jsonWriter2.close();
            System.out.println("Saved replacement manager to " + JSON_STORE1);
            System.out.println("Saved body text to " + JSON_STORE2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE1 + " and/or " + JSON_STORE2);
        }
    }

    // EFFECTS: loads replacement manager from file
    private void loadReplacementManager() {
        try {
            repMan = jsonReader1.repManRead();
            bt = jsonReader2.bodyTextRead();
            System.out.println("Loaded replacement manager from " + JSON_STORE1);
            System.out.println("Loaded body text from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE1 + " and/or " + JSON_STORE2);
        }
    }

}