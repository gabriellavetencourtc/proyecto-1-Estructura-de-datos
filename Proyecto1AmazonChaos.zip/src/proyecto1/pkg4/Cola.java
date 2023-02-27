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
public class Cola {
      private int size;
    private Nodo front;
    private Nodo back;

    public Cola() {
        this.size = 0;
        this.front = null;
        this.back = null;
    }

    public int getSize() {
        return size;
    }

    public Nodo getFront() {
        return front;
    }

    public Nodo getBack() {
        return back;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFront(Nodo front) {
        this.front = front;
    }

    public void setBack(Nodo back) {
        this.back = back;
    }

    //Metodo insertar
    public void insertar(int dato) {
            Nodo nuevo = new Nodo(dato);
        if (esVacio()) {
            this.front = nuevo;
            this.back = nuevo;
            size++;
        } else {
            back.setPnext(nuevo);
            back = nuevo;
            size++;
        }
    }

    //Metodo extraer dato
    public int extraer() {
        System.out.println("uuuuuuuu");
             Nodo aux = front; 
             System.out.println("TTTT "+aux.getDato());
        int index = aux.getDato();
        if (!esVacio()) {
            front = front.getPnext();
            size--;
        }
    return index;
    }

    //Metodo para comprobar que la cola no esta vacia
    public boolean esVacio() {
       return this.front == null;
    }

    //Metodo para contar los elementos de la cola
    public int contar() {
        int contador = 0;
        Nodo c = this.front;
        while (c != null) {
            contador++;
            c = c.getPnext();
        }
        System.out.println("Numero de datos en la cola: " + contador);
        return contador;
    }

    //Metodo toString
    public String toString() {
        Nodo c = this.front;
        String s = "";
        while (c != null) {
            s = s + c.toString();
            c = c.getPnext();
        }
        return s;
    }
}
