package application.DAO;

import application.model.Word;

import java.util.List;

public interface WordInterface {
    public void insertWord(Word word);
    public void deleteWord(Word word);
    public void updateWord(Word word);
    public List<Word> getWords();
}
