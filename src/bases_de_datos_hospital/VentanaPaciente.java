/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bases_de_datos_hospital;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VentanaPaciente extends JFrame {

    private final JTextField txtNombre;
    private final JTextField txtDni;
    private final JTextField txtEdad;
    private final JTextField txtDiagnostico;
    private final JButton btnGuardar;
    private final JButton btnListar;
    private final JButton btnEliminar;
    private final JTable tablaPacientes;
    private final DefaultTableModel modelo;
    private final PacienteDAO pacienteDAO;

    public VentanaPaciente() {
        pacienteDAO = new PacienteDAO();

        txtNombre = new JTextField(20);
        txtDni = new JTextField(20);
        txtEdad = new JTextField(20);
        txtDiagnostico = new JTextField(20);
        btnGuardar = new JButton("Guardar paciente");
        btnListar = new JButton("Listar");
        btnEliminar = new JButton("Eliminar paciente");

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "DNI", "Edad", "Diagnóstico"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaPacientes = new JTable(modelo);

        configurarVentana();
        organizarComponentes();
        registrarEventos();
        listarPacientes();
    }

    private void configurarVentana() {
        setTitle("Sistema de registro de pacientes");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void organizarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Datos del paciente"),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("DNI:"));
        panelFormulario.add(txtDni);
        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdad);
        panelFormulario.add(new JLabel("Diagnóstico:"));
        panelFormulario.add(txtDiagnostico);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnListar);
        panelBotones.add(btnEliminar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        tablaPacientes.setRowHeight(28);
        tablaPacientes.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));
        tablaPacientes.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));

        JScrollPane desplazamiento = new JScrollPane(tablaPacientes);

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(desplazamiento, BorderLayout.CENTER);
    }

    private void registrarEventos() {
        btnGuardar.addActionListener(e -> guardarPaciente());
        btnListar.addActionListener(e -> listarPacientes());
        btnEliminar.addActionListener(e-> eliminarPaciente());
    }

    private void guardarPaciente() {
        String nombre = txtNombre.getText().trim();
        String dni = txtDni.getText().trim();
        String edadTexto = txtEdad.getText().trim();
        String diagnostico = txtDiagnostico.getText().trim();

        if (nombre.isEmpty() || dni.isEmpty() || edadTexto.isEmpty() || diagnostico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.",
                "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número.",
                "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Paciente paciente = new Paciente(nombre, dni, edad, diagnostico);
        boolean registrado = pacienteDAO.registrar(paciente);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Paciente registrado.");
            limpiarCampos();
            listarPacientes();
        } else {
            JOptionPane.showMessageDialog(this, "No fue posible registrar el paciente.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarPacientes() {
        modelo.setRowCount(0);
        List<Paciente> pacientes = pacienteDAO.listar();
        for (Paciente paciente : pacientes) {
            modelo.addRow(new Object[]{
                paciente.getId(),
                paciente.getNombre(),
                paciente.getDni(),
                paciente.getEdad(),
                paciente.getDiagnostico()
            });
        }
    }
    
    private void eliminarPaciente() {
    int filaSeleccionada = tablaPacientes.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un paciente de la tabla.",
            "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int id = (int) modelo.getValueAt(filaSeleccionada, 0);

    int confirmacion = JOptionPane.showConfirmDialog(this,
        "¿Seguro que desea eliminar este paciente?",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        boolean eliminado = pacienteDAO.eliminar(id);

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Paciente eliminado.");
            listarPacientes();
        } else {
            JOptionPane.showMessageDialog(this, "No fue posible eliminar el paciente.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDni.setText("");
        txtEdad.setText("");
        txtDiagnostico.setText("");
        txtNombre.requestFocus();
    }
}