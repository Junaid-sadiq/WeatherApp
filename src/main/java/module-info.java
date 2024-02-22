module fi.tuni.prog3.weatherapp {
    requires javafx.controls;
    requires com.google.gson;
    opens fi.tuni.prog3.weatherapp.model to com.google.gson;
    requires javafx.fxml;
    requires java.net.http;
    opens fi.tuni.prog3.weatherapp.view to javafx.fxml;
    exports fi.tuni.prog3.weatherapp;
}
