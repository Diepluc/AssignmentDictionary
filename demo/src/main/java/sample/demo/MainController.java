package sample.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.*;


public class MainController implements Initializable {
    List<Word> words = Dictionary.words;
    Dictionary dictionary = new Dictionary(words);
    DictionaryManagement dManagement = new DictionaryManagement(dictionary);
    TextSpeech speechText = new TextSpeech();


    @FXML
    private TextField wordTarget;

    @FXML
    private Label wordExplain;

    @FXML
    private ListView<String> listView;

    private ObservableList<String> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectWord();
        targetAction();
        viewList();
    }

    /**
     * chon tu tu viewList de hien thi tren o tim kiem.
     */
    public void viewList() {
        this.listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> wordTarget.setText(newValue.trim()));
    }

    /**
     * lay gia tri cua o tim kiem de hien thi viewList
     */
    public void targetAction() {
        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            this.listView.getItems().clear();
            updateList();
        });
    }

    /**
     * cap nhap lai danh sach sau moi lan chinh sua.
     */
    public void updateList() {
        String target = wordTarget.getText();
        List<String> list = dManagement.dictionarySearcher(target);
        obList.setAll(list);
        this.listView.setItems(obList);
    }

    /**
     * cai dat giai nghia tu.
     */
    public void selectWord() {
        this.listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String explain = dManagement.dictionaryLookup(newValue);
            wordExplain.setText(Objects.requireNonNullElse(explain, ""));
        });
       updateList();
    }


    /** them tu. */
    public void addWord() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("add-word.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 380, 245);
        stage.setTitle("Add Word");
        stage.setScene(scene);
        stage.showAndWait();
        updateList();
    }

    /** xoa tu. */
    public void removeWord() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("remove-word.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 380, 245);
        stage.setTitle("Remove Word");
        stage.setScene(scene);
        stage.showAndWait();
        stage.close();
        updateList();
    }

    /** sua doi tu. */
    public void changeWord() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("change-word.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 380, 245);
        stage.setTitle("Change Word");
        stage.setScene(scene);
        stage.show();
        updateList();
    }

    /** xua ra file. */
    public void exportFile() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("export-file.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 180);
        stage.setTitle("Export To File");
        stage.setScene(scene);
        stage.showAndWait();
        stage.close();
    }

    /** chuyen thanh giona noi. */
    public void textSpeech() {
        String target = wordTarget.getText();
        speechText.textSpeech(target);
    }

    /**
     * xoa tren o tim kiem.
     */
    public void removeTarget() {
        wordTarget.setText("");
    }

}