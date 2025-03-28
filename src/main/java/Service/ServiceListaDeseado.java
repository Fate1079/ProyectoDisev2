/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Service;

import Repositorio.RepositorioAmigos;
import Repositorio.RepositorioListaDeseados;
import java.sql.SQLException;

/**
 *
 * @author ANDRES FELIPE
 */
public class ServiceListaDeseado {

    private RepositorioListaDeseados JuegosRepository = new RepositorioListaDeseados();
    
    public void obtenerListadeseado() throws SQLException {
        JuegosRepository.buscarListaDeseados();
    }
    
    public boolean agregarListaDeseo(String nombre) throws SQLException {
        JuegosRepository.agregarJuegoListaDeseados(nombre);
        return true;
    }
}
