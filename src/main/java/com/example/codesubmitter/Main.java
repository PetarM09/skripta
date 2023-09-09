package com.example.codesubmitter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ControlTaskApplication controlTaskApplication = new ControlTaskApplication(primaryStage);
        Scene scene = new Scene(controlTaskApplication, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.setTitle("OOP Test");
        primaryStage.show();
    }
}

