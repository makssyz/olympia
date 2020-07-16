package tools;

import lists.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class FileHandler {
    private static final File fileRelative = new File("../data/smol.db") ;
    private static final File fileAbsolute = new File("src/data/smol.db") ;

    public static Database loadData() {

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

    public static Scanner openFileReader() {
        InputStream datafile = FileHandler.class.getResourceAsStream(fileRelative.getPath());
        return new Scanner(datafile);
    }

    public static void addToFile(String entryLine) {
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


