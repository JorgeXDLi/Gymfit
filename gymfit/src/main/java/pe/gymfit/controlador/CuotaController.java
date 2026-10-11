/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gymfit.controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import pe.gymfit.dao.DatosException;
import pe.gymfit.modelo.Cuota;
import pe.gymfit.modelo.Usuario;
import pe.gymfit.seguridad.Rol;
import pe.gymfit.seguridad.Sesion;
import pe.gymfit.servicio.CuotaService;
import pe.gymfit.servicio.PagoInvalidoException;
import pe.gymfit.vista.CobrarCuotaView;

/**
 *
 * @author USUARIO
 */
public class CuotaController {

    private static final Logger LOG
            = Logger.getLogger(CuotaController.class.getName());

    private final CuotaService servicio;
    private final CobrarCuotaView vista;
    private final Sesion sesion;

    public CuotaController(
            CuotaService servicio,
            CobrarCuotaView vista,
            Sesion sesion) {

        this.servicio = servicio;
        this.vista = vista;
        this.sesion = sesion;

        this.vista.setControlador(this);
    }

    public void mostrar() {

        Usuario usuario = sesion.getUsuario();

        vista.mostrarUsuario(
                usuario.getNombre() + " - " + usuario.getRol());

        vista.setVisible(true);
    }

    public void onRegistrarPago() {
        if (!sesion.tieneRol(Rol.ASESOR, Rol.ADMIN)) {
            vista.mostrarError("No tienes permiso para registrar pagos.");

            return;
        }

        try {
            Cuota cuota = servicio.registrarPago(vista.getIdCuota(), vista.getMonto(), vista.getMedio());

            vista.mostrarExito("Pago registrado: S/ " + cuota.getMonto());

            vista.limpiar();
        } catch (NumberFormatException e) {
            vista.mostrarError("Ingresa números válidos.");
        } catch (PagoInvalidoException e) {
            vista.mostrarError(e.getMessage());
        } catch (DatosException e) {
            LOG.log(Level.SEVERE, "Error al registrar pago", e);

            vista.mostrarError("No se pudo registrar el pago.");
        }
    }
}
