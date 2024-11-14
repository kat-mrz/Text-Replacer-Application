package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a body of text that has text.
public class BodyText implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("body text", text);
        return json;
    }
}
