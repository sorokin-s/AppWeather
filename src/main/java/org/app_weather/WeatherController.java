package org.app_weather;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

import static javafx.application.Platform.exit;

public class WeatherController   {

    @FXML
    public TextField textField;
    @FXML
    public Label lblCity,lblWeather;
    @FXML
    public Button btnRefresh;
    @FXML
    public ProgressBar progressBar;
    @FXML
    public TextArea textArea;
    @FXML
    public ImageView imageView;
    @FXML
    Pane panel;

    Thread thread;
    Weather weather;
    Stage stage = new Stage();

    public WeatherController()            // конструктор
    {   weather = new Weather();
        weather.addListener(evt ->{if(evt.getPropertyName().equals("jasonData"))updateTextArea();} ); // подписываемся на изменение
        weather.addListener(evt->{if(evt.getPropertyName().equals("progress"))updateProgressBar();});
       try{stage.setScene(new SceneBuilder().getScene("about"));} catch (IOException _) { }
       stage.setResizable(false);
       stage.setAlwaysOnTop(true);
       stage.setIconified(false);

    }


    @FXML
    protected void onExitButtonClick()
    {
        exit();
    }

    @FXML
    protected  void getWeatherForNameCity() throws Exception
    {  var image = imageView.getImage().getUrl();
        if(image.equals(String.valueOf((getClass().getResource("weather.jpg")))))
            imageView.setImage(new Image(String.valueOf((getClass().getResource("image1.jpg")))));

        allTextClear(); // очищаем предыдущие значения текстовых полей
        weather.setNameCity(textField.getText()); // присваиваем значение переменной название города
        lblCity.setText("Погода для города: "+textField.getText());
        weather.getStrWeather();       // метод получения данных

    }
    @FXML
    protected void allTextClear()
    {
        if(!textArea.getText().isEmpty())textArea.clear();
        weather.strWeather="";
        weather.setProgress(0);
        weather.strError="";
        lblWeather.setText("");
    }

    @FXML
    protected void updateTextArea()
    {   weather.parserWeather();
        if (weather.jsonObject != null)
        {

            Platform.runLater(() -> lblWeather.setText(weather.strWeather));
            weather.jsonObject.toMap().forEach((k, v) -> textArea.appendText(k + ": " + v + "\n"));
            weather.jsonObject.clear();
            weather.setJsonData("");
        }
        if(weather.strWeather.isEmpty())
        {
            Platform.runLater(() -> lblWeather.setText("Для данного города погода не найдена"));
        }
        var image = imageView.getImage().getUrl();
        if(image.equals(String.valueOf((getClass().getResource("image1.jpg")))))
            imageView.setImage(new Image(String.valueOf((getClass().getResource("weather.jpg")))));
    }
    @FXML
   protected void updateProgressBar()
    {
        Platform.runLater(()->progressBar.setProgress((double)weather.getProgress()));
    }
    @FXML
   protected void showAbout()
    {
       stage.showAndWait();
       var image = imageView.getImage().getUrl();
       if(image.equals(String.valueOf((getClass().getResource("weather.jpg")))))
             imageView.setImage(new Image(String.valueOf((getClass().getResource("image1.jpg")))));
      // else  imageView.setImage(new Image(String.valueOf((getClass().getResource("weather.jpg")))));



    }

}