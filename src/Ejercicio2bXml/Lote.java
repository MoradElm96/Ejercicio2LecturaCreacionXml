/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2bXml;

/**
 *
 * @author Morad
 */
public class Lote {
    
    String id;
    int numerocajas;
    Contenido contenido;
    String peso, manipulacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumerocajas() {
        return numerocajas;
    }

    public void setNumerocajas(int numerocajas) {
        this.numerocajas = numerocajas;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getManipulacion() {
        return manipulacion;
    }

    public void setManipulacion(String manipulacion) {
        this.manipulacion = manipulacion;
    }

    @Override
    public String toString() {
        return "Lote{" + "id=" + id + ", numerocajas=" + numerocajas + ", contenido=" + contenido + ", peso=" + peso + ", manipulacion=" + manipulacion + '}';
    }
    
    
    
}
