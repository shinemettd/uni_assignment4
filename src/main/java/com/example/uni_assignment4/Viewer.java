package com.example.uni_assignment4;

import com.example.uni_assignment4.CalculatorController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;


public class Viewer extends Application {
    private CalculatorController controller;

    public void showApplication(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Viewer.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 290, 450);

        controller = fxmlLoader.getController();

        stage.setTitle("Calculator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
