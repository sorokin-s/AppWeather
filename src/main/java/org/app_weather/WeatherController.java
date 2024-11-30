package org.app_weather;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import org.w3c.dom.events.MouseEvent;

import static javafx.application.Platform.exit;

public class WeatherController extends Weather  {
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
    String value;
    public WeatherController()            // конструктор
    {
        addListener(evt ->{if(evt.getPropertyName().equals("jasonData"))updateTextArea();} ); // подписываемся на изменение
        addListener(evt->{if(evt.getPropertyName().equals("progress"))updateProgressBar();});
    }
    @FXML
    protected void onExitButtonClick() {
        exit();

    }

    @FXML
    protected  void getWeatherForNameCity() throws Exception
    {   allTextClear(); // очищаем предыдущие значения текстовых полей
        setNameCity(textField.getText()); // присваиваем значение переменной название города
        lblCity.setText("Погода для города: "+textField.getText());
        getStrWeather();       // метод получения данных


    }
    @FXML
    protected void allTextClear(){
        if(!textArea.getText().isEmpty())textArea.clear();
        setProgress(0);
        strError="";
        lblWeather.setText("");
    }

    @FXML
    protected void updateTextArea()
    {
        parserWeather();
        if (jsonObject != null) {
            Platform.runLater(() -> lblWeather.setText(strWeather));
            jsonObject.toMap().forEach((k, v) -> textArea.appendText(k + ": " + v + "\n"));
            jsonObject.clear();
            setJsonData("");
        }


    }
     @FXML
    void updateProgressBar(){Platform.runLater(()->progressBar.setProgress((double)getProgress()));}

}