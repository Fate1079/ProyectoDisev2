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
// T para que me deje almacenar lo que  sea




public class Lista<T> implements Serializable, ILista<T> {
    Nodo<T> primero;
    int size;

    public Lista() {
        this.primero = null;
        this.size = 0;
    }

    @Override
    public void add(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);  // Corregido para especificar el tipo genérico
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

    @Override
    public int size() {
        return this.size;
    }

    @Override
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
    public void addpos(int indice, T dato) {  
        if (indice < 0 || indice > size) {
            throw new ArrayIndexOutOfBoundsException(indice);
        } else {
            Nodo<T> nuevo = new Nodo<>(dato);  
            if (indice == 0) {
                nuevo.siguiente = primero;
                primero = nuevo;
            } else {
                int c = 0;
                Nodo<T> aux2 = primero;
                while (c < indice - 1) {
                    aux2 = aux2.siguiente;
                    c++;
                }
                nuevo.siguiente = aux2.siguiente;
                aux2.siguiente = nuevo;
                size++;
            }
        }
    }

    @Override
    public void remove(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException(indice);
        } else {
            if (indice == 0) {
                primero = primero.siguiente;
                size--;
            } else {
                int c = 0;
                Nodo<T> aux = primero;
                while (c < indice - 1) {
                    aux = aux.siguiente;
                    c++;
                }
                aux.siguiente = aux.siguiente.siguiente;
                size--;
            }
        }
    }

    @Override
    public boolean isEmpty() { 
        return primero == null;
    }
}

