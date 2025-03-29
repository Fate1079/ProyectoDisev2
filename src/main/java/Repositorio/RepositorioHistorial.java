/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Repositorio;

import Modelo.Historial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRES FELIPE
 */
public class RepositorioHistorial {

  public boolean guardarHistorial(Historial historial) throws SQLException {
        String consulta = "INSERT INTO historial (id, nombre_juego, fecha_compra, precio) VALUES (?, ?, ?, ?)";

        try (Connection conexion = DataBaseConfing.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {

            stmt.setInt(1, historial.getIdHistorial());
            stmt.setString(2, historial.getNombreJuegos());
            stmt.setDate(3, historial.getFechaCompra());
            stmt.setString(4, historial.getPrecioQuesCompro());
            stmt.executeUpdate();

            System.out.println("Historial guardado con éxito.");
            return true;
        }
    }
  
  public DefaultTableModel buscarHistorial() throws SQLException {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID Historial", "Juego", "Fecha", "Precio"});

    String consulta = "SELECT h.id, h.nombre_juego, h.fecha_compra, h.precio FROM historial h";

    try (Connection conexion = DataBaseConfing.getConnection();
         PreparedStatement stmt = conexion.prepareStatement(consulta);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre_juego"),
                    rs.getTimestamp("fecha_compra"), // Si es TIMESTAMP
                    rs.getString("precio")
            });
        }
    }

    return modelo;
}
}
