package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import model.BodyText;
import model.EventLog;
import model.ReplacePair;
import model.ReplacementManager;
import persistence.JsonReader;
import persistence.JsonWriter;

public class TextReplacerUI extends JFrame implements WindowListener {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE1 = "./data/replacementmanager.json";
    private static final String JSON_STORE2 = "./data/bodytext.json";
    private BodyText bt;
    private ReplacePair rp;
    private ReplacementManager repMan;
    private JPanel panel;
    private JFrame frame;
    private JTextArea body;
    private JTextArea historyTextArea;
    private JTextArea unsuccessfulHistoryTextArea;
    private JTextField replacee;
    private JTextField replacer;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private String unsuccessfulHistory;
    private JsonWriter jsonWriter1;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader1;
    private JsonReader jsonReader2;
    private EventLog eventLog;

    // EFFECTS: Constructs TextReplacerUI frame and instantiates fields
    public TextReplacerUI() throws IOException {
        this.frame = new JFrame("Text Replacer");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.addWindowListener(this);

        this.panel = new JPanel();
        panel.setBackground(Color.decode("#80002A"));

        this.eventLog = new EventLog();
        this.body = new JTextArea(30, 30);
        this.historyTextArea = new JTextArea(30, 10);
        this.unsuccessfulHistoryTextArea = new JTextArea(30, 10);
        this.replacee = new JTextField(10);
        this.replacer = new JTextField(10);

        jsonReadWriteConstructor();
        buttonConstructor();

        this.repMan = new ReplacementManager();
        this.unsuccessfulHistory = "";

        addButtonBehaviour();
        loadButtonBehaviour();
        saveButtonBehaviour();
        addElements();

        frame.setVisible(true);
    }

    private void jsonReadWriteConstructor() {
        this.jsonWriter1 = new JsonWriter(JSON_STORE1);
        this.jsonReader1 = new JsonReader(JSON_STORE1);
        this.jsonWriter2 = new JsonWriter(JSON_STORE2);
        this.jsonReader2 = new JsonReader(JSON_STORE2);
    }

    // EFFECTS: constructs addButton, saveButton, and loadButton.
    private void buttonConstructor() throws IOException {
        BufferedImage icon = ImageIO.read(new File("images/replace icon.png"));
        addButton = new JButton(new ImageIcon(icon));
        addButton.setSize(20, 20);

        saveButton = new JButton("save");
        loadButton = new JButton("load");
    }

    // EFFECTS: calls actionListener for addButton
    private void addButtonBehaviour() {
        addButton.addActionListener(new ActionListener() {
            @Override
            // MODIFIES: this
            // EFFECTS: when button is pressed, replaces any instance of replacee in body
            // text with replacer.
            public void actionPerformed(ActionEvent e) {
                bt = new BodyText(body.getText());
                rp = new ReplacePair(replacee.getText(), replacer.getText());
                repMan.setPossibleRepPair(rp);
                bt.replaceIgnoreCase(repMan.getPossibleRepPair());

                body.setText(bt.getText());
                if (bt.getContainsReplacer(rp)) {
                    repMan.addRepPair(rp);
                    historyTextArea.setText(printSuccessfulHistory());
                } else {
                    unsuccessfulHistoryTextArea.setText(printUnsuccessfulHistory());
                }
            }
        });
    }

    // EFFECTS: calls actionListener for loadButton
    private void loadButtonBehaviour() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            // MODIFIES: this
            // EFFECTS: when button is pressed, loads the replacement manager from file
            public void actionPerformed(ActionEvent e) {
                try {
                    bt = jsonReader2.bodyTextRead();
                    body.setText(bt.getText());
                    repMan = jsonReader1.repManRead();
                    historyTextArea.setText(printSuccessfulHistory());
                    unsuccessfulHistoryTextArea.setText(printUnsuccessfulHistory());
                } catch (IOException i) {
                    body.setText("Unable to read from file: " + JSON_STORE1 + " and/or " + JSON_STORE2);
                }
            }
        });
    }

    // EFFECTS: calls actionListener for saveButton
    private void saveButtonBehaviour() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            // MODIFIES: this
            // EFFECTS: when button is pressed, saves the replacement manager to file
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter1.open();
                    jsonWriter2.open();
                    jsonWriter1.repManWrite(repMan);
                    jsonWriter2.bodyTextWrite(bt);
                    jsonWriter1.close();
                    jsonWriter2.close();
                    body.setText("Saved replacement manager to " + JSON_STORE1 + ", Saved body text to " + JSON_STORE2);
                } catch (FileNotFoundException f) {
                    body.setText("Unable to write to file: " + JSON_STORE1 + " and/or " + JSON_STORE2);
                }
            }
        });
    }

    // EFFECTS: adds gui elements to panel, then adds panel to frame
    private void addElements() {
        panel.add(body);
        panel.add(historyTextArea);
        panel.add(unsuccessfulHistoryTextArea);
        panel.add(replacee);
        panel.add(replacer);
        panel.add(addButton);
        panel.add(saveButton);
        panel.add(loadButton);
        frame.add(panel, BorderLayout.CENTER);
    }

    // EFFECTS: returns a single string containing the history of successful changes.
    private String printSuccessfulHistory() {
        String successfulHistory = new String();
        for (ReplacePair currentRepPair : repMan.getRepPairs()) {
            successfulHistory = (successfulHistory + "Replaced " + currentRepPair.getReplacee()
                    + " with " + currentRepPair.getReplacer() + "." + "\n");
        }
        return successfulHistory;
    }

    // MODIFIES: this
    // EFFECTS: returns a single string containing the history of unsuccessful changes.
    private String printUnsuccessfulHistory() {
        unsuccessfulHistory = (unsuccessfulHistory + "Cannot replace " + rp.getReplacee()
                + " with " + rp.getReplacer() + "." + "\n");
        return unsuccessfulHistory;
    }

    public static void main(String[] args) {
        try {
            new TextReplacerUI();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // Action not needed, left as stub
    }

    // EFFECTS: prints eventLog to console after gui window is closed.
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println(eventLog.printEvents());
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // Action not needed, left as stub
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // Action not needed, left as stub
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // Action not needed, left as stub
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // Action not needed, left as stub
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // Action not needed, left as stub
    }

}
