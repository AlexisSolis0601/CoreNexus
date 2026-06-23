package com.example.corenexus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("CoreNexus");
        stage.setResizable(false); //Bloquea la redimension
        stage.setScene(scene);
        stage.show();
    }
}
