/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gymfit.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import pe.gymfit.dao.DatosException;

/**
 *
 * @author USUARIO
 */
public class Conexion {

    private final String url;
    private final String usuario;
    private final String clave;

    public Conexion() {
        Properties propiedades = new Properties();
        try (InputStream archivo = Conexion.class.getResourceAsStream("/db.properties")) {
            if (archivo == null) {
                throw new DatosException("No se encontró db.properties", null);
            }
            propiedades.load(archivo);
        } catch (IOException e) {
            throw new DatosException("Error al leer db.properties", e);
        }
        this.url = propiedades.getProperty("db.url");
        this.usuario = propiedades.getProperty("db.user");
        this.clave = propiedades.getProperty("db.password");

        if (url == null || usuario == null || clave == null) {
            throw new DatosException("Configuración incompleta de la base de datos", null);
        }
    }

    public Connection obtener() throws SQLException {
        return DriverManager.getConnection(url, usuario, clave);
    }
}
