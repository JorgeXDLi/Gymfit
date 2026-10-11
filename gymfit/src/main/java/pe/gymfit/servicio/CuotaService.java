/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gymfit.servicio;

import java.math.BigDecimal;
import java.util.Objects;
import pe.gymfit.dao.CuotaDAO;
import pe.gymfit.modelo.Cuota;
import pe.gymfit.modelo.MedioPago;

/**
 *
 * @author USUARIO
 */
public class CuotaService {

    private final CuotaDAO dao;

    public CuotaService(CuotaDAO dao) {
        this.dao = Objects.requireNonNull(dao, "El DAO es obligatorio");
    }

    public Cuota registrarPago(int idCuota, BigDecimal monto, MedioPago medio) {
        if (idCuota <= 0) {
            throw new PagoInvalidoException("El número de cuota debe ser positivo");
        }

        if (monto == null || monto.signum() <= 0) {
            throw new PagoInvalidoException("El monto debe ser mayor que cero");
        }

        if (medio == null) {
            throw new PagoInvalidoException("Selecciona un medio de pago");
        }

        Cuota cuota = dao.buscarPorId(idCuota).orElseThrow(() -> new PagoInvalidoException("La cuota no existe"));

        if (!cuota.estaPendiente()) {
            throw new PagoInvalidoException("La cuota ya fue pagada o no está pendiente");
        }

        if (cuota.getMonto().compareTo(monto) != 0) {
            throw new PagoInvalidoException("El monto no coincide con la cuota");
        }

        cuota.marcarPagada(medio);
        dao.actualizarPago(cuota);

        return cuota;
    }
}
