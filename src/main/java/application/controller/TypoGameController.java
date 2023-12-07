package application.controller;

import application.model.Dictionary;
import application.model.Word;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypoGameController implements Initializable {
    private final Map<String, String> replacement = createMap();
    private final List<Word> correctWords = new ArrayList<>();
    private final Queue<String> wordsQueue = new LinkedList<>();
    private int score;
    private int progress;

    @FXML
    private AnchorPane typoGameOverContainer;
    @FXML
    private AnchorPane readyTypoGameContainer;
    @FXML
    private AnchorPane inTypoGameContainer;
    @FXML
    private Text wordText;
    @FXML
    private Text scoreText;
    @FXML
    private Text meaningText;
    @FXML
    private Text initTypoGameWarningText;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button correctWordButton;
    @FXML
    private Button typoWordButton;
    @FXML
    private Button startTypoGameButton;

    /**
     * Create a map where key is replaced by value to get a typo word.
     * @return a map
     */
    private static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "e");
        map.put("e", "a");
        map.put("b", "p");
        map.put("p", "b");
        map.put("k", "c");
        map.put("c", "k");
        map.put("y", "i");
        map.put("o", "a");
        map.put("j", "g");
        map.put("m", "n");
        map.put("n", "m");
        map.put("th", "ht");
        map.put("ei", "ie");
        map.put("ie", "ei");
        map.put("dd", "d");
        map.put("gg", "g");
        map.put("ff", "f");
        map.put("pp", "p");
        map.put("rr", "r");
        map.put("nn", "n");
        map.put("mm", "m");
        map.put("ss", "s");
        map.put("tt", "t");
        return map;
    }

    /**
     * Choose words from favorite words list.
     */
    private void chooseWord() {
        correctWords.clear();
        List<String> words = Dictionary.favoriteWords.stream().toList();
        int numberOfElements = words.size();
        if (numberOfElements == 0) {
            return;
        }
        int streamSize = Math.min(numberOfElements, 10);
        int[] indexList = generateRandNumberSet(streamSize, numberOfElements);
        for (int j : indexList) {
            for (Word w : Dictionary.dictionary) {
                if (w.getWordTarget().equals(words.get(j))) {
                    correctWords.add(w);
                }
            }
        }
    }

    /**
     * Generate a sequence of non-duplicated integers.
     * @param size number of element in sequence
     * @param bound bound of sequence
     * @return an array of non-duplicated numbers
     */
    private int[] generateRandNumberSet(int size, int bound) {
        Random random = new Random();
        Set<Integer> numbers = new CopyOnWriteArraySet<>();
        while (numbers.size() < size) {
            int rand = random.nextInt(bound);
            numbers.add(rand);
        }
        int[] res = new int[size];
        int index = 0;
        for (Integer i : numbers) {
            res[index++] = i;
        }
        return res;
    }

    /**
     * Generate typo word from correct word
     * @param correctWord origin word
     * @return a typo word
     */
    private String generateTypoWord(String correctWord) {
        List<String> replaceablePatterns = findAllReplaceablePatterns(correctWord);
        if (replaceablePatterns.isEmpty()) {
            return correctWord;
        }
        String toBeReplaced = chooseRandomReplaceablePattern(replaceablePatterns);
        return replacePattern(toBeReplaced, correctWord);
    }

    /**
     * Replace a pattern by another that can easily be mistaken.
     * @param toBeReplaced pattern to be replaced
     * @param correctWord origin word
     * @return word after replacing
     */
    public String replacePattern(String toBeReplaced, String correctWord) {
        Random random = new Random();
        Pattern pattern = Pattern.compile(toBeReplaced);
        Matcher matcher = pattern.matcher(correctWord);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            double escape = random.nextDouble();
            final double STOP_FLOOR = 0.3;
            if (escape >= STOP_FLOOR) {
                matcher.appendReplacement(stringBuilder, replacement.get(matcher.group()));
                break;
            }
        }
        matcher.appendTail(stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * Choose a random pattern from list of replaceable patterns.
     * @param replaceablePatterns list of replaceable patterns
     * @return a random pattern to be replaced
     */
    public String chooseRandomReplaceablePattern(List<String> replaceablePatterns) {
        Random random = new Random();
        int rand = random.nextInt(replaceablePatterns.size());
        return replaceablePatterns.get(rand);
    }

    /**
     * Find patterns which can be replaced.
     * @param correctWord origin word
     * @return a list of patterns that can be replaced
     */
    public List<String> findAllReplaceablePatterns(String correctWord) {
        Set<String> keys = replacement.keySet();
        Set<String> replaceablePatterns = new CopyOnWriteArraySet<>();
        int n = correctWord.length();
        for (int i = 0; i < n; i++) {
            String s = String.valueOf(correctWord.charAt(i));
            if (keys.contains(s)) {
                replaceablePatterns.add(s);
            }
        }
        for (int i = 0; i < n - 1; i++) {
            String s = correctWord.substring(i, i + 2);
            if (keys.contains(s)) {
                replaceablePatterns.add(s);
            }
        }
        return replaceablePatterns.stream().toList();
    }

    private void queueWords() {
        for (Word w : correctWords) {
            wordsQueue.add(generateTypoWord(w.getWordTarget()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readyTypoGameContainer.setVisible(true);
        inTypoGameContainer.setVisible(false);
        typoGameOverContainer.setVisible(false);
        if (Dictionary.favoriteWords.isEmpty()) {
            startTypoGameButton.setDisable(true);
            initTypoGameWarningText.setVisible(true);
        }
    }

    public void startTypoGame() {
        correctWords.clear();
        chooseWord();
        queueWords();
        readyTypoGameContainer.setVisible(false);
        inTypoGameContainer.setVisible(true);
        wordText.setText(wordsQueue.remove());
        meaningText.setText(correctWords.get(progress).getWordMeaning());
        scoreText.setText(String.valueOf(score));
        progressBar.setProgress(0);
    }

    public void clickCorrectWordButton() {
        if (wordText.getText().equals(correctWords.get(progress++).getWordTarget())) {
            score++;
        }
        pollNextWords();
    }

    public void clickTypoWordButton() {
        if (!wordText.getText().equals(correctWords.get(progress++).getWordTarget())) {
            score++;
        }
        pollNextWords();
    }

    public void pollNextWords() {
        scoreText.setText(String.valueOf(score));
        progressBar.setProgress(1.0 * progress / correctWords.size());
        if (progress < correctWords.size()) {
            wordText.setText(wordsQueue.remove());
            meaningText.setText(correctWords.get(progress).getWordMeaning());
        } else {
            typoWordButton.setDisable(true);
            correctWordButton.setDisable(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                displayGameOverScreen();
                typoWordButton.setDisable(false);
                correctWordButton.setDisable(false);
            });
            pause.play();
        }
    }

    public void displayGameOverScreen() {
        inTypoGameContainer.setVisible(false);
        typoGameOverContainer.setVisible(true);
    }

    public void clickReplayButton() {
        progress = 0;
        score = 0;
        typoGameOverContainer.setVisible(false);
        startTypoGame();
    }
}
