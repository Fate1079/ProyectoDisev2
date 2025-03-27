/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRES FELIPE
 */
public class ControladorListaDeseados {

   public boolean agregarJuegoListaDeseados(String tituloJuego) throws SQLException {
    String obtenerUsuarioId = "SELECT id FROM users WHERE username = (SELECT username FROM users LIMIT 1)";
    String obtenerJuegoId = "SELECT id FROM juego WHERE titulo = ?";
    String agregarListaDeseados = "INSERT INTO lista_deseados (usuario_id, juego_id) VALUES (?, ?)";

    try (Connection conn = DataBaseConfing.getConnection();
         PreparedStatement obtenerUsuarioStmt = conn.prepareStatement(obtenerUsuarioId);
         PreparedStatement obtenerJuegoStmt = conn.prepareStatement(obtenerJuegoId)) {

        // Obtener ID del usuario autenticado
        ResultSet usuarioRs = obtenerUsuarioStmt.executeQuery();
        if (!usuarioRs.next()) {
            System.out.println("Error: Usuario autenticado no encontrado.");
            return false;
        }
        int usuarioId = usuarioRs.getInt("id");

        // Obtener ID del juego por su título
        obtenerJuegoStmt.setString(1, tituloJuego);
        ResultSet juegoRs = obtenerJuegoStmt.executeQuery();
        if (!juegoRs.next()) {
            System.out.println("No se encontró el juego: " + tituloJuego);
            return false;
        }
        int juegoId = juegoRs.getInt("id");

        // Insertar en la tabla lista_deseados
        try (PreparedStatement agregarStmt = conn.prepareStatement(agregarListaDeseados)) {
            agregarStmt.setInt(1, usuarioId);
            agregarStmt.setInt(2, juegoId);
            agregarStmt.executeUpdate();
            System.out.println("Juego agregado a la lista de deseados con éxito.");
            return true;
        }
    }
}
   
   
   public DefaultTableModel buscarListaDeseados() throws SQLException {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID", "Título", "Consola", "Año", "Precio"}); 

    // Obtener el ID del usuario autenticado
    String obtenerUsuarioId = "SELECT id FROM users LIMIT 1";
    String obtenerListaDeseados = "SELECT j.id, j.titulo, j.consola, j.anio_lanzamiento, j.precio " +
                                  "FROM lista_deseados ld " +
                                  "JOIN juego j ON ld.juego_id = j.id " +
                                  "WHERE ld.usuario_id = ?";

    try (Connection conn = DataBaseConfing.getConnection();
         PreparedStatement obtenerUsuarioStmt = conn.prepareStatement(obtenerUsuarioId);
         ResultSet usuarioRs = obtenerUsuarioStmt.executeQuery()) {

        if (!usuarioRs.next()) {
            System.out.println("Error: Usuario autenticado no encontrado.");
            return modelo;
        }
        int usuarioId = usuarioRs.getInt("id");

        // Buscar los juegos en la lista de deseados
        try (PreparedStatement obtenerListaStmt = conn.prepareStatement(obtenerListaDeseados)) {
            obtenerListaStmt.setInt(1, usuarioId);
            try (ResultSet rs = obtenerListaStmt.executeQuery()) {
                while (rs.next()) {
                    int idJuego = rs.getInt("id");
                    String titulo = rs.getString("titulo");
                    String consola = rs.getString("consola");
                    int anio = rs.getInt("anio_lanzamiento");
                    double precio = rs.getDouble("precio");
                    modelo.addRow(new Object[]{idJuego, titulo, consola, anio, precio});
                }
            }
        }
    }
    return modelo;
}
}
