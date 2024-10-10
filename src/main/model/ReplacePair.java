package model;

//Represents a word pairing having a
//replacee word and a replacer word.
public class ReplacePair {
    private String replacee;
    private String replacer;

    //EFFECTS: constructs substitution pairing with given replacee word and replacer word.
    public ReplacePair(String replacee, String replacer) {
        this.replacee = replacee;
        this.replacer = replacer;
    }

    public String getReplacee() {
        return this.replacee;
    }

    public String getReplacer() {
        return this.replacer;
    }

    public void setReplacee(String r) {
        replacee = r;
    }

    public void setReplacer(String r) {
        replacer = r;
    }
}
