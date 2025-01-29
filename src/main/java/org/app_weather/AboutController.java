package org.app_weather;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AboutController  {
    @FXML
    protected Button btnClose;
    @FXML
    protected Label lblThread;
    public AboutController()
    {
        String thread = Thread.currentThread().getName();

    }


    @FXML
    protected void btnClose_Clicked() throws Exception {
         btnClose.getScene().getWindow().hide();

    }


}
