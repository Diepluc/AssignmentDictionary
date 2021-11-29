package sample.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class EditWords {
    List<Word> words = Dictionary.words;
    Dictionary dictionary = new Dictionary(words);
    DialogDisplay dis = new DialogDisplay();

    @FXML
    public TextField word_target;
    @FXML
    public TextField word_explain;

    public void removeWord() {
        String target = word_target.getText();
        dictionary.removeWord(target);
        closeButtonAction();
    }

    public void addWord() {
        String target = word_target.getText();
        String explain = word_explain.getText();
        if (target.isEmpty() || explain.isEmpty()) {
            dis.checkWord();
        } else {
            Word word = new Word(target, explain);
            word.setWord_targe(target);
            word.setWord_explain(explain);
            dictionary.addWord(word);
            System.out.println(words.size());
            closeButtonAction();
        }
    }

    public void changeWord() {
        String target = word_target.getText();
        String explain = word_explain.getText();
        int pos = dictionary.searchPosWord(target);
        if (pos == -1) {
            System.out.println("Khong co tu nay trong tu dien, khong the sua");
            dis.errorDis();
        } else {
            if (explain.isEmpty()) {
                dis.checkWord();
            } else {
                closeButtonAction();
                words.get(pos).setWord_explain(explain);
            }
        }
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) word_target.getScene().getWindow();
        stage.close();
    }
}