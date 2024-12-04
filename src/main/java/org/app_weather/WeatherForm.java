package org.app_weather;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


public class WeatherForm extends Application
{   static Stage stageMain;
    @Override
    public void start(Stage stage) throws IOException {
        stageMain = stage;
        stage.setResizable(false);
        stage.setTitle("Weather");
        stage.setScene(new SceneBuilder().getScene("weather"));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
