package com.losK.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private FXMLLoader loader;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    public void initialize() {
        loadView("StartView");
    }

    public void loadView(String viewName) {
        loadView(viewName, mainAnchorPane);
    }


    public void loadView(String viewName, AnchorPane mainAnchorPane) {
        loader = new FXMLLoader(this.getClass().getResource("/fxml/" + viewName + ".fxml"));

        try {
            AnchorPane pane = loader.load();
            Controller controller = loader.getController();
            controller.setMainController(this);
            mainAnchorPane.getChildren().clear();
            mainAnchorPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainController(Stage primaryStage) {
        setStage(primaryStage);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public AnchorPane getMainAnchorPane() {
        return mainAnchorPane;
    }
}