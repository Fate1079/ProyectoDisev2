/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Service;

import Repositorio.RepositorioAmigos;
import java.sql.SQLException;

/**
 *
 * @author ANDRES FELIPE
 */
public class ServiceAmigos {

   private RepositorioAmigos JuegosRepository = new RepositorioAmigos();
    
    public void obtenerJuguete(int id) throws SQLException {
        JuegosRepository.buscarAmigos();
    }
    
    public boolean agregaramigos(String nombre) throws SQLException {
        JuegosRepository.agregarAmigo(nombre);
        return true;
    }
}
