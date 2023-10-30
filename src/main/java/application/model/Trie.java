import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return true;
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(TrieNode curr, String word, int index) {
        if (index == word.length()) {
            if (!curr.isWord) {
                return false;
            }
            curr.isWord = false;
            return curr.children == null;
        }
        int i = word.charAt(index) - 'a';
        if (curr.children[i] == null) {
            return false;
        }
        boolean shouldDelete = delete(curr.children[i], word, index + 1);
        if (shouldDelete) {
            curr.children[i] = null;
            return curr.children == null && !curr.isWord;
        }
        return false;
    }

    public List<String> findAllWords() {
        List<String> result = new ArrayList<>();
        findAllWords(root, "", result);
        return result;
    }

    private void findAllWords(TrieNode curr, String word, List<String> result) {
        if (curr == null) {
            return;
        }
        if (curr.isWord) {
            result.add(word);
        }
        for (int i = 0; i < 26; i++) {
            findAllWords(curr.children[i], word + (char)('a' + i), result);
        }
    }
}