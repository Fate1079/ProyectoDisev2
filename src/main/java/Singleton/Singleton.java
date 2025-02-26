/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Singleton;



import Modelo.Usuario;
import Util.Cola;
import Util.Lista;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ANDRES FELIPE
 */
public class Singleton implements Serializable{
   
    private static final Singleton INSTANCIA = new Singleton();
   private Lista<Usuario> listaUsuario;
   

    private Singleton() {
        listaUsuario = leerUsuario();
        
    }

    public static Singleton getInstancia() {
        return INSTANCIA;
    }

    public Lista<Usuario> getListaEmpleados() {
        return listaUsuario;
    }
   

     

    public void escribirObjectoPersona() {
        try {
            FileOutputStream archivo
                    = new FileOutputStream("Persona.dat");
            ObjectOutputStream escritor
                    = new ObjectOutputStream(archivo);
            escritor.writeObject(listaUsuario);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Lista<Usuario> leerUsuario() {
        try {
            FileInputStream archivo
                    = new FileInputStream("Persona.dat");
            ObjectInputStream lector
                    = new ObjectInputStream(archivo);
            return (Lista<Usuario>) lector.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return new Lista<>();
        }
    }
    

}
