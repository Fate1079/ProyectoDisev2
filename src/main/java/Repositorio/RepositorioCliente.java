/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Modelo.Usuario;
import Singleton.Singleton;
import Util.Lista;

/**
 *
 * @author ANDRES FELIPE
 */
public class RepositorioCliente {
     Lista<Usuario> listaUsuario;

    public RepositorioCliente() {
        listaUsuario= Singleton.getInstancia().getListaEmpleados();
    }
    
    public Lista<Usuario> getPersona(){
        return listaUsuario;
    }
    
   
    
    
    public Usuario BuscarCliente(String NombreUsuario) {
    for (int i = 0; i < listaUsuario.size(); i++) {
        String nombreUsuario = listaUsuario.get(i).getNombre();
        
        
        if (nombreUsuario != null && nombreUsuario.equals(NombreUsuario)) {
            return listaUsuario.get(i);
        }
    }
    return null; 
    }
     
     
     //buscar a la persona que se logeo//
}
