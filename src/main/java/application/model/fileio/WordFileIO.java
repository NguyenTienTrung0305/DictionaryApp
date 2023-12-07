package application.model.fileio;

import application.model.Dictionary;

import java.io.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class WordFileIO {
    /**
     * Save list of words to binary file.
     *
     * @param fileName path of file to save list of recorded words to
     * @param words    list of recorded words
     */
    private static void saveWordsToFile(String fileName, CopyOnWriteArraySet<String> words) {
        try {
            DataOutputStream outStream = new DataOutputStream(new FileOutputStream(fileName));
            for (String s : words) {
                outStream.writeUTF(s);
            }
            outStream.close();
        } catch (IOException e) {
            System.out.println("IOError: " + e.getMessage() + "\n");
        }
    }

    /**
     * Load list of words to binary file.
     *
     * @param fileName path of file to load list of recorded words from
     * @param words    list of recorded words
     */
    private static void loadWordsFromFile(String fileName, CopyOnWriteArraySet<String> words) {
        try {
            DataInputStream inStream = new DataInputStream(new FileInputStream(fileName));
            try {
                while (true) {
                    String word = inStream.readUTF();
                    words.add(word);
                }
            } catch (EOFException e) {  // Until EOF exception
                inStream.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("IOError: File NOT Found: " + fileName + "\n");
        } catch (IOException e) {
            System.out.println("IOError: " + e.getMessage() + "\n");
        }
    }

    /**
     * Save list of favorite words and list of recently searched words to binary file.
     */
    public static void saveRecordedWordsToFile() {
        saveWordsToFile("src/main/resources/application/data/favorite_words.bin",
                Dictionary.favoriteWords);
        saveWordsToFile("src/main/resources/application/data/recent_words.bin",
                Dictionary.recentWords);
    }

    /**
     * Load list of favorite words and list of recently searched words from binary file.
     */
    public static void loadRecordedWordsFromFile() {
        loadWordsFromFile("src/main/resources/application/data/favorite_words.bin",
                Dictionary.favoriteWords);
        loadWordsFromFile("src/main/resources/application/data/recent_words.bin",
                Dictionary.recentWords);
    }
}
