package com.example.integrador3.Beans;

import java.io.Serializable;

public class Consumo implements Serializable {

    private int idConsumo;
    private String fecha, consumo;
    private double calorias;
    private String tipoConsumo;

    public Consumo(int idConsumo, String fecha, String consumo, double calorias) {
        this.idConsumo = idConsumo;
        this.fecha = fecha;
        this.consumo = consumo;
        this.calorias = calorias;
    }

    public Consumo(int idConsumo, String fecha, String consumo, double calorias, String tipoConsumo) {
        this.idConsumo = idConsumo;
        this.fecha = fecha;
        this.consumo = consumo;
        this.calorias = calorias;
        this.tipoConsumo = tipoConsumo;
    }

    public Consumo() {
    }

    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }
}
