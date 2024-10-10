package model;


// Represents a body of text that has text.
public class BodyText {
    private String text;
    private ReplacePair r;

    //EFFECTS: constructs a body of text containing text
    public BodyText(String text) {
        this.text = text;
    }

    //REQUIRES: getContainsReplacee() == true
    //MODIFIES: this
    //EFFECTS: replaces every occurence of replacee in text with replacer.
    public void replace(ReplacePair r) {
        text = text.replace(r.getReplacee(), r.getReplacer());
    }

    public String getText() {
        return this.text;
    }

    public boolean getContainsReplacee(ReplacePair r) {
        if (text.contains(r.getReplacee())) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
