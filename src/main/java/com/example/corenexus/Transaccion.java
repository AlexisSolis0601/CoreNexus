package com.example.corenexus;

public class Transaccion {
    private String identificador;
    private double montoBruto;
    private String paisOrigen;
    protected boolean aprobado = true;

    public Transaccion(String identificador, double montoBruto,
                       String paisOrigen, boolean aprobado){
        this.identificador = identificador;
        this.montoBruto = montoBruto;
        this.paisOrigen = paisOrigen;
        this.aprobado = aprobado;
    }

    public String getIdentificador() {
        return identificador;
    }

    public double getMontoBruto() {
        return montoBruto;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public boolean isAprobado(){
        return aprobado;
    }

    public void setAprobado(boolean aprobado){
        this.aprobado = aprobado;

        if (aprobado){
            System.out.println("TRANSACCION APROBADA!");
        }else{
            System.out.println("TRANSACCION RECHAZADA!");
        }
    }
}
