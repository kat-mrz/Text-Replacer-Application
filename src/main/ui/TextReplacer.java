package ui;

import model.ReplacePair;
import model.BodyText;


import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TextReplacer {
    private List<ReplacePair> repPairs;
    private Scanner input;
    private BodyText bt;
    private boolean isCaseSensitive;

    //EFFECTS: instantiates the TextReplacer application
    public TextReplacer() {
        init();
        
        newBodyText(bt);
        runMenu();
        
    }

    //MODIFIES: this
    //EFFECTS: initializes scanner and list of replace pairs.
    public void init() {
        this.input = new Scanner(System.in);
        this.repPairs = new ArrayList<>();
        this.isCaseSensitive = false;
    }

    public void newBodyText(BodyText bt) {
        System.out.println("Enter your body text below this line.");
        String text = this.input.nextLine();
        this.bt = new BodyText(text);
    }

    public void newReplacePair() {
        System.out.println("Enter the word you would like to replace below this line.");
        String replacee = this.input.nextLine();
        System.out.println("Enter the word you would like to replace it with below this line.");
        String replacer = this.input.nextLine();

        ReplacePair rp = new ReplacePair(replacee, replacer);
        this.repPairs.add(rp);
        if (isCaseSensitive == false) {
            bt.replaceIgnoreCase(rp);
        }
        else {
            bt.replace(rp);
        }
        
    }

    public void runMenu() {
        System.out.println("Enter 'v' to view current body text.");
        System.out.println("Enter 'a' to add a word to replace.");
        System.out.println("Enter 'e' to edit a word replacement.");
        System.out.println("Enter 'c' to toggle case sensitivity on/off.");
        String choice = this.input.nextLine();
        if (choice.equalsIgnoreCase("v")) {
            System.out.println("Here is your body text:\n" + bt.getText());
        }
        else if (choice.equalsIgnoreCase("a")) {
            newReplacePair();
        }
        else if (choice.equalsIgnoreCase("e")) {
            //stub
        }
        else if (choice.equalsIgnoreCase("c")); {
            if (isCaseSensitive == false) {
                isCaseSensitive = true;
                System.out.println("Case sensitivity is now on!");
            }
            else {
                isCaseSensitive = false;
                System.out.println("Case sensitivity is now off!");
            }
        }

        runMenu();
    }




}
