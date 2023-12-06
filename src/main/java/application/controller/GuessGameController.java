package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


import java.net.URL;
import java.util.*;


public class GuessGameController implements Initializable {
    private List<String> radWords = Arrays.asList("Aboil", "Abort", "Abuse", "Acorn", "Adapt", "Admin",
            "Admit", "Admen", "Affix", "Agree", "Ahead", "Album", "Alley", "Allot", "Allow", "Amate", "Amaze"
            , "Angel", "Anger", "Angle", "Angry", "Apple", "Apply", "Asset", "Atlas", "Audio", "Await", "Awake"
            , "Award", "Aware", "Awful", "Arson", "Bacon", "Badge", "Badly", "Baked", "Baken", "Baker", "Banks"
            , "Baths", "Beach", "Beams", "Beans", "Beany", "Beard", "Beare", "Bears", "Beast", "Benni", "Bingo"
            , "Blond", "Blood", "Bombs", "Books", "Brave", "Bravo", "Broad", "Buddy", "Build", "Built", "Cabin"
            , "Cable", "Cacao", "Canna", "Canny", "Canoe", "Carat", "Cargo", "Coast", "Cramp", "Cruck", "Dairy"
            , "Daisy", "Dance", "Dancy", "Dater", "Dates", "Daunt", "Deads", "Death", "Decay", "Decil", "Decor"
            , "Delta", "Delve", "Debit", "Dizzy", "Dolce", "Dolly", "Draco", "Draff", "Draft", "Drail", "Drain"
            , "Drake", "Drama", "Drank", "Dummy", "Dumpy", "Dunce", "Eagle", "Early", "Earth", "Ebony", "Ebook"
            , "Ebola", "Ebike", "Ecard", "Egypt", "Eight", "Elbow", "Emits", "Emmas", "Engle", "Enjoy", "Ennet"
            , "Enemy", "Epoch", "Elona", "Equip", "Essay", "Euros", "Evens", "Event", "Every", "Evoke", "Exact"
            , "Excel", "Extra", "Faded", "Fader", "Fades", "Fagot", "Fails", "Faint", "Farts", "Fasts", "Fatal"
            , "Fends", "Funny", "Feods", "Ferry", "Fesse", "Fetal", "Fiend", "Fiery", "Fifed", "Firth", "Fiscs",
            "Fishy", "Fiver", "Fives", "Fixed", "Fixer", "Gabby", "Gable", "Gains", "Galax", "Galea", "Gamer"
            , "Gangs", "Gaped", "Gaper", "Gasol", "Gassy", "Gator", "Ghost", "Glean", "Gloom", "Glove", "Gloss"
            , "Gluey", "Gnarl", "Glass", "Happy", "House", "Heart", "Handy", "Honey", "Hurry", "Horde", "Heark"
            , "Hedge", "Hovel", "Hiker", "Henna", "Hazel", "Hunch", "Hitch", "Havoc", "Haiku", "Helix", "Hilly"
            , "Hives", "Hated", "Ickle", "Icons", "Idear", "Ideas", "Idola", "Idols", "Igloo", "Iglus", "Ilion"
            , "Ilium", "Imels", "Imhos", "Infer", "Infix", "Impro", "Imshi", "Incel", "Incog", "Insue", "Intel",
            "Inter", "Iring", "Irish", "Islam", "Islay", "Italy", "Itchy", "Ivory", "Ivrit", "Izmit", "Izzat"
            , "Jelly", "Joint", "Jumbo", "Jawed", "Jager", "Jivey", "Jolly", "Jotty", "Judgy", "Joust", "Jutty"
            , "Jabot", "Jacks", "Jaded", "Jager", "Jains", "Jakes", "Jambu", "Janny", "Japes", "Jarks", "Jazzy"
            , "Jeans", "Jerky", "Jiffy", "Jingo", "Jinks", "Jived", "Jiver", "Kakas", "Kakis", "Kappa", "Kaput",
            "Kebab", "Kebar", "Ketch", "Ketol", "Kheda", "Kheth", "Knout", "Known", "Kooks", "Kooky", "Krona"
            , "Krone", "Lacer", "Laces", "Lacey", "Laith", "Laker", "Lakes", "Lands", "Lanes", "Larva", "Lased"
            , "Laser", "Lases", "Later", "Latex", "Laver", "Laves", "Lawed", "Lawns", "Macro", "Madam", "Madly",
            "Mafia", "Magic", "Mamma", "Mammy", "Manga", "Melon", "Mercy", "Merge", "Mango", "Match", "Matey"
            , "Mauve", "Maxim", "Mince", "Miner", "Moldy", "Money", "Month", "Moody", "Mummy", "Munch", "Mural"
            , "Night", "North", "Novel", "Nurse", "Nudge", "Nerve", "Nifty", "Notch", "Newts", "Nooks", "Nasal"
            , "Natal", "Nerve", "Nihil", "Nomer", "Nacho", "Nudie", "Nanny", "Nasty", "Noses", "Occur", "Ocean"
            , "Ochre", "Octal", "Odeum", "Odals", "Odesa", "Often", "Ofgem", "Ohiah", "Ohing", "Oligo", "Oliva"
            , "Ollas", "Oller", "Omers", "Omovs", "Omaha", "Oppos", "Opsin", "Osmic", "Osseo", "Osaka", "Oscar"
            , "Ovals", "Ovate", "Ovens", "Oxfam", "Oxbow", "Party", "Photo", "Plant", "Plate", "Pluto", "Pasty"
            , "Piles", "Pills", "Pinta", "Pitch", "Pixel", "Plush", "Poppy", "Porky", "Prize", "Pumps", "Punch"
            , "Puffy", "Puggy", "Punch", "Peaky", "Pedal", "Peony", "Perky", "Pesty", "Petal", "Pinks", "Pithy"
            , "Pixes", "Plaid", "Qatar", "Queen", "Quick", "Quiet", "Quilt", "Quirk", "Quoin", "Quoit", "Quote"
            , "Quash", "Quest", "Quern", "Quail", "Qualm", "Quays", "Quaff", "Quark", "Quasi", "Quell", "Rapid"
            , "Rarau", "Ravin", "Rayah", "Rayon", "Reeve", "Rhine", "Rhino", "Robin", "Roche", "Roper", "Roral"
            , "Rinse", "Ripen", "Risen", "Riser", "Rummy", "Rumor", "Sabin", "Sacks", "Sadly", "Saddo", "Saint"
            , "Salon", "Salps", "Salsa", "Sales", "Sakai", "Saker", "Salad", "Salal", "Salat", "Sauce", "Sauna"
            , "Saved", "Saver", "Saves", "Savvy", "Tacos", "Tacts", "Taels", "Taffy", "Taken", "Taker", "Takes"
            , "Takhi", "Tamed", "Tamer", "Tames", "Taroc", "Tarok", "Taros", "Taxer", "Taxes", "Taxis", "Thang"
            , "Thack", "Theek", "Theos", "Thief", "Throw", "Upend", "Urban", "Usage", "Usher", "Usual", "Utter"
            , "Uvula", "Uxmal", "Upper", "Upset", "Unfit", "Union", "Vague", "Valve", "Vapid", "Vapor", "Vault"
            , "Venus", "Verge", "Vexed", "Vibes", "Vigil", "Villa", "Virus", "Wacko", "Wader", "Wafer", "Wains"
            , "Waker", "Waler", "Wames", "Wamus", "Wands", "Waney", "Wanes", "Wanks", "Wants", "Wards", "Wares"
            , "Warts", "Warty", "Waser", "Wasps", "Waspy", "Waste", "Water", "Waxen", "Waxer", "Weals", "Weans"
            , "Wears", "Xebec", "Xenic", "Xenon", "Xerox", "Xoana", "Xylic", "Xylol", "Xysti", "Xysts", "Yacht"
            , "Yahoo", "Yodel", "Young", "Youth", "Yummy", "Yuppy", "Yexed", "Yexes", "Yarto", "Yauds", "Zaire",
            "Zazen", "Zebec", "Zebra", "Zesty", "Ziggy", "Zilch", "Zincy", "Zingy", "Zippy", "Ziram", "Zonal"
            , "Zonky");


    private String radWord;
    private int numbersOfGuess = 1;
    private boolean checkGuessWord = false;

    @FXML
    private GridPane gridGuessWord;

    @FXML
    private GridPane gridCheck;

    @FXML
    private AnchorPane containerWinGuessGame;

    @FXML
    private AnchorPane containerLoseGuessGame;

    @FXML
    private Button btnCheckGuessWord;

    @FXML
    private AnchorPane containerReady;

    @FXML
    private AnchorPane containerInGame;

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf4;

    @FXML
    private TextField tf5;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tf1.setOnKeyPressed(event -> handleTextFieldKeyPress(event, tf2));
        tf2.setOnKeyPressed(event -> handleTextFieldKeyPress(event, tf3));
        tf3.setOnKeyPressed(event -> handleTextFieldKeyPress(event, tf4));
        tf4.setOnKeyPressed(event -> handleTextFieldKeyPress(event, tf5));

        containerWinGuessGame.setVisible(false);
        containerLoseGuessGame.setVisible(false);
        containerReady.setVisible(true);
        containerInGame.setVisible(false);
        gridGuessWord.setVisible(false);
        btnCheckGuessWord.setVisible(false);
    }


    public void generatedRadWord() {
        Random generator = new Random();
        int value = generator.nextInt(radWords.size() - 1) + 0;
        radWord = radWords.get(value);
        radWord = radWord.toLowerCase();
    }

    public void StartGuessGame() {
        generatedRadWord();
        containerReady.setVisible(false);
        containerInGame.setVisible(true);
        gridGuessWord.setVisible(true);
        btnCheckGuessWord.setVisible(true);
    }


    public void CheckGuessWord() {
        tf1.requestFocus();
        System.out.print(radWord + " ");
        String guessword = "";
        for (int i = 0; i < 5; i++) {
            TextField textField = (TextField) gridGuessWord.getChildren().get(i);
            guessword += textField.getText();
        }

        guessword = guessword.toLowerCase();

        Map<Integer, Character> solve = logicGuessGame(radWord, guessword);

        for (int i = numbersOfGuess * 5 - 5; i < numbersOfGuess * 5; i++) {
            if (solve.get(i % 5).equals('A')) {
                gridCheck.getChildren().get(i).setStyle("-fx-background-color: #32CD32;");
            }
            if (solve.get(i % 5).equals('B')) {
                gridCheck.getChildren().get(i).setStyle("-fx-background-color: #20B2AA;");
            }
            if (solve.get(i % 5).equals('C')) {
                gridCheck.getChildren().get(i).setStyle("-fx-background-color: #FF8C00;");
            }
        }
        ++numbersOfGuess;
        if (radWord.equals(guessword)) {
            checkGuessWord = true;
            containerWinGuessGame.setVisible(true);
        }
        if (numbersOfGuess == 6 && checkGuessWord == false) {
            containerLoseGuessGame.setVisible(true);
        }
    }

    public static Map<Integer, Character> logicGuessGame(String a, String b) {
        // abcde aeffc
        Map<Character, Integer> m = new HashMap<>();
        Map<Integer, Character> m2 = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            if (m.containsKey(ch)) {
                int count = m.get(ch);
                m.put(ch, count + 1);
            } else {
                m.put(ch, 1);
            }
        }

        // trung lap
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                m2.put(i, 'A');
            }
        }

        // cac vi tri ma khong trung lap cung khong xuat hien
        for (int i = 0; i < b.length(); i++) {
            Integer count = m.get(b.charAt(i));
            if (count == null) {
                m2.put(i, 'B');
            } else {
                m.put(b.charAt(i), count - 1);
            }
        }

        for (int i = 0; i < 5; i++) {
            Character value = m2.get(i);
            if (value == null || (!value.equals('A') && !value.equals('B'))) {
                m2.put(i, 'C');
            }
        }
        return m2;
    }

    // click try again
    public void TryGuessGame() {
        tf1.requestFocus();
        generatedRadWord();
        containerWinGuessGame.setVisible(false);
        containerLoseGuessGame.setVisible(false);
        numbersOfGuess = 1;
        checkGuessWord = false;
        for (int i = 0; i < 25; i++) {
            gridCheck.getChildren().get(i).setStyle("-fx-background-color: #CCFFFF;");
        }
    }

    // exit win guess game
    public void ExitWinGuessGame() {
        containerWinGuessGame.setVisible(false);
        containerLoseGuessGame.setVisible(false);
        checkGuessWord = false;
        numbersOfGuess = 1;
        btnCheckGuessWord.setDisable(true);
    }

    private void handleTextFieldKeyPress(KeyEvent event, TextField nextTextField) {
        if (event.getCode() == KeyCode.ENTER) {
            // Di chuyển focus đến TextField tiếp theo
            nextTextField.requestFocus();
        }
    }

}
