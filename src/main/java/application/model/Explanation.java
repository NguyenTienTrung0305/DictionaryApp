public class Explanation {
    private String wordExplanation;
    private String wordExample;

    public Explanation(String wordExplanation, String wordExample) {
        this.wordExplanation = wordExplanation;
        this.wordExample = wordExample;
    }

    public String getWordExplanation() {
        return wordExplanation;
    }

    public String getWordExample() {
        return wordExample;
    }

    public void setWordExplanation(String wordExplanation) {
        this.wordExplanation = wordExplanation;
    }

    public void setWordExample(String wordExample) {
        this.wordExample = wordExample;
    }
}
