package model;

// Represents a body of text that has text.
public class BodyText {
    private String text;

    // EFFECTS: constructs a body of text containing text
    public BodyText(String text) {
        this.text = text;
    }

    // MODIFIES: this
    // EFFECTS: replaces every occurence of replacee in text with replacer.
    public void replace(ReplacePair r) {
        text = text.replace(r.getReplacee(), r.getReplacer());
    }

    // MODIFIES: this
    // EFFECTS: replaces every occurence of replacee in text with replacer.
    public void replaceIgnoreCase(ReplacePair r) {
        text = text.replaceAll("(?i)" + r.getReplacee(), r.getReplacer());
    }

    public String getText() {
        return this.text;
    }

    public boolean getContainsReplacee(ReplacePair r) {
        if (text.contains(r.getReplacee())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getContainsReplacer(ReplacePair r) {
        if (text.contains(r.getReplacer())) {
            return true;
        } else {
            return false;
        }
    }
}
