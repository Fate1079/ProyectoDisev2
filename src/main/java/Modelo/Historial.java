/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author ANDRES FELIPE
 */
public class Historial {

   int idHistorial;
   String nombreJuegos;
   Date fechaCompra;
   String precioQuesCompro;

    public Historial(int idHistorial, String nombreJuegos, Date fechaCompra, String precioQuesCompro) {
        this.idHistorial = idHistorial;
        this.nombreJuegos = nombreJuegos;
        this.fechaCompra = fechaCompra;
        this.precioQuesCompro = precioQuesCompro;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getNombreJuegos() {
        return nombreJuegos;
    }

    public void setNombreJuegos(String nombreJuegos) {
        this.nombreJuegos = nombreJuegos;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

   
    public String getPrecioQuesCompro() {
        return precioQuesCompro;
    }

    public void setPrecioQuesCompro(String precioQuesCompro) {
        this.precioQuesCompro = precioQuesCompro;
    }
   
   
   
}
