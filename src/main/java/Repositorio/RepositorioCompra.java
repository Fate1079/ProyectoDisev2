/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Repositorio;

import Modelo.Juego;
import Modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANDRES FELIPE
 */
public class RepositorioCompra {

  public boolean guardarPedido(Pedido pedido) throws SQLException {
    String obtenerUsuarioId = "SELECT id FROM users WHERE username = (SELECT username FROM users LIMIT 1)";
    String insertarPedido = "INSERT INTO pedidos (cliente_id, total) VALUES (?, ?)";
    String insertarDetalle = "INSERT INTO pedido_juegos (pedido_id, juego_id) VALUES (?, ?)"; // Eliminado precio

    try (Connection conexion = DataBaseConfing.getConnection();
         PreparedStatement stmtObtenerUsuario = conexion.prepareStatement(obtenerUsuarioId);
         PreparedStatement stmtPedido = conexion.prepareStatement(insertarPedido, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement stmtDetalle = conexion.prepareStatement(insertarDetalle)) {

        // Obtener el ID del usuario autenticado
        ResultSet rsUsuario = stmtObtenerUsuario.executeQuery();
        if (!rsUsuario.next()) {
            System.out.println("Error: Usuario autenticado no encontrado.");
            return false;
        }
        int usuarioId = rsUsuario.getInt("id");

        double totalCalculado = 0.0;

        // Validar y calcular total
        for (Juego juego : pedido.getJuegosComprados()) {
            if (!validarPrecioJuego(juego.getId(), juego.getPrecio())) {
                System.out.println("Error: El precio del juego " + juego.getTitulo() + " no coincide con la base de datos.");
                return false;
            }
            totalCalculado += juego.getPrecio();
        }

        // Insertar pedido en la base de datos
        stmtPedido.setInt(1, usuarioId);
        stmtPedido.setDouble(2, totalCalculado);
        stmtPedido.executeUpdate();

        // Obtener el ID del pedido recién insertado
        ResultSet generatedKeys = stmtPedido.getGeneratedKeys();
        if (!generatedKeys.next()) {
            System.out.println("Error al obtener el ID del pedido.");
            return false;
        }
        int pedidoId = generatedKeys.getInt(1);

        // Insertar cada juego en la tabla pedido_juegos
        for (Juego juego : pedido.getJuegosComprados()) {
            stmtDetalle.setInt(1, pedidoId);
            stmtDetalle.setInt(2, juego.getId());
            stmtDetalle.executeUpdate();
        }

        System.out.println("Pedido guardado con éxito.");
        return true;
    }
}
  
  
  public boolean validarPrecioJuego(int juegoId, double precioIngresado) throws SQLException {
    String verificarPrecio = "SELECT precio FROM juego WHERE id = ?";

    try (Connection conexion = DataBaseConfing.getConnection();
         PreparedStatement stmtVerificarPrecio = conexion.prepareStatement(verificarPrecio)) {

        stmtVerificarPrecio.setInt(1, juegoId);
        ResultSet rs = stmtVerificarPrecio.executeQuery();

        if (rs.next()) {
            double precioRegistrado = rs.getDouble("precio");
            return precioIngresado == precioRegistrado;
        } else {
            System.out.println("Error: El juego con ID " + juegoId + " no existe.");
            return false;
        }
    }
}
  
   
   
   public DefaultTableModel buscarJuegosComprados() throws SQLException {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID Pedido", "Juego", "Fecha", "Precio"});

    String consulta = "SELECT p.id, j.titulo, p.fecha, j.precio " +
                      "FROM pedidos p " +
                      "JOIN pedido_juegos pj ON p.id = pj.pedido_id " +
                      "JOIN juego j ON pj.juego_id = j.id";

    try (Connection conexion = DataBaseConfing.getConnection();
         PreparedStatement stmt = conexion.prepareStatement(consulta);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getTimestamp("fecha"),  // Cambio: Usa Timestamp si es TIMESTAMP en MySQL
                    rs.getDouble("precio")
            });
        }
    }

    return modelo;
}
}
