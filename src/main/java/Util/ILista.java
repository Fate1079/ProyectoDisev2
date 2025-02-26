/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Util;

/**
 *
 * @author ANDRES FELIPE
 */
public interface ILista<T> {
    void add(T dato);
    T get(int indice);
    void addpos(int indice, T dato);  
    void remove(int indice);
    boolean isEmpty();  
    int size();
}

