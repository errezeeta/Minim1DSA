package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.Date;

public class Vacuna {
    /**
     * Clase Vacuna que contiene la info de una vacunación (id del vacunado, nombre de la vacuna y una fecha
     */
    //Atributos
    String idVacunado;
    String Marca;
    Integer ordenFecha;
    //Constr
    public Vacuna(String idVacunado, String marca,int ordenFecha) {
        this.idVacunado= idVacunado;
        this.Marca= marca;
        this.ordenFecha=ordenFecha;
    }
    //Getters&setters
    public String getIdVacunado() {
        return idVacunado;
    }

    public void setIdVacunado(String idVacunado) {
        this.idVacunado = idVacunado;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public int getOrdenFecha() {
        return ordenFecha;
    }

    public void setOrdenFecha(int ordenFecha) {
        this.ordenFecha = ordenFecha;
    }

    //ToString

    @Override
    public String toString() {
        return "Vacuna{" +
                "idVacunado='" + idVacunado + '\'' +
                ", Marca='" + Marca + '\'' +
                ", ordenFecha=" + ordenFecha +
                '}';
    }
}
