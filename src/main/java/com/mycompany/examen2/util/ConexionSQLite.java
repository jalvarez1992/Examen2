package com.mycompany.examen2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionSQLite {

    // Se cambió el nombre del archivo de la base de datos para que tenga sentido con tu proyecto
    private static final String urldb = "jdbc:sqlite:encriptacion.db";

    public static Connection conexion() {
        try {
            return DriverManager.getConnection(urldb);
        } catch (Exception ex) {
            System.out.println("Falló la conexión a la base de datos.");
            return null;
        }
    }

    public static void crearTablas() {
        // Se actualizó la sentencia SQL con la tabla Encriptadas y el campo Config tipo BLOB
        String sql = "CREATE TABLE IF NOT EXISTS Encriptadas ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " Frase TEXT NOT NULL, "
                + " Config BLOB NOT NULL)";

        try (Connection con = conexion(); Statement st = con.createStatement();) {
            st.execute(sql);
            System.out.println("Tabla Encriptadas verificada/creada exitosamente.");
        } catch (Exception ex) {
            System.out.println("Error al crear la tabla: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
