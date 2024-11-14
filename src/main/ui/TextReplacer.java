package ui;

import model.ReplacePair;
import model.ReplacementManager;
import model.BodyText;

import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TextReplacer {
    private static final String JSON_STORE = "./data/replacementmanager.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
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
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
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
        System.out.println("Enter 'v' to view current body text.");
        System.out.println("Enter 'a' to add a word to replace.");
        System.out.println("Enter 'h' to view your change history.");
        System.out.println("Enter 's' to save your history.");
        System.out.println("Enter 'l' to load your history.");
        System.out.println("Enter 'c' to toggle case sensitivity on/off.");
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

    // MODIFIES: this
    // EFFECTS: displays all replacer words and prompts user to edit a selected
    // word.
    public void viewHistory() {
        if (repMan.getRepPairs().size() == 0) {
            System.out.println("No words have been replaced. Redirecting to menu.");
        } else {
            for (ReplacePair currentRepPair : repMan.getRepPairs()) {
                System.out.println(
                        "Replaced " + currentRepPair.getReplacee() + " with " + currentRepPair.getReplacer() + ".");
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
            jsonWriter.open();
            jsonWriter.write(repMan);
            jsonWriter.close();
            System.out.println("Saved replacement manager to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads replacement manager from file
    private void loadReplacementManager() {
        try {
            repMan = jsonReader.RMread();
            System.out.println("Loaded replacement manager from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}