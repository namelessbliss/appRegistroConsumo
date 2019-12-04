package com.example.integrador3.Beans;

import java.io.Serializable;

public class Alimentos implements Serializable {
    String Codigo;
    String Nombre;
    Integer Calorias;
    Integer Carbohidratos;
    Integer Proteinas;
    Integer Grasas;
    Integer Fibra;
    boolean selected;

    public Alimentos() {
    }

    public Alimentos(String codigo, String nombre, Integer calorias, Integer carbohidratos, Integer proteinas, Integer grasas, Integer fibra) {
        Codigo = codigo;
        Nombre = nombre;
        Calorias = calorias;
        Carbohidratos = carbohidratos;
        Proteinas = proteinas;
        Grasas = grasas;
        Fibra = fibra;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Integer getCalorias() {
        return Calorias;
    }

    public void setCalorias(Integer calorias) {
        Calorias = calorias;
    }

    public Integer getCarbohidratos() {
        return Carbohidratos;
    }

    public void setCarbohidratos(Integer carbohidratos) {
        Carbohidratos = carbohidratos;
    }

    public Integer getProteinas() {
        return Proteinas;
    }

    public void setProteinas(Integer proteinas) {
        Proteinas = proteinas;
    }

    public Integer getGrasas() {
        return Grasas;
    }

    public void setGrasas(Integer grasas) {
        Grasas = grasas;
    }

    public Integer getFibra() {
        return Fibra;
    }

    public void setFibra(Integer fibra) {
        Fibra = fibra;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
