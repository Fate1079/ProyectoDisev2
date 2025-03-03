/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author MI PC
 */
public class VentaPuntos implements Serializable {

    private Usuario usuario;
    private Juego juego;
    private int puntosGastados;

    public VentaPuntos(Usuario usuario, Juego juego, int puntosGastados) {
        this.usuario = usuario;
        this.juego = juego;
        this.puntosGastados = puntosGastados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Juego getJuego() {
        return juego;
    }

    public int getPuntosGastados() {
        return puntosGastados;
    }

    @Override
    public String toString() {
        return "Venta de " + juego.getNombre() + " por " + puntosGastados + " puntos a " + usuario.getNombre();
    }
}
