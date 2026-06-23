package com.example.corenexus;

public class Internacional extends Transaccion{
    private double montoNeto;
    private final double comision = 20;

    public Internacional(String identificador, double montoBruto, String paisOrigen, boolean aprobado) {
        super(identificador, montoBruto, paisOrigen, aprobado);
        this.montoNeto = montoBruto;
    }

    public double deposito(double montoNeto){
        double result;
        result = montoNeto*(1 * (comision/100));
        return montoNeto + result;
    }
}
