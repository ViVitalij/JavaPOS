package com.losK.application;

import com.losK.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by m.losK on 2017-04-12.
 */

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MainView.fxml"));

        MainController mainController = new MainController(primaryStage);
        fxmlLoader.setController(mainController);

        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaPOS app");
        primaryStage.show();
    }
}