package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Dictionary {
    public static final List<Word> dictionary = new ArrayList<>();

    // get list of words sorted alphabetically
    public static List<Word> sortWordAsAnphabetically() {
        return dictionary.stream().sorted(Comparator.comparing(Word::getWordTarget)).collect(Collectors.toList());
    }

    // get list of word start with '..'
    public static List<Word> SearchingWord(String targetWord) {
        return dictionary.stream().filter(x -> x.getWordTarget().startsWith(targetWord.toLowerCase())).collect(Collectors.toList());
    }

    // remove word
    public static void removeWord(Word word){
        dictionary.remove(word);
    }

}
