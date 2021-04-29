package edu.upc.dsa.models;

import java.util.Date;

public class Seguimiento {

    /**
     * Clase Seguimiento que contiene la info de un seguimiento (id del vacunado, fecha del seguimiento, estado
     */
    //Atributos
    String idVacunado;
    Integer ordenFecha;
    String estado;

    public Seguimiento(String idVacunado, int ordenFecha, String estado) {
        this.idVacunado = idVacunado;
        this.ordenFecha = ordenFecha;
        this.estado = estado;
    }

    public String getIdVacunado() {
        return idVacunado;
    }

    public void setIdVacunado(String idVacunado) {
        this.idVacunado = idVacunado;
    }

    public int getOrdenFecha() {
        return ordenFecha;
    }

    public void setOrdenFecha(int ordenFecha) {
        this.ordenFecha = ordenFecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
