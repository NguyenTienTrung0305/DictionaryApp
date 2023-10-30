public class TrieNode {
    public TrieNode[] children;
    public boolean isWord;
    public TrieNode() {
        children = new TrieNode[26];
        isWord = false;
    }
}
