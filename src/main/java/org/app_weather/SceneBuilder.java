package org.app_weather;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class SceneBuilder {

        public Scene getScene(String name) throws IOException {
            return new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(name + "-view.fxml"))));
        }

}
