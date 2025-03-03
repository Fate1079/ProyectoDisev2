/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author MI PC
 */
public class Notificacion implements Serializable {

    private String mensaje;
    private LocalDateTime fechaHora;
    private boolean leida;

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
        this.fechaHora = LocalDateTime.now();
        this.leida = false;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public boolean isLeida() {
        return leida;
    }

    public void marcarComoLeida() {
        this.leida = true;
    }

    @Override
    public String toString() {
        return "[" + fechaHora + "] " + mensaje + (leida ? " (Leída)" : " (No leída)");
    }
}
