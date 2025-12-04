/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoestudio2_leimanwu;

/**
 *
 * @author leyma
 */
public class Nodo {
    private Alquiler dato;
    private Nodo siguiente;

    public Nodo(Alquiler dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Alquiler getDato() {
        return dato;
    }

    public void setDato(Alquiler dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
    
}
