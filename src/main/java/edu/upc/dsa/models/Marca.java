package edu.upc.dsa.models;

import java.util.Date;

public class Marca {

    /**
     * Clase Marca que contiene la info de un tipo de vacuna (nombre de la vacuna y nº de personas vacunadas con esta)
     */
    //Atributos
    String Marca;
    int cont;

    //Constructor
    public Marca(String Marca,int cont) {
        this.Marca=Marca;
        this.cont=cont;
    }
    //ToString

    @Override
    public String toString() {
        return "Marca{" +
                "Marca='" + Marca + '\'' +
                ", cont=" + cont +
                '}';
    }

    //Getteres&Setters
    public String getMarca() {
        return Marca;
    }

    public int getCont() {
        return cont;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }
}
