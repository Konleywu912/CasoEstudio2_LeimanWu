/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoestudio2_leimanwu;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leyma
 */
public class VentanaPrincipal extends JFrame {

    //Vehiculo
    private JTextField texMarca;
    private JTextField texModelo;
    private JTextField texPlaca;
    private JTextField texAnio;
    private JLabel lblMarca, lblModelo, lblPlaca, lblAnio;
    private JButton btnRegistrarVehiculo;

    //Persona
    private JTextField txtNombre;
    private JTextField txtId;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JLabel lblNombre, lblId, lblDireccion, lblTelefono;
    private JButton btnRegistrarAlquiler;

    private JButton btnRegistrarCarro, btnRegistrarPersona, btnVerCarros, btnBuscarCliente, btnEliminarPlaca;

    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private ListaAlquileres lista;
    private JLabel Titulo, universidad;

    public VentanaPrincipal() {
        lista = new ListaAlquileres();
        setTitle("Registro de alquileres");
        setSize(1000, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        universidad = new JLabel("Universidad Fidelitas");
        universidad.setBounds(820,400,300,30);
        add(universidad);
        
        Titulo = new JLabel("Sistema de Alquiler de Carros");
        Titulo.setBounds(20,20,300,30);
        add(Titulo);
        
        
        btnRegistrarCarro = new JButton("Registrar Carro");
        btnRegistrarCarro.setBounds(800, 70, 160, 30);
        add(btnRegistrarCarro);

        btnRegistrarPersona = new JButton("Alquilar Carro");
        btnRegistrarPersona.setBounds(800, 130, 160, 30);
        add(btnRegistrarPersona);

        btnVerCarros = new JButton("Actualizar Tabla");
        btnVerCarros.setBounds(800, 190, 160, 30);
        add(btnVerCarros);

        btnBuscarCliente = new JButton("Buscar Cliente");
        btnBuscarCliente.setBounds(800, 250, 160, 30);
        add(btnBuscarCliente);

        btnEliminarPlaca = new JButton("Eliminar Carro");
        btnEliminarPlaca.setBounds(800, 310, 160, 30);
        add(btnEliminarPlaca);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Placa");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Alquiler");
        modeloTabla.addColumn("Devolución");

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 70, 760, 380);
        add(scroll);

        btnRegistrarCarro.addActionListener(e -> registrarCarro());
        btnRegistrarPersona.addActionListener(e -> alquilarCarro());
        btnVerCarros.addActionListener(e -> actualizarTabla());
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        btnEliminarPlaca.addActionListener(e -> eliminarPlaca());

        setVisible(true);
    }

    private void registrarCarro() {
        JTextField txtMarca = new JTextField();
        JTextField txtModelo = new JTextField();
        JTextField txtPlaca = new JTextField();
        JTextField txtAnio = new JTextField();

        Object[] message = {
            "Marca:", txtMarca,
            "Modelo:", txtModelo,
            "Placa:", txtPlaca,
            "Año:", txtAnio
        };

        while (true) {
            int option = JOptionPane.showConfirmDialog(null, message, "Registrar Carro", JOptionPane.OK_CANCEL_OPTION);

            if (option != JOptionPane.OK_OPTION) {
                return;
            }

            try {
                Carro carro = new Carro(
                        txtMarca.getText(),
                        txtModelo.getText(),
                        txtPlaca.getText(),
                        Integer.parseInt(txtAnio.getText())
                );
                Alquiler alquiler = new Alquiler(carro, null, "", "");
                lista.insertarFinal(alquiler);
                JOptionPane.showMessageDialog(null, "Carro registrado correctamente.");
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Por favor, llenar todos los espacios correctamente.");
            }
        }
    }

    private void alquilarCarro() {

        if (lista.getHead() == null) {
            JOptionPane.showMessageDialog(null, "No hay vehículos registrados.");
            return;
        }

        StringBuilder disponibles = new StringBuilder();
        Nodo aux = lista.getHead();
        int contador = 1;
        while (aux != null) {
            Alquiler a = aux.getDato();
            if (a.getPersona() == null) { 
                disponibles.append(contador).append(". ")
                        .append(a.getCarro().getMarca()).append(" ")
                        .append(a.getCarro().getModelo()).append(" - Placa: ")
                        .append(a.getCarro().getPlaca()).append("\n");
            }
            aux = aux.getSiguiente();
            contador++;
        }

        if (disponibles.length() == 0) {
            JOptionPane.showMessageDialog(null, "No hay vehículos disponibles para alquilar.");
            return;
        }

        String seleccion = JOptionPane.showInputDialog("Vehículos disponibles:\n" + disponibles
                + "\nIngrese la placa del carro que desea alquilar:");
        if (seleccion == null || seleccion.isEmpty()) {
            return;
        }

        aux = lista.getHead();
        while (aux != null) {
            Alquiler a = aux.getDato();
            if (a.getCarro().getPlaca().equalsIgnoreCase(seleccion) && a.getPersona() == null) {
                
                JTextField txtNombre = new JTextField();
                JTextField txtCedula = new JTextField();
                JTextField txtDireccion = new JTextField();
                JTextField txtTelefono = new JTextField();

                Object[] message = {
                    "Nombre:", txtNombre,
                    "Cédula:", txtCedula,
                    "Dirección:", txtDireccion,
                    "Teléfono:", txtTelefono
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Registrar Cliente", JOptionPane.OK_CANCEL_OPTION);
                if (option != JOptionPane.OK_OPTION) {
                    return;
                }
                try {
                    Persona persona = new Persona(
                            txtNombre.getText(),
                            Integer.parseInt(txtCedula.getText()),
                            txtDireccion.getText(),
                            Integer.parseInt(txtTelefono.getText())
                    );
                    a.setPersona(persona);
                    a.setFechaAlquiler("10/12/2025");
                    a.setFechaDevolucion("15/12/2025");
                    JOptionPane.showMessageDialog(null, "Carro alquilado correctamente a " + persona.getNombre());
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el cliente. Complete todos los campos correctamente.");
                    return;
                }
            }
            aux = aux.getSiguiente();
        }

        JOptionPane.showMessageDialog(null, "No se encontró un vehículo disponible con esa placa.");
    }

    private void actualizarTabla() {
        
        modeloTabla.setRowCount(0);
        Nodo aux = lista.getHead();
        while (aux != null) {
            Alquiler a = aux.getDato();
            String cliente = a.getPersona() != null ? a.getPersona().getNombre() : "";
            String cedula = a.getPersona() != null ? String.valueOf(a.getPersona().getId()) : "";
            modeloTabla.addRow(new Object[]{
                a.getCarro().getMarca(),
                a.getCarro().getModelo(),
                a.getCarro().getPlaca(),
                a.getCarro().getAnio(),
                cliente,
                cedula,
                a.getFechaAlquiler(),
                a.getFechaDevolucion()
            });
            aux = aux.getSiguiente();
        }
        JOptionPane.showMessageDialog(null, "Tabla actualizada correctamente.");
    }

    private void buscarCliente() {
        String input = JOptionPane.showInputDialog("Ingrese la cédula del cliente:");
        if (input == null) {
            return;
        }
        int cedula = Integer.parseInt(input);

        Nodo aux = lista.getHead();
        while (aux != null) {
            Persona p = aux.getDato().getPersona();
            if (p != null && p.getId() == cedula) {
                JOptionPane.showMessageDialog(null, "Cliente encontrado: " + p.getNombre()
                        + "\nCarro: " + aux.getDato().getCarro().getMarca() + " " + aux.getDato().getCarro().getModelo()
                        + "\nPlaca: " + aux.getDato().getCarro().getPlaca());
                return;
            }
            aux = aux.getSiguiente();
        }

        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
    }

    private void eliminarPlaca() {
        String placa = JOptionPane.showInputDialog("Ingrese la placa del carro a eliminar:");
        if (placa == null) {
            return;
        }
        lista.eliminar(placa);
        JOptionPane.showMessageDialog(null, "Vehiculo eliminado exitosamente");
        actualizarTabla();

    }

}
