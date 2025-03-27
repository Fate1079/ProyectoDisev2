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
public class ControladorAmigos {

    /**
     * @param args the command line arguments
     */
   
    
    public DefaultTableModel buscarAmigos() throws SQLException {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre"}); 

    // Obtener el ID del usuario autenticado
    String obtenerUsuarioId = "SELECT id FROM users LIMIT 1";
    String obtenerAmigos = "SELECT u.id, u.username FROM amigos a " +
                           "JOIN users u ON a.amigo_id = u.id " +
                           "WHERE a.usuario_id = ?";

    try (Connection conn = DataBaseConfing.getConnection();
         PreparedStatement obtenerUsuarioStmt = conn.prepareStatement(obtenerUsuarioId);
         ResultSet usuarioRs = obtenerUsuarioStmt.executeQuery()) {

        if (!usuarioRs.next()) {
            System.out.println("Error: Usuario autenticado no encontrado.");
            return modelo;
        }
        int usuarioId = usuarioRs.getInt("id");

       
        try (PreparedStatement obtenerAmigosStmt = conn.prepareStatement(obtenerAmigos)) {
            obtenerAmigosStmt.setInt(1, usuarioId);
            try (ResultSet rs = obtenerAmigosStmt.executeQuery()) {
                while (rs.next()) {
                    int idAmigo = rs.getInt("id");
                    String nombreAmigo = rs.getString("username"); 
                    modelo.addRow(new Object[]{idAmigo, nombreAmigo});
                }
            }
        }
    }
    return modelo;
}

   
    
    
   public boolean agregarAmigo(String nombreAmigo) throws SQLException {
    String obtenerUsuarioId = "SELECT id FROM users WHERE username = (SELECT username FROM users LIMIT 1)";
    String obtenerAmigoId = "SELECT id FROM users WHERE username = ?";
    String agregarAmigo = "INSERT INTO amigos (usuario_id, amigo_id) VALUES (?, ?)";

    try (Connection conn = DataBaseConfing.getConnection();
         PreparedStatement obtenerUsuarioStmt = conn.prepareStatement(obtenerUsuarioId);
         PreparedStatement obtenerAmigoStmt = conn.prepareStatement(obtenerAmigoId)) {

     
        ResultSet usuarioRs = obtenerUsuarioStmt.executeQuery();
        if (!usuarioRs.next()) {
            System.out.println("Error: Usuario autenticado no encontrado.");
            return false;
        }
        int usuarioId = usuarioRs.getInt("id");

        // Buscar el ID del amigo en la tabla users
        obtenerAmigoStmt.setString(1, nombreAmigo);
        ResultSet amigoRs = obtenerAmigoStmt.executeQuery();
        if (!amigoRs.next()) {
            System.out.println("No se encontró el amigo: " + nombreAmigo);
            return false;
        }
        int amigoId = amigoRs.getInt("id");

        // Insertar en la tabla amigos
        try (PreparedStatement agregarStmt = conn.prepareStatement(agregarAmigo)) {
            agregarStmt.setInt(1, usuarioId);
            agregarStmt.setInt(2, amigoId);
            agregarStmt.executeUpdate();
            System.out.println("Amigo agregado con éxito.");
            return true;
        }
    }
}

}
