/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoestudio2_leimanwu;

import javax.swing.JOptionPane;

/**
 *
 * @author leyma
 */
public class ListaAlquileres {

    private Nodo head;

    public Nodo getHead() {
        return head;
    }

    public void setHead(Nodo head) {
        this.head = head;
    }

    public ListaAlquileres() {
        head = null;
    }

    public void insertarInicio(Alquiler a) {
        Nodo nuevo = new Nodo(a);
        nuevo.setSiguiente(head);
        head = nuevo;

    }

    // Insertar al final
    public void insertarFinal(Alquiler a) {
        Nodo nuevo = new Nodo(a);
        if (head == null) {
            head = nuevo;
        } else {
            Nodo aux = head;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
    }

    // Insertar en una posición específica
    public void insertarMedio(Alquiler a, int pos) {
        if (pos <= 0 || head == null) {
            insertarInicio(a);
            return;
        }

        Nodo aux = head;
        int contador = 0;

        while (aux.getSiguiente() != null && contador < pos - 1) {
            aux = aux.getSiguiente();
            contador++;
        }

        Nodo nuevo = new Nodo(a);
        nuevo.setSiguiente(aux.getSiguiente());
        aux.setSiguiente(nuevo);
    }

    // Mostrar todos los alquileres
    public void mostrar() {
        if (head == null) {
            JOptionPane.showMessageDialog(null, "No hay registros");
            return;
        }

        Nodo aux = head;
        while (aux != null) {
            Alquiler a = aux.getDato();
            System.out.println("Vehículo: " + a.getCarro().getMarca() + " " + a.getCarro().getModelo() + ", Placa: " + a.getCarro().getPlaca());
            System.out.println("Cliente: " + a.getPersona().getNombre() + ", ID: " + a.getPersona().getId());
            System.out.println("Alquiler: " + a.getFechaAlquiler() + " | Devolución: " + a.getFechaDevolucion());
            System.out.println("------------------------------------");
            aux = aux.getSiguiente();
        }
    }

    // Buscar el alquiler más reciente (último en la lista)
    public Alquiler buscarMasNuevo() {
        if (head == null) {
            return null;
        }

        Nodo aux = head;
        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
        }
        return aux.getDato();
    }

    // Eliminar por placa (opcional)
    public void eliminar(String placa) {
        if (head == null) {
            return;
        }

        if (head.getDato().getCarro().getPlaca().equals(placa)) {
            head = head.getSiguiente();
            return;
        }

        Nodo aux = head;
        while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().getCarro().getPlaca().equals(placa)) {
            aux = aux.getSiguiente();
        }

        if (aux.getSiguiente() != null) {
            aux.setSiguiente(aux.getSiguiente().getSiguiente());
        }
    }
}
