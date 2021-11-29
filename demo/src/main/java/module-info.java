module sample.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jsapi;


    opens sample.demo to javafx.fxml;
    exports sample.demo;
}