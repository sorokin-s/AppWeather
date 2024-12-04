package org.app_weather;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static org.app_weather.WeatherForm.stageMain;
public class AboutController  {
    public AboutController(){}

    @FXML
    protected Button btnClose;

    @FXML
    protected void btnClose_Clicked() throws Exception {
        btnClose.getScene().getWindow().hide();
    }


}
