/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;

/**
 *
 * @author gabriellavetencourt
 */
public class Nodo {
    
    private int dato;
    private Nodo pNext;

    public Nodo(int dato) {
        this.dato = dato;
        pNext = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getPnext() {
        return pNext;
    }

    public void setPnext(Nodo pnext) {
        this.pNext = pnext;
    }

    
}
