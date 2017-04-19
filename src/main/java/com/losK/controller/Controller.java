package com.losK.controller;

/**
 * Created by m.losK on 2017-04-12.
 */


public class Controller {
    protected MainController mainController;

    protected MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
