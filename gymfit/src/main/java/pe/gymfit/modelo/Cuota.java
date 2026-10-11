/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gymfit.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author USUARIO
 */
public class Cuota {

    private int idCuota;
    private int idMembresia;
    private BigDecimal monto;
    private LocalDate fechaVencimiento;
    private EstadoCuota estado = EstadoCuota.PENDIENTE;
    private MedioPago medio;
    private LocalDateTime fechaPago;

    public Cuota(int idCuota, int idMembresia, BigDecimal monto, LocalDate fechaVencimiento) {

        if (idCuota <= 0 || idMembresia <= 0) {
            throw new IllegalArgumentException("Los identificadores deben ser positivos");
        }

        if (monto == null || monto.signum() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }

        this.idCuota = idCuota;
        this.idMembresia = idMembresia;
        this.monto = monto;
        this.fechaVencimiento = Objects.requireNonNull(fechaVencimiento, "La fecha de vencimiento es obligatoria");
    }

    public boolean estaPendiente() {
        return estado == EstadoCuota.PENDIENTE;
    }

    public void marcarPagada(MedioPago medio) {
        if (!estaPendiente()) {
            throw new IllegalStateException("La cuota no esta pendiente");
        }

        this.medio = Objects.requireNonNull(medio, "Debes seleccionar un medio de pago");
        this.estado = EstadoCuota.PAGADA;
        this.fechaPago = LocalDateTime.now();
    }

    public void cargarPago(EstadoCuota estado, MedioPago medio, LocalDateTime fechaPago) {
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
        this.medio = medio;
        this.fechaPago = fechaPago;
    }

    public int getIdCuota() {
        return idCuota;
    }

    public int getIdMembresia() {
        return idMembresia;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public EstadoCuota getEstado() {
        return estado;
    }

    public MedioPago getMedio() {
        return medio;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }
}
