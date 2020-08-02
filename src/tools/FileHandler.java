package tools;

import javafx.scene.control.Alert;
import lists.*;

import java.io.*;
import java.util.Scanner;


public class FileHandler {
    private static File fileRelative;
    private static File fileAbsolute;

    public FileHandler(String filename) {
        fileRelative = new File("../data/" + filename + ".db");
        fileAbsolute = new File("src/data/" + filename + ".db");
    }

    public Database loadData() {
        Scanner reader = openFileReader();
        reader.nextLine();

        Database database = new Database();

        while (reader.hasNextLine()) {
            Entry entry = new Entry(reader.nextLine());
            database.addEntry(entry);
        }
        reader.close();
        return database;
    }

    public Scanner openFileReader() {
        try {
            InputStream datafile = FileHandler.class.getResourceAsStream(fileRelative.getPath());
            return new Scanner(datafile);
        } catch (NullPointerException e) {
            Alert fileDoesntExist = new Alert(Alert.AlertType.ERROR);
            fileDoesntExist.setHeaderText("Error");
            fileDoesntExist.setContentText("File with this name does not exist in the data directory. "
                    + "Please try a different name or create a valid file.");
            fileDoesntExist.show();
        }
        return null;
    }

    public void addToFile(String entryLine) {
        try {

            FileWriter author = new FileWriter(fileAbsolute.getAbsoluteFile(), true);
            author.write(entryLine);
            author.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

}


