package persistence;

import model.BodyText;
import model.ReplacementManager;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of ReplacementManager to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer;
    // throws FileNotFoundException if desination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // writes JSON representation of ReplacementManager to file
    public void RMwrite(ReplacementManager repMan) {
        JSONObject json = repMan.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // writes JSON representation of BodyText to file
    public void BTwrite(BodyText bt) {
        JSONObject json = bt.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }  
}
