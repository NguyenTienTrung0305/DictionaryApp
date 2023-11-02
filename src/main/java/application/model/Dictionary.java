import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private Trie root;
    private Map<String, Explanation> dictionary;

    public Dictionary() {
        this.root = new Trie();
        this.dictionary = new HashMap<>();
    }

    public void insert(String word, Explanation explanation) {
        this.root.insert(word);
        this.dictionary.put(word, explanation);
    }

    public boolean search(String word) {
        return this.root.search(word);
    }

    public boolean startsWith(String prefix) {
        return this.root.startsWith(prefix);
    }

    public boolean delete(String word) {
        this.dictionary.remove(word);
        return this.root.delete(word);
    }

    public Explanation getExplanation(String word) {
        return this.dictionary.get(word);
    }

    public void setExplanation(String word, Explanation explanation) {
        this.dictionary.put(word, explanation);
    }

    public void updateExplanation(String word, Explanation explanation) {
        this.dictionary.replace(word, explanation);
    }

    public void clear() {
        this.root = new Trie();
        this.dictionary.clear();
    }
}
