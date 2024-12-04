package org.app_weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AboutForm extends Application{

    public  void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(AboutForm.class.getResource("about-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);

    }

}
