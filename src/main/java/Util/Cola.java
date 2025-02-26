/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.Serializable;

/**
 *
 * @author ANDRES FELIPE
 */
public class Cola<T> implements Serializable ,Icola<T> {
    
    Nodo<T> primero;
    int size;
    
    public Cola() {
        this.primero = null;
        this.size = 0;
    }


    public void Enqueuo(T dato) {
         Nodo<T> nuevo = new Nodo<>(dato); 
        if (primero == null) {
            this.primero = nuevo;
        } else {
            Nodo<T> axum = primero;
            while (axum.siguiente != null) {
                axum = axum.siguiente;
            }
            axum.siguiente = nuevo;
        }
        size++;
    }

     public boolean isEmpty(){
        return primero ==null;
    }
    
    @Override
    public T Doequeo() {
    if (isEmpty()) {
        return null; 
    }
    
    Nodo<T> aux = primero; 
    primero = primero.siguiente; 
    size--; 
    
    return aux.dato; 
}

    
     public int size() {
        return this.size;
    }
      public T get(int indice) {
   

    if (indice < 0 || indice >= size) { 
        throw new ArrayIndexOutOfBoundsException(indice);
    }

    Nodo<T> axum = primero;
    for (int c = 0; c < indice; c++) {
        axum = axum.siguiente;
    }
    return axum.dato;
}
    @Override
    public T Peak() {
        return primero.dato;
    }

   

    
   
    
    
}
