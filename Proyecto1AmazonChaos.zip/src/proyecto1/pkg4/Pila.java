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
public class Pila {
    
    
    private Nodo top;
    private int size;

    //Constructor 
    public Pila() {
        this.top = null;
        this.size = 0;
    }

    //Obtener top
    public Nodo getTop() {
        return top;
    }

    //Asignar top
    public void setTop(Nodo cima) {
        this.top = cima;
    }
    
     //Obtener tamaño
    public int getSize() {
        return size;
    }
    
    //Asignar tamaño
    public void setSize(int size){
        this.size = size;
    }

    //Apilar 
    public void apilar(int dato) {
        Nodo aux = new Nodo(dato);
        aux.setPnext(this.top);
        setTop(aux);
        size++;
    }

    //Desapilar 
    public int desapilar() {
        int info = top.getDato();
        this.top = top.getPnext();
        size--;
    return info;
    }

  
    //Obtener valor del top o cima
    public int top() {
        return top.getDato();
    }

    //Esta vacio
    public boolean isEmpty() {
        return top == null;
    }


    //Mostrar pila
    public void mostrarPila() {       
        Nodo pAux = this.top;
        while (pAux != null) {
            System.out.println(pAux.getDato());
            pAux = pAux.getPnext();
        }
        
    }

    //sumergir valor
    public void sumergir(int x) {
        if (!isEmpty()) {
            int aux = this.desapilar();
            this.sumergir(x);
            this.apilar(aux);
        } else {
            this.apilar(x);
        }
    }

    //Invertir pila
    public void invertirPila() {
        if (!isEmpty()) {
            int aux = this.desapilar();
            this.invertirPila();
            this.sumergir(aux);
        }
    }

    //Vaciar pila
    public void vaciarPila() {
        while (!isEmpty()) {
            this.desapilar();
        }
    }
    
}
