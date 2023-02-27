/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;

import javax.swing.JOptionPane;

/**
 *
 * @author gabriellavetencourt
 */
public class Dijkstra {

    private int[][] peso;
    private int[] ultimo;
    private int[] D;
    private int[] ruta;
    private boolean[] F;
    private int s, n; // vértice origen y número de vértices
    private Grafo g;
    private Mapa m;
    private int destino;
    private int resultado;
    

    public Dijkstra(Grafo g, int origen, int destino, Mapa m) {
        n = g.getAlmacenes().length;
        s = origen;
        this.peso = g.getMatrizD();
        this.ultimo = new int[n];
        this.D = new int[n];
        this.F = new boolean[n];
        this.destino = destino;
        this.g = g;
        this.m = m;
        this.ruta = new int[n];
        


        for (int i = 0; i < ruta.length; i++) {
            ruta[i] = 999;
        }
        

        for (int i = 0; i < peso.length; i++) {
            for (int j = 0; j < peso[i].length; j++) {
                if (peso[i][j] == 0) {
                    peso[i][j] = 999999999;
                }

            }
        }

       
    }

    public Dijkstra(Grafo g, int origen, boolean d, int destino, Mapa m) {
        n = g.getAlmacenes().length;
        s = origen;
        peso = g.getMatrizD();
        ultimo = new int[n];
        D = new int[n];
        F = new boolean[n];
        this.g = g;
        this.destino = destino;
        this.m = m;

        for (int i = 0; i < ruta.length; i++) {
            ruta[i] = 999;
        }

       

        for (int i = 0; i < peso.length; i++) {
            for (int j = 0; j < peso[i].length; j++) {
                if (peso[i][j] == 0) {
                    peso[i][j] = 999999999;
                }

            }
        }

        

    }

    public void caminoMinimos() {
        //valores iniciales

        for (int i = 0; i < n; i++) {
            F[i] = false;
            D[i] = peso[s][i];
            ultimo[i] = s;
        }

        F[s] = true;
        D[s] = 0;
        // Pasos para marcar los n-1 vértices 
        for (int i = 0; i < n; i++) {

            int v = minimo();

            /* selecciona vértice no marcado
de menor distancia */
            F[v] = true;
            // actualiza distancia de vértices no marcados
            for (int w = 0; w < n; w++) {
                if (!F[w]) {
                    

                    if ((D[v] + peso[v][w]) < D[w]) {

                        D[w] = D[v] + peso[v][w];

                        ultimo[w] = v;

                    }
                }
            }

        }

        for (int i = 0; i < n; i++) {
            if (i == destino) {
                if (D[i] != 999999999 && D[i] != 0) {
                    this.resultado = D[i];
                } else {
                    JOptionPane.showMessageDialog(m, "No existe ninguna ruta hacia el destino ingresado");
                }

            }

        }

    }
//para ek minimo se pone el 999 pq representa una distancia muy grande y es para q no se pase
    private int minimo() {
        int mx = 999999999;
        int v = 1;
        for (int j = 0; j < n; j++) {

            if (!F[j] && (D[j] <= mx) && D[j] != 0) {

                mx = D[j];
                v = j;
            }
        }
//        System.out.println(v);
        return v;
    }
    
    
//recursiva, va volviendo al final del ultimo
    public void recuperaCamino(int v) {
        int anterior = ultimo[v];
        if (v != s) {
            recuperaCamino(anterior); // vuelve al último del último
            for (int k = 1; k < n; k++) {
                if (ruta[k] == 999 ) {
                    ruta[k] = v;
                    break;
                }
            }
            
        } else {

           ruta[0]=s;
        }

    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int[] getRuta() {
        return ruta;
    }

    public void setRuta(int[] ruta) {
        this.ruta = ruta;
    }

}
