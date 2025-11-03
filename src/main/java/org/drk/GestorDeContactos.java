package org.drk;

import javax.swing.*;
import java.util.ArrayList;

public class GestorDeContactos extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTextField tfCorreo;
    private JTextField tfPais;
    private JButton btnAgregar;
    private JButton btnSalir;
    private JLabel lblLogger;
    private JComboBox cbPlataforma;

    private ArrayList<Contacto> contactos = new ArrayList<>();

    /**
     * Constructor de la clase GestorDeContactos.
     * Configura la ventana principal y los eventos asociados a los componentes.
     */
    public GestorDeContactos(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestor De Contactos");
        setSize(400,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panel1);

        actualizarTabla();

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /** Muestra los detalles del contacto seleccionado en un cuadro de diálogo */
        table1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table1.getSelectedRow() >= 0) {
                int row = table1.getSelectedRow();
                if (row >= 0 && row < contactos.size()) {
                    Contacto c = contactos.get(row);
                    String mensaje = "Correo: " + c.getCorreo() + "\n" +
                            "País: " + c.getPais() + "\n" +
                            "Plataforma: " + c.getPlataforma();
                    JOptionPane.showMessageDialog(this, mensaje, "Detalle del contacto", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnAgregar.addActionListener(e -> agregarContacto());

        btnSalir.addActionListener(e -> System.exit(0));
    }

    /**
     * Agrega un nuevo contacto a la lista después de validar los datos ingresados.
     * Muestra mensajes de error o confirmación en el label correspondiente.
     */
    private void agregarContacto() {
        String correo = tfCorreo.getText();
        String pais = tfPais.getText();
        String plataforma = cbPlataforma.getSelectedItem().toString();

        Contacto nuevoContacto = new Contacto();
        nuevoContacto.setCorreo(correo);
        nuevoContacto.setPais(pais);
        nuevoContacto.setPlataforma(plataforma);

        /** Validaciones de los datos ingresados */
        if (correo.isEmpty() || pais.isEmpty() || plataforma.isEmpty()) {
            lblLogger.setText("Por favor ingrese el Correo");
            limpiarCampos();
        } else if (!correo.contains("@")) {
            lblLogger.setText("El correo no es válido");
            limpiarCampos();
        } else if (contactos.stream().anyMatch(c -> c.getCorreo().equalsIgnoreCase(correo))) {
            lblLogger.setText("El correo ya existe");
            limpiarCampos();
        } else {
            contactos.add(nuevoContacto);
            lblLogger.setText("Contacto agregado correctamente");
        }
        actualizarTabla();
        limpiarCampos();
    }

    /**
     * Actualiza la tabla de contactos con los datos actuales de la lista.
     */
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

    /**
     * Limpia los campos de entrada de texto.
     */
    private void limpiarCampos() {
        tfCorreo.setText("");
        tfPais.setText("");
    }

}
