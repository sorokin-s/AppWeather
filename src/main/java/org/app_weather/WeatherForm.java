package org.app_weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class WeatherForm extends Application
{   static Stage stageMain;
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(WeatherProgram.class.getResource("weather-view.fxml"));
       // Scene scene = new Scene(fxmlLoader.load());
        stageMain = stage;
        stage.setResizable(false);
        stage.setTitle("Weather");
        stage.setScene(new SceneBuilder().getScene("weather"));
        stage.show();

    }
    public static void main(String[] args) {

        launch();
    }

    public static class SceneBuilder {


        public Scene getScene(String name) throws IOException {
            return new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(name + "-view.fxml"))));
        }
    }
}
