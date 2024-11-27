package model;

import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class ReplacementManager implements Writable {
    private List<ReplacePair> repPairs;
    private ReplacePair possibleRepPair;

    // EFFECTS: constructs a replacement manager with a list of replace pairs and a
    // possible replace pair.
    public ReplacementManager() {
        EventLog.getInstance().logEvent(new Event("Replacement manager created."));
        this.repPairs = new ArrayList<>();
        this.possibleRepPair = null;
    }

    // EFFECTS: adds a replace pair to the list of replace pairs.
    // MODIFIES: this
    public void addRepPair(ReplacePair rp) {
        repPairs.add(rp);
        EventLog.getInstance().logEvent(new Event("Replace pair added to history."));
    }

    public void setPossibleRepPair(ReplacePair rp) {
        possibleRepPair = rp;
    }

    public ReplacePair getPossibleRepPair() {
        return possibleRepPair;
    }

    public List<ReplacePair> getRepPairs() {
        return repPairs;
    }

    // EFFECTS: returns last rep pair in list of rep pairs. Returns null if list has no rep pairs.
    public ReplacePair getLastRep() {
        if (repPairs.size() > 0) {
            return repPairs.get(repPairs.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("rep pairs", repPairsToJson());
        return json;
    }

    // EFFECTS: returns repPairs in this ReplacementManager as a JSON array
    private JSONArray repPairsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ReplacePair rp : repPairs) {
            jsonArray.put(rp.toJson());
        }

        return jsonArray;
    }
}
