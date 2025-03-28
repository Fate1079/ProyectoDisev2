/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Service;

import Modelo.Juego;
import Repositorio.RepositoriJuegos;
import Repositorio.RepositorioAmigos;
import java.sql.SQLException;

/**
 *
 * @author ANDRES FELIPE
 */
public class ServiceJuegos {

    private RepositoriJuegos JuegosRepository = new RepositoriJuegos();
    
    
      
    
    
    public boolean agregarjuego(Juego nombre) throws SQLException {
        JuegosRepository.guardar(nombre);
        return true;
    }
}
