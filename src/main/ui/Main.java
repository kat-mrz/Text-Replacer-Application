package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            new TextReplacer();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot run application: file not found");
        }
        
    }
}
