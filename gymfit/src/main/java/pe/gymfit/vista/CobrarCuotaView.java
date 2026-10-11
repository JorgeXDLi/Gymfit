/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gymfit.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pe.gymfit.controlador.CuotaController;
import pe.gymfit.modelo.MedioPago;

/**
 *
 * @author USUARIO
 */
public class CobrarCuotaView extends JFrame{
    private final JTextField txtIdCuota = new JTextField();

    private final JTextField txtMonto = new JTextField();

    private final JComboBox<MedioPago> cboMedio = new JComboBox<>(MedioPago.values());

    private final JButton btnRegistrar = new JButton("Registrar pago");

    private final JLabel lblUsuario = new JLabel("Usuario:");

    private final JLabel lblMensaje = new JLabel(" ");

    private CuotaController controlador;

    public CobrarCuotaView() {

        super("GymFit - Registrar pago");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("Registrar pago");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));

        JPanel encabezado = new JPanel(new GridLayout(2, 1, 0, 8));

        encabezado.add(titulo);
        encabezado.add(lblUsuario);

        JPanel formulario = new JPanel(new GridLayout(3, 2, 10, 10));

        formulario.add(new JLabel("Número de cuota:"));
        formulario.add(txtIdCuota);

        formulario.add(new JLabel("Monto (S/):"));
        formulario.add(txtMonto);

        formulario.add(new JLabel("Medio de pago:"));
        formulario.add(cboMedio);

        JPanel pie = new JPanel(new GridLayout(2, 1, 0, 8));

        pie.add(btnRegistrar);
        pie.add(lblMensaje);

        JPanel contenido = new JPanel(new BorderLayout(0, 16));

        contenido.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        contenido.add(encabezado, BorderLayout.NORTH);
        contenido.add(formulario, BorderLayout.CENTER);
        contenido.add(pie, BorderLayout.SOUTH);

        add(contenido);

        btnRegistrar.setEnabled(false);

        btnRegistrar.addActionListener(e -> controlador.onRegistrarPago());

        setSize(460, 310);
        setLocationRelativeTo(null);
    }

    public void setControlador(CuotaController controlador) {
        this.controlador = controlador;
        btnRegistrar.setEnabled(true);
    }

    public int getIdCuota() {
        return Integer.parseInt(
            txtIdCuota.getText().trim());
    }

    public BigDecimal getMonto() {
        return new BigDecimal(txtMonto.getText().trim());
    }

    public MedioPago getMedio() {
        return (MedioPago) cboMedio.getSelectedItem();
    }

    public void mostrarUsuario(String texto) {
        lblUsuario.setText("Usuario: " + texto);
    }

    public void mostrarExito(String mensaje) {
        lblMensaje.setForeground(new Color(0, 128, 0));
        lblMensaje.setText(mensaje);
    }

    public void mostrarError(String mensaje) {
        lblMensaje.setForeground(new Color(185, 0, 0));
        lblMensaje.setText(mensaje);
    }

    public void limpiar() {
        txtIdCuota.setText("");
        txtMonto.setText("");
        cboMedio.setSelectedIndex(0);
    }
}
