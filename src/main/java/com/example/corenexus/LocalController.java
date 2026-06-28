package com.example.corenexus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LocalController implements Initializable {

    @FXML
    private TextField idDestino, montoTextField;
    @FXML
    private ComboBox<String> CBEstado;
    @FXML
    private Button btnEnviar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CBEstado.getItems().addAll("Aguascalientes", "Baja California", "Baja California Sur", "Campeche",
                "Chiapas", "Ciudad de México", "Durango", "Guanajuato", "Hidalgo", "México", "Michoacán", "Oaxaca",
                "Querétaro", "Veracruz", "Yucatán", "Sonora");
    }

    @FXML
    public void transactionProcess(ActionEvent event){
        String cuenta = idDestino.getText();
        String estado = CBEstado.getValue();
        String montoString = montoTextField.getText().replace(" ", "").trim(); //Limpiamos puntos y comas del monto y tambien espacios vacios

        //Validar si los campos estan vacios.
        if(cuenta.trim().isEmpty() || estado == null ||
                estado.trim().isEmpty() || montoString.trim().isEmpty()) {
            mostrarAlerta("Campos Incompletos", "Por favor, rellene todos los datos necesarios para la transferencia",
                    Alert.AlertType.WARNING);
            return;
        }

        try{
            double monto = Double.parseDouble(montoString); //convertimos el tipo de dato de monto de string a double
            if (monto <= 0) {
                mostrarAlerta("Monto Invalido", "El monto debe ser mayor a 0", Alert.AlertType.WARNING);
                return;
            } else if (monto >= 20000) {
                mostrarAlerta("LIMITE SUPERADO!!", "Limite de Transaccion Superada! \nLimite: $20,000", Alert.AlertType.ERROR);
                return;
            }

            Transaccion tx = new Transaccion(cuenta, monto, estado, true);

            BancoDatos.addHistorial(tx);

            mostrarAlerta("TRANSACCION EXITOSA!", "Se realizo una transaccion de: $"+monto+
                    "\nA la cuenta: "+cuenta+" con EXITO!", Alert.AlertType.INFORMATION);

            idDestino.clear();
            montoTextField.clear();
            CBEstado.setValue(null);

        }catch(NumberFormatException e){
            mostrarAlerta("Error de Formato", "El monto debe ser un numero valido (Ej. $10,500).", Alert.AlertType.ERROR);
        }catch (Exception e){
            mostrarAlerta("Error de Entrada", "Otro valor", Alert.AlertType.ERROR);
        }
    }

    //metodo para mostrar alertas
    public void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
