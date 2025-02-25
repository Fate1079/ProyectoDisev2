/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author MI PC
 */
public class Pedido {
    private int idPedido;
    private Usuario cliente;
    private ArrayList<Juego> juegosComprados;
    private double total;

    public Pedido(int idPedido, Usuario cliente, ArrayList<Juego> juegosComprados) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.juegosComprados = juegosComprados;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double total = 0;
        for (Juego juego : juegosComprados) {
            total += juego.getPrecio();
        }
        return total;
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }

    public ArrayList<Juego> getJuegosComprados() { return juegosComprados; }
    public void setJuegosComprados(ArrayList<Juego> juegosComprados) { this.juegosComprados = juegosComprados; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}

