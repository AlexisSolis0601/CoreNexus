package com.example.corenexus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField nameTextField, idTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginBtn(ActionEvent event) throws IOException {
        String username = nameTextField.getText();
        String idText = idTextField.getText();

        if (username.trim().isEmpty() || idText.trim().isEmpty()) { //Trim corta los espacios vacios.
            //Se crea la ventana emergente de tipo WARNING en caso de que los campos Nombre e Id esten vacios.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validacion de Seguridad!");
            alert.setHeaderText("Campos vacios!");
            alert.setContentText("Por favor, rellene todos los campos!");

            alert.showAndWait();//Se detiene la ejecucion hasta que se cierre la alerta
            return;//Se detiene el metodo
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        root = loader.load();

        MenuController menuController = loader.getController();
        menuController.displayName(username);

        //Parent root = FXMLLoader.load(Application.class.getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
