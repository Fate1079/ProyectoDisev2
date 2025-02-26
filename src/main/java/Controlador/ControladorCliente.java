/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Singleton.Singleton;
import Util.Lista;

/**
 *
 * @author ANDRES FELIPE
 */
public class ControladorCliente {
     Lista<Usuario> listaUsuario;

    public ControladorCliente() {
        listaUsuario= Singleton.getInstancia().getListaEmpleados();
    }
    
    public Lista<Usuario> getPersona(){
        return listaUsuario;
    }
    
    public boolean Guardar(Usuario persona){
        Lista<Usuario> listaPersona = this.listaUsuario;
        listaPersona.add(persona);
        Singleton.getInstancia().escribirObjectoPersona();
        return true;
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
