/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.gymfit.servicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.gymfit.dao.CuotaDAOMemoria;
import pe.gymfit.modelo.Cuota;
import pe.gymfit.modelo.EstadoCuota;
import pe.gymfit.modelo.MedioPago;

/**
 *
 * @author USUARIO
 */
public class CuotaServiceTest {
    
    private CuotaDAOMemoria dao;
    private CuotaService servicio;

    private final BigDecimal monto = new BigDecimal("129.00");

    @BeforeEach
    void preparar() {
        dao = new CuotaDAOMemoria();

        Cuota cuota = new Cuota(1, 10, monto, LocalDate.of(2026, 10, 21));

        dao.agregar(cuota, 1);

        servicio = new CuotaService(dao);
    }

    @Test
    void registraPagoPendiente() {

        servicio.registrarPago(1, monto, MedioPago.TARJETA);

        Cuota cuota = dao.buscarPorId(1).orElseThrow();

        assertEquals(EstadoCuota.PAGADA, cuota.getEstado());

        assertEquals(MedioPago.TARJETA, cuota.getMedio());
    }

    @Test
    void rechazaPagoDuplicado() {

        servicio.registrarPago(
                1, monto, MedioPago.EFECTIVO);

        assertThrows(PagoInvalidoException.class, () -> servicio.registrarPago(1, monto, MedioPago.EFECTIVO));
    }

    @Test
    void rechazaMontoIncorrecto() {
        assertThrows(PagoInvalidoException.class, () -> servicio.registrarPago(1, new BigDecimal("100.00"), MedioPago.EFECTIVO));
    }
}
