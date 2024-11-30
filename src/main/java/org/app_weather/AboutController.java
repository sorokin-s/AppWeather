package org.app_weather;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import static org.app_weather.WeatherForm.stageMain;
public class AboutController  {
    public AboutController(){}

    @FXML
    protected Button btnClose;

    @FXML
    protected void btnClose_Clicked() throws Exception
    {stageMain.setScene(new WeatherForm.SceneBuilder().getScene("weather"));}


}
