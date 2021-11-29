package sample.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-controller.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 550);
        scene.getStylesheets().add(getClass().getResource("cssAssignDictionary.css").toExternalForm());
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        List<Word> words = new ArrayList<>();
        Dictionary dictionary = new Dictionary(words);
        DictionaryManagement dManagement = new DictionaryManagement(dictionary);
        dManagement.insertFromFile();
        launch();
    }
}