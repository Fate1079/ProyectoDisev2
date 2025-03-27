/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Controlador;

import Modelo.Juego;
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
public class ControladorJuegos {

 public boolean guardar(Juego juego) throws SQLException {
    String consulta = "INSERT INTO juego (titulo, consola, genero, anio_lanzamiento, precio) VALUES ('" 
                      + juego.getTitulo() + "', '" 
                      + juego.getConsola() + "', '" 
                      + juego.getGenero() + "', " 
                      + juego.getAnioLanzamiento() + ", " 
                      + juego.getPrecio() + ")";
    
    try (Connection conexion = DataBaseConfing.getConnection();
         Statement stmt = conexion.createStatement()) {
        
        stmt.executeUpdate(consulta);
        System.out.println("Juego guardado con éxito.");
        return true;
    }
}
 
 public DefaultTableModel buscarJuegos() throws SQLException {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"ID", "Título", "Consola", "Género", "Año", "Precio"});

    String consulta = "SELECT id, titulo, consola, genero, anio_lanzamiento, precio FROM juego";

    try (Connection conexion = DataBaseConfing.getConnection();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(consulta)) {

        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("consola"),
                rs.getString("genero"),
                rs.getInt("anio_lanzamiento"),
                rs.getDouble("precio")
            });
        }
    }

    
    return modelo;
}
 
 public DefaultTableModel buscarJuegosAccion() throws SQLException {
    DefaultTableModel modeloAccion = new DefaultTableModel();
    modeloAccion.setColumnIdentifiers(new Object[]{"ID", "Título", "Consola", "Año", "Precio"});

    String consulta = "SELECT id, titulo, consola, anio_lanzamiento, precio FROM juego WHERE genero = 'Accion'";

    try (Connection conexion = DataBaseConfing.getConnection();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(consulta)) {

        while (rs.next()) {
            modeloAccion.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("consola"),
                rs.getInt("anio_lanzamiento"),
                rs.getDouble("precio")
            });
        }
    }
    return modeloAccion;
}

public DefaultTableModel buscarJuegosTerror() throws SQLException {
    DefaultTableModel modeloTerror = new DefaultTableModel();
    modeloTerror.setColumnIdentifiers(new Object[]{"ID", "Título", "Consola", "Año", "Precio"});

    String consulta = "SELECT id, titulo, consola, anio_lanzamiento, precio FROM juego WHERE genero = 'Terror'";

    try (Connection conexion = DataBaseConfing.getConnection();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(consulta)) {

        while (rs.next()) {
            modeloTerror.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("consola"),
                rs.getInt("anio_lanzamiento"),
                rs.getDouble("precio")
            });
        }
    }
    return modeloTerror;
}

public DefaultTableModel buscarJuegosComedia() throws SQLException {
    DefaultTableModel modeloComedia = new DefaultTableModel();
    modeloComedia.setColumnIdentifiers(new Object[]{"ID", "Título", "Consola", "Año", "Precio"});

    String consulta = "SELECT id, titulo, consola, anio_lanzamiento, precio FROM juego WHERE genero = 'Comedia'";

    try (Connection conexion = DataBaseConfing.getConnection();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(consulta)) {

        while (rs.next()) {
            modeloComedia.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("consola"),
                rs.getInt("anio_lanzamiento"),
                rs.getDouble("precio")
            });
        }
    }
    return modeloComedia;
}

  
  
}
