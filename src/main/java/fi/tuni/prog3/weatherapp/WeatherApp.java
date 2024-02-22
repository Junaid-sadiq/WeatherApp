package fi.tuni.prog3.weatherapp;

import fi.tuni.prog3.weatherapp.view.WeatherView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class WeatherApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(WeatherApp.class.getResource("view/main-view.fxml"));
        Scene scene = new Scene(root, 1200, 700);
        

        //Set te Icon of the application
        Image icon = new Image(getClass().getResourceAsStream("/assets/CloudyDay.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.setTitle("WeatherApp");
        stage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}

