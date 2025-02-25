/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MI PC
 */
public class Juego {
    private int id;
    private String titulo;
    private String consola;
    private String genero;
    private int anioLanzamiento;
    private double precio;

    public Juego(int id, String titulo, String consola, String genero, int anioLanzamiento, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.consola = consola;
        this.genero = genero;
        this.anioLanzamiento = anioLanzamiento;
        this.precio = precio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getConsola() { return consola; }
    public void setConsola(String consola) { this.consola = consola; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getAnioLanzamiento() { return anioLanzamiento; }
    public void setAnioLanzamiento(int anioLanzamiento) { this.anioLanzamiento = anioLanzamiento; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}

