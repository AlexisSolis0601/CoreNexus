package com.example.corenexus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class InternController implements Initializable {

    @FXML
    private TextField idDestino, paisTextField, montoTextField;
    @FXML
    private Label montoTotal;
    @FXML
    private Button btnEnviar;
    @FXML
    private ListView<String> blackListView;

    private final List<String> BlackList = Arrays.asList("Corea Del Norte", "Iran", "Rusia", "Siria", "Cuba", "Birmania",
            "Somalia", "Yemen", "Afganistan", "Sudan"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        blackListView.getItems().addAll(BlackList);

        montoTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String limpiarMonto = newValue.replace(",","").replace(" ", "").trim();

            if(limpiarMonto.isEmpty()){
                montoTotal.setText("$000,000.00");
                return;
            }

            try{
                double monto = Double.parseDouble(limpiarMonto);
                double montoFinal = monto + (monto * .20);

                montoTotal.setText(String.format("$%.2f", montoFinal));
            }catch(NumberFormatException e){
                montoTotal.setText("$000,000.00");
            }
        });
    }

    public void transactionProcess(ActionEvent event){
        String cuenta = idDestino.getText();
        String pais = paisTextField.getText();
        String montoString = montoTextField.getText().replace(" ", "").trim();

        if(cuenta.trim().isEmpty() || pais.trim().isEmpty()
                || montoTextField.getText().trim().isEmpty()){
            mostrarAlerta("Campos Incompletos", "Por favor, rellene todos los campos necesarios "+
                    "\npara realizar la transaccion!", Alert.AlertType.WARNING);
            return;
        }

        if(pais.matches(".*\\d.*")){
            mostrarAlerta("Formato de País Inválido", "El nombre del país no puede contener números."+
                    "\nPor favor, escriba un país válido (Ej. Canadá).", Alert.AlertType.WARNING);
            return;
        }

        try{
            double monto = Double.parseDouble(montoString);

            //Validar si el monto es menor o igual a 0
            if(monto <= 0){
                mostrarAlerta("Monto Invalido!","Debe ingresar un monto mayor a 0!",Alert.AlertType.WARNING);
                return;
            }

            double result;
            result = monto*(1 * (20/100));
            montoTotal.setText(Double.toString(result + monto));

            //Ciclo para reccorres la blacklist y condicion si el pais destino coincide
            //con uno de los elementos de la Blacklist
            for(String blackListItem : BlackList){
                if(pais.trim().equalsIgnoreCase(blackListItem)){
                    mostrarAlerta("TRANSACCION RECHAZADA!", "El pais destino de la transaccion esta "+
                            "\n en la LISTA NEGRA, por ende no se realizara la transaccion",  Alert.AlertType.WARNING);
                    return;
                }
            }

            Internacional tx = new Internacional(cuenta, monto, pais, true);

            double comision = tx.deposito(monto);
            montoTotal.setText(String.format("$%,.2f", comision));

            mostrarAlerta("TRANSACCION EXITOSA!", "Se realizo una transaccion de: $"+monto+
                    "\nComision: (20%): $" + (comision - monto)+
                    "\nMonto Total: $" +comision+
                    "\nPais Destino: "+pais+
                    "\nA la cuenta: "+cuenta+" con EXITO!", Alert.AlertType.INFORMATION);

            idDestino.clear();
            paisTextField.clear();
            montoTextField.clear();
            montoTotal.setText("$000,000.00");

        }catch(NumberFormatException e){
            mostrarAlerta("Error de Formato!","El monto debe ser un numero valido"+
                    "\n(Ej. $26,680.00)", Alert.AlertType.ERROR);
        }catch(Exception e){
            mostrarAlerta("Error de Entrada!","Otro Valor", Alert.AlertType.ERROR);
        }
    }

    public void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
