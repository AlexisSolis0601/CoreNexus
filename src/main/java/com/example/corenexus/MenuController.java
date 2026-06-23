package com.example.corenexus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuController {

    @FXML
    Label nameLabel, nameLabel2;
    @FXML
    private BorderPane borderPane;

    private void centerView(String fileFXML){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileFXML));
            Parent newView = loader.load();
            borderPane.setCenter(newView);
        }catch (IOException e){
            System.out.println("FXML file not found");
            e.printStackTrace();
        }
    }

    public void displayName(String username){
        nameLabel.setText(username);
        nameLabel2.setText("BIENVENIDO "+username);
    }

    public void transaccionLocal(){
        centerView("trans_local.fxml");
    }

    public void transaccionInter(){
        centerView("trans_intern.fxml");
    }

    public void historial(){
        centerView("historial.fxml");
    }

}
