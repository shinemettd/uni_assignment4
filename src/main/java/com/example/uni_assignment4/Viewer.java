package com.example.uni_assignment4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 *
 * @author shinemettd (David O.)
 *
 */

public class Viewer extends Application {
    //launching calculator
    public void showApplication(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //sets path to the fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(Viewer.class.getResource("hello-view.fxml"));
        //creating and runs the scene
        Scene scene = new Scene(fxmlLoader.load(), 290, 450);
        stage.setTitle("shinemettd's Calculator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
