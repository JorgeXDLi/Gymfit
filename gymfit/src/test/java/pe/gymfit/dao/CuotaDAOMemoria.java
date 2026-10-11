/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gymfit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import pe.gymfit.modelo.Cuota;

/**
 *
 * @author USUARIO
 */
public class CuotaDAOMemoria implements CuotaDAO {

    private final Map<Integer, Cuota> cuotas = new HashMap<>();

    private final Map<Integer, Integer> socios = new HashMap<>();

    public void agregar(Cuota cuota, int idSocio) {
        cuotas.put(cuota.getIdCuota(), cuota);
        socios.put(cuota.getIdCuota(), idSocio);
    }

    @Override
    public Optional<Cuota> buscarPorId(int idCuota) {
        return Optional.ofNullable(cuotas.get(idCuota));
    }

    @Override
    public List<Cuota> listarPendientes(int idSocio) {

        List<Cuota> pendientes = new ArrayList<>();

        for (Cuota cuota : cuotas.values()) {
            if (cuota.estaPendiente() && socios.getOrDefault(cuota.getIdCuota(), -1) == idSocio) {
                pendientes.add(cuota);
            }
        }

        return pendientes;
    }

    @Override
    public void actualizarPago(Cuota cuota) {
        cuotas.put(cuota.getIdCuota(), cuota);
    }
}
