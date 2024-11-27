package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a word pairing having a
//replacee word and a replacer word.
public class ReplacePair implements Writable {
    private String replacee;
    private String replacer;

    // EFFECTS: constructs substitution pairing with given replacee word and
    // replacer word.
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
        EventLog.getInstance().logEvent(new Event("New replacee set."));
    }

    public void setReplacer(String r) {
        replacer = r;
        EventLog.getInstance().logEvent(new Event("New replacer set."));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("replacee", replacee);
        json.put("replacer", replacer);
        return json;
    }

}
