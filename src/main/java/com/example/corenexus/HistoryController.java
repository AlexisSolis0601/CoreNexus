package com.example.corenexus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private ListView<String> historyListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshHistory();
    }

    private  void refreshHistory(){
        historyListView.getItems().clear();

        for (Transaccion tx : BancoDatos.getHistorial()){
            String type = (tx instanceof Internacional) ? "Internacional" : "Local";
            String row = String.format("TIPO: %s\nCUENTA: %s\nDESTINO: %s\nMONTO: $%.2f\n-----------------------------------",
                    type,
                    tx.getIdentificador(),
                    tx.getPaisOrigen(),
                    tx.getMontoBruto());

            historyListView.getItems().add(row);
        }
    }
}
