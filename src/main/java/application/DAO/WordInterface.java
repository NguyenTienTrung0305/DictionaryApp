package application.DAO;

import application.model.Word;

import java.util.List;

public interface WordInterface {
    public void insertWord(String word, Explanation explanation);
    public void deleteWord(String word);
    public void updateWord(String word, Explanation explanation);
    public List<String> getWords();
}
