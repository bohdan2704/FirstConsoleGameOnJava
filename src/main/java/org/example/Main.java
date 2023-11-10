package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    // Working here with streams, outputs and so on...
    private static final String path = "output.txt";
    public static void main(String[] args) {
        try {
            // Redirect standard output to a file
            System.setOut(new PrintStream(new FileOutputStream(path)));

            // Run your code that generates output
            BattleField battleField = new BattleField();
            battleField.battle();

        } catch (IOException e) {
            throw new RuntimeException("Exception while writing to file: ", e);
        } finally {
            // Reset standard output to the console
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        }

        try {
            List<String> linesList = Files.readAllLines(Path.of(path));
            for (String line : linesList) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading file: ", e);
        }

    }
    // Add graphic to this work, make everything better, comment -HP
    // Fetch a few small ASCII images
    // Make some chance for infantry to destroy Tank immediately
    // Make damage from art bigger for tanks, smaller for infantry
}