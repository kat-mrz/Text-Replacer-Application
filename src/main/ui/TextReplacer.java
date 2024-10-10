package ui;

import model.ReplacePair;
import model.BodyText;


import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TextReplacer {
    private List<ReplacePair> rp;
    private Scanner input;

    //EFFECTS: instantiates the TextReplacer application
    public TextReplacer() {
        init();

        newBodyText();
        newReplacePair();
        
    }

    //MODIFIES: this
    //EFFECTS: initializes scanner and list of replace pairs.
    public void init() {
        this.input = new Scanner(System.in);
        this.rp = new ArrayList<>();
    }

    public void newBodyText() {
        //stub
    }

    public void newReplacePair() {
        //stub
    }




}
