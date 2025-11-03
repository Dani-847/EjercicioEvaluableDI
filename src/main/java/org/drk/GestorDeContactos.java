package org.drk;

import javax.swing.*;
import java.util.ArrayList;

public class GestorDeContactos extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTextField tfCorreo;
    private JTextField tfPais;
    private JTextField tfPlataforma;
    private JButton btnAgregar;
    private JButton btnSalir;

    private ArrayList<Contacto> contactos = new ArrayList<>();

    public GestorDeContactos(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestor De Contactos");
        setSize(400,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panel1);

        btnAgregar.addActionListener(e -> agregarContacto());

        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void agregarContacto() {
        String correo = tfCorreo.getText();
        String pais = tfPais.getText();
        String plataforma = tfPlataforma.getText();

        Contacto nuevoContacto = new Contacto();
        nuevoContacto.setCorreo(correo);
        nuevoContacto.setPais(pais);
        nuevoContacto.setPlataforma(plataforma);

        contactos.add(nuevoContacto);
        actualizarTabla();
        limpiarCampos();
    }

    private void actualizarTabla() {
        var modelo = new javax.swing.table.DefaultTableModel();
        modelo.addColumn("Correo");
        modelo.addColumn("Pais");
        modelo.addColumn("Plataforma");

        for (Contacto c : contactos) {
            modelo.addRow(new Object[]{c.getCorreo(), c.getPais(), c.getPlataforma()});
        }

        table1.setModel(modelo);
    }

    private void limpiarCampos() {
        tfCorreo.setText("");
        tfPais.setText("");
        tfPlataforma.setText("");
    }

}
