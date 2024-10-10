package model;

// Represents a body of text that has text.
public class BodyText {
    private String text;

    //EFFECTS: constructs a body of text containing text
    public BodyText(String text) {
        this.text = text;
    }

    //REQUIRES: text.contains(Replacer.replacee)
    //MODIFIES: this
    //EFFECTS: replaces every occurence of replacee in text with replacer.
    public void replace() {
        //stub
    }

    public String getText() {
        return this.text;
    }
    
}
