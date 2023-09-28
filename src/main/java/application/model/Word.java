package application.model;

public class Word {
    private String wordTarget;
    private String wordMeaning;
    private String describeWord;
    public Word(String wordTarget, String wordMeaning, String describeWord) {
        super();
        this.wordTarget = wordTarget;
        this.wordMeaning = wordMeaning;
        this.describeWord = describeWord;
    }
    public String getWordTarget() {
        return wordTarget;
    }
    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }
    public String getWordMeaning() {
        return wordMeaning;
    }
    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }
    public String getDescribeWord() {
        return describeWord;
    }
    public void setDescribeWord(String describeWord) {
        this.describeWord = describeWord;
    }


}
