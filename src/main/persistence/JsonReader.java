package persistence;

import model.BodyText;
import model.ReplacementManager;
import model.ReplacePair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads a replacement manager from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader that reads from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ReplacementManager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ReplacementManager RMread() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseReplacementManager(jsonObject);
    }

    // EFFECTS: reads BodyText from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BodyText BTread() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBodyText(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses replacement manager from JSON object and returns it
    private ReplacementManager parseReplacementManager(JSONObject jsonObject) {
        ReplacementManager repMan = new ReplacementManager();
        addRepPairs(repMan, jsonObject);
        return repMan;
    }

    // MODIFIES: repMan
    // EFFECTS: parses replace pairs from JSON ibject and adds them to Replacement Manager
    private void addRepPairs(ReplacementManager repMan, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("rep pairs");
        for (Object json : jsonArray) {
            JSONObject nextRepPair = (JSONObject) json;
            addRepPair(repMan, nextRepPair);
        }
    }

    // MODIFIES: repMan
    // EFFECTS: parses replace pair from JSON object and adds it to Replacement Manager
    private void addRepPair(ReplacementManager repMan, JSONObject jsonObject) {
        String replacee = jsonObject.getString("replacee");
        String replacer = jsonObject.getString("replacer");
        ReplacePair rp = new ReplacePair(replacee, replacer);
        repMan.addRepPair(rp);
    }   

    // EFFECTS: parses body text from JSON object and returns it
    private BodyText parseBodyText(JSONObject jsonObject) {
        String text = jsonObject.getString("body text");
        BodyText bt = new BodyText(text);
        return bt;
    }
}
