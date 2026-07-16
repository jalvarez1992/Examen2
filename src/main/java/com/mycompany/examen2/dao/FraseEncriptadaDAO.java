package com.mycompany.examen2.dao;

import com.mycompany.examen2.model.FraseEncriptada;
import com.mycompany.examen2.util.ConexionSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FraseEncriptadaDAO {

    // CREATE - Guardar frase encriptada y su configuración en SQLite
    public boolean guardarFrase(FraseEncriptada frase) {
        String sqlQuery = "INSERT INTO Encriptadas (Frase, Config) VALUES(?,?)";

        try (Connection con = ConexionSQLite.conexion(); PreparedStatement ps = con.prepareStatement(sqlQuery)) {
            ps.setString(1, frase.getFrase());
            ps.setBytes(2, frase.getConfig()); // Guardamos el BLOB usando setBytes

            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // SELECT - Obtener una frase específica por su ID
    public FraseEncriptada obtenerFrase(int id) {
        String sqlQuery = "SELECT * FROM Encriptadas WHERE id=?";

        try (Connection con = ConexionSQLite.conexion(); PreparedStatement ps = con.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FraseEncriptada frase = new FraseEncriptada();
                    frase.setId(rs.getInt("id"));
                    frase.setFrase(rs.getString("Frase"));
                    frase.setConfig(rs.getBytes("Config")); // Recuperamos el BLOB usando getBytes
                    return frase;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // UPDATE - Actualizar una frase y su configuración
    public boolean actualizarFrase(FraseEncriptada frase) {
        String sqlQuery = "UPDATE Encriptadas SET Frase=?, Config=? WHERE id=?";

        try (Connection con = ConexionSQLite.conexion(); PreparedStatement ps = con.prepareStatement(sqlQuery)) {
            ps.setString(1, frase.getFrase());
            ps.setBytes(2, frase.getConfig());
            ps.setInt(3, frase.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // DELETE - Eliminar un registro de la base de datos
    public boolean eliminarFrase(FraseEncriptada frase) {
        String sqlQuery = "DELETE FROM Encriptadas WHERE id=?";

        try (Connection con = ConexionSQLite.conexion(); PreparedStatement ps = con.prepareStatement(sqlQuery)) {
            ps.setInt(1, frase.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // SELECT ALL - Obtener todas las frases guardadas
    public List<FraseEncriptada> obtenerFrases() {
        List<FraseEncriptada> listaFrases = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Encriptadas";

        try (
                Connection con = ConexionSQLite.conexion(); PreparedStatement ps = con.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                FraseEncriptada frase = new FraseEncriptada();
                frase.setId(rs.getInt("id"));
                frase.setFrase(rs.getString("Frase"));
                frase.setConfig(rs.getBytes("Config"));

                listaFrases.add(frase);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaFrases;
    }
}
