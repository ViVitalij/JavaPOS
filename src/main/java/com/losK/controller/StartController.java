package com.losK.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by m.losK on 2017-04-14.
 */
public class StartController extends Controller {

    @FXML
    void submitOnAction(ActionEvent event) {
        getMainController().loadView("ScanView");
    }


}



