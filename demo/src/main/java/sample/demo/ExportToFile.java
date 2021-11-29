package sample.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportToFile {
    List<Word> words = Dictionary.words;
    Dictionary dictionary = new Dictionary(words);

    DialogDisplay display = new DialogDisplay();
    @FXML
    public TextField name;


    public void dictionaryExportToFile() {
        String nameFile = name.getText();
        List<Word> words = dictionary.getWords();
        BufferedWriter writer = null;
        try {
            File fileName = new File(nameFile);
            writer = new BufferedWriter(new FileWriter(fileName));

            for (Word word : words) {
                String wordTarge = word.getWord_targe();
                String wordexplain = word.getWord_explain();
                writer.write(wordTarge + "   " + wordexplain + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                System.out.println("Xuat file thanh cong");
                display.actionSuccess();
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        closeButtonAction();
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
}
