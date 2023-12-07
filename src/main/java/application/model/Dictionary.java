package application.model;

import application.DAO.WordDAO;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public class Dictionary {
    public static final List<Word> dictionary = WordDAO.getInstance().getWords();
    public static final CopyOnWriteArraySet<String> favoriteWords = new CopyOnWriteArraySet<>();
    public static final CopyOnWriteArraySet<String> recentWords = new CopyOnWriteArraySet<>();

    // get list of words sorted alphabetically
    public static List<Word> sortWordAsAnphabetically() {
        return dictionary.stream().sorted(
                Comparator.comparing(Word::getWordTarget)).collect(Collectors.toList());
    }

    // get list of word start with '..'
    public static List<Word> SearchingWord(String targetWord) {
        return dictionary.stream().filter(x ->
                x.getWordTarget().startsWith(targetWord.toLowerCase())).collect(Collectors.toList());
    }

    // remove word
    public static void removeWord(Word word) {
        // remove data in database
        WordDAO.getInstance().deleteWord(word);

        // remove data in list
        dictionary.remove(word);
    }

    /**
     * Add word to recently-searched words list
     * and delete least-recently-searched word if there are more than 200 recent words.
     * @param wordTarget most-recently-searched word
     */
    public static void addRecentWord(String wordTarget) {
        recentWords.add(wordTarget);
        if (Dictionary.recentWords.size() > 200) {
            Iterator<String> iterator = Dictionary.recentWords.iterator();
            Dictionary.recentWords.remove(iterator.next());
        }
    }

    /**
     * Add word to favorite words list
     * and delete least-recently-searched word if there are more than 500 recent words.
     * @param wordTarget newly-added favorite word
     */
    public static void addFavoriteWord(String wordTarget) {
        favoriteWords.add(wordTarget);
        if (Dictionary.favoriteWords.size() > 500) {
            Iterator<String> iterator = Dictionary.favoriteWords.iterator();
            Dictionary.favoriteWords.remove(iterator.next());
        }
    }
}
