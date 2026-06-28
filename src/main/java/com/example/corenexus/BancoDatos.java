package com.example.corenexus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BancoDatos {
    private static final ObservableList<Transaccion> historial = FXCollections.observableArrayList();

    public static ObservableList<Transaccion> getHistorial(){
        return historial;
    }

    public static void addHistorial(Transaccion tx){
        historial.add(tx);
    }
}
