# GymFit

Sistema de registro de pagos de cuotas para un gimnasio.

Proyecto desarrollado para el curso Integrador I:
Sistemas - Software.

## Tecnologías utilizadas

- Java 24
- Apache NetBeans
- Maven
- MySQL 8
- JDBC
- Java Swing
- BCrypt
- JUnit 5

## Arquitectura del proyecto

El sistema utiliza una arquitectura organizada en capas:

- Modelo: entidades y enumeraciones.
- DAO: interfaces de acceso a datos.
- DAO MySQL: consultas a la base de datos.
- Servicio: reglas de negocio.
- Controlador: comunicación entre vista y servicio.
- Vista: formularios con Java Swing.
- Seguridad: autenticación y control de roles.
- Configuración: conexión con MySQL.

## Funcionalidad principal

Registrar el pago de cuotas de los socios del gimnasio.

## Reglas de negocio

R1: Solo se pueden pagar cuotas pendientes.

R2: El monto ingresado debe coincidir con el
monto correspondiente a la cuota.

R3: Solo los usuarios con rol ADMIN o ASESOR
pueden registrar pagos.

## Base de datos

La base de datos se denomina gymfit.

El script de creación se encuentra en:

sql/gymfit.sql

Advertencia: el script recrea la base de datos
y elimina los registros existentes.

## Configuración

1. Instalar JDK 24 y MySQL.
2. Configurar la base de datos gymfit.
3. Crear src/main/resources/db.properties.
4. Configurar la URL, usuario y contraseña de MySQL.
5. Abrir el proyecto Maven en Apache NetBeans.
6. Compilar y ejecutar pe.gymfit.Main.

## Pruebas

Se utilizan pruebas unitarias JUnit 5.

Casos principales:

- Registrar una cuota pendiente.
- Rechazar el pago duplicado.
- Rechazar un monto incorrecto.

## Seguridad

Las contraseñas de los usuarios se verifican
mediante BCrypt.

La conexión con MySQL utiliza una cuenta
con permisos limitados.

El archivo db.properties no debe publicarse
en GitHub.