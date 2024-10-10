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

    //EFFECTS: instantiates the TextReplacer application
    public TextReplacer() {
        init();
        
        newBodyText(bt);
        newReplacePair();
        
    }

    //MODIFIES: this
    //EFFECTS: initializes scanner and list of replace pairs.
    public void init() {
        this.input = new Scanner(System.in);
        this.repPairs = new ArrayList<>();
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
    }




}
