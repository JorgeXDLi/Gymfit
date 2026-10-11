/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gymfit.servicio;

/**
 *
 * @author USUARIO
 */
public class PagoInvalidoException extends RuntimeException{
    public PagoInvalidoException(String mensaje){
        super(mensaje);
    }
}
