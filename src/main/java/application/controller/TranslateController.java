package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class TranslateController {
    // nút nói
    @FXML
    private Button btnSpeechToText;

    // nút nghe
    @FXML
    private Button btnTextToSpeech;

    // nút hình ảnh
    @FXML
    private Button btnImageToText;

    // Vùng chứa english text
    @FXML
    private TextArea taEnglish;

    // Vùng chứa vietnamese text
    @FXML
    private TextArea taVietnamese;

    // Khi nhấn vào nút nói
    public void SpeechToText(){};

    // Khi nhấn vào nút nghe
    public void TextToSpeech(){};

    // Khi nhấn vào nút hình ảnh
    public void ImageToText(){};

}
