/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;

import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriellavetencourt
 */
public class Grafo {

    Almacen[] almacenes;
    int[][] matrizA;
    int[][] matrizD;
    Circulo circulo;
    private Mapa mapa;
    Ruta[] ruta;
    private int numRutas;
    private int almacenesSize;
    String[] rutas = new String[10];

    public Grafo(Mapa m) {

        this.almacenes = new Almacen[50]; //limite de arreglo
        this.ruta = new Ruta[50];
        this.matrizA = new int[almacenes.length][almacenes.length];
        this.matrizD = new int[almacenes.length][almacenes.length];
        this.almacenesSize = 0;
        this.mapa = m;

        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizA[i].length; j++) {
                matrizA[i][j] = 0;
            }
        }

        for (int i = 0; i < matrizD.length; i++) {
            for (int j = 0; j < matrizD[i].length; j++) {
                matrizD[i][j] = 0;
            }
        }

    }

    public int getAlmacenesSize() {
        return almacenesSize;
    }

    public boolean esVacio() {
        boolean vacio = true;
        for (int i = 0; i < almacenes.length; i++) {
            if (almacenes[i] != null) {
                vacio = false;
            }
        }

        return vacio;
    }

    public void agregarAlmacen(String nombre) {
        boolean agregado = false;

        for (int i = 0; i < almacenes.length; i++) {
            if (almacenes[i] == null) {
                System.out.println(nombre);
                this.almacenes[i] = new Almacen(nombre);
                this.almacenesSize += 1;
                agregado = true;
                break;
            }
        }
        if (!agregado) {
            JOptionPane.showMessageDialog(null, "Número maximo de almacenes.");
        }

    }

    public void agregarRuta(String codigoO, String codigoD, int distancia, String nRuta) {
      
        boolean existeO = false;
        boolean existeD = false;
        boolean existeV = false;
        int posO = 0;
        int posD = 0;

        for (int i = 0; i < almacenes.length; i++) {

            if (almacenes[i].getCodigoA().equalsIgnoreCase(codigoO)) {
                existeO = true;
                posO = i;

                break;
            }
        }

        for (int j = 0; j < almacenes.length; j++) {
            if (almacenes[j].getCodigoA().equalsIgnoreCase(codigoD)) {
                existeD = true;
                posD = j;

                break;
            }
        }

        for (int m = 0; m < ruta.length; m++) {
            if (ruta[m] != null) {
                if (ruta[m].getOrigen().getNombre() == codigoO && ruta[m].getDestino().getNombre() == codigoD) {
                    existeV = true;
                    break;
                }
            }
        }

        if (existeO && existeD && matrizA[posO][posD] == 0 && !existeV) {
     
            for (int k = 0; k < ruta.length; k++) {
                if (ruta[k] == null) {
                    ruta[k] = new Ruta(this.almacenes[posO], this.almacenes[posD], distancia, nRuta);
                  
                    matrizA[posO][posD] = 1;
                    matrizD[posO][posD] = this.ruta[k].getDistancia();
                    break;
                }
            }

            for (int j = 0; j < mapa.getCirculo().size(); j++) {

                if (mapa.getCirculo().elementAt(j).getNombre().equalsIgnoreCase(codigoO)) {
                    posO = j;
                }
            }
            for (int i = 0; i < mapa.getCirculo().size(); i++) {

                if (mapa.getCirculo().elementAt(i).getNombre().equalsIgnoreCase(codigoD)) {
                    posD = i;

                }
            }

            mapa.getArista().add(new Arista(mapa.getCirculo().elementAt(posO).getX(), mapa.getCirculo().elementAt(posO).getY(), mapa.getCirculo().elementAt(posD).getX(), mapa.getCirculo().elementAt(posD).getY(), codigoO + codigoD, Integer.toString(distancia)));
            mapa.repaint();

        } else {
            JOptionPane.showMessageDialog(mapa, "Su solicitud no ha podido ser realizada. Verifique los datos ingresados.");
        }

    }

//existe ruta
    public boolean existeArista(int i, int j) {
    
        if (matrizD[i][j] != 0) {
            return true;
        } else if (matrizD[i][j] == 0) {
            return false;
        }
        return false;
    }

    //get la lista de almacenes
    public Almacen[] getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(Almacen[] almacenes) {
        this.almacenes = almacenes;
    }

    public int[][] getMatrizA() {
        return matrizA;
    }

    public void setMatrizA(int[][] matrizA) {
        this.matrizA = matrizA;
    }

    public Circulo getCirculo() {
        return circulo;
    }

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Ruta[] getRuta() {
        return ruta;
    }

    public void setRuta(Ruta[] ruta) {
        this.ruta = ruta;
    }

    public int[][] getMatrizD() {
        return matrizD;
    }

    public Almacen buscarAlmacen(String nombre) {
        Almacen aux = null;
        boolean found = false;

        for (int i = 0; i < almacenes.length; i++) {
            if (almacenes[i] != null) {
                if (almacenes[i].getNombre().equalsIgnoreCase(nombre)) {

                    aux = almacenes[i];
                    found = true;
                    break;

                }
            }
        }
        System.out.println(found);
        if (found) {
            return aux;
        } else {
            return null;
        }

    }

    public void setMatrizD(int[][] matrizD) {
        this.matrizD = matrizD;
    }
    
    
    //imprime la matriz
    public void ImprimirTabla() {

        for (int i = 0; i < almacenes.length; i++) {

            for (int j = 0; j < almacenes.length; j++) {

                System.out.print(" " + matrizD[i][j] + " ");
            }

            System.out.println("\n");
        }

    }
//indica el indice, osea la posicion del almacen
    public int getIndex(String nombre) {
        int index = -1;

        for (int i = 0; i < almacenes.length; i++) {
            if (almacenes[i] != null) {
                System.out.println(nombre.trim().equals(almacenes[i].getNombre().trim()));
                if (nombre.trim().equalsIgnoreCase(almacenes[i].getNombre().trim())) {

                    index = i;
                    System.out.println("aqui" + index);
                    break;
                }
            }

        }

        return index;
    }

    
    public String listarProductos() {
        String content = "";

        for (int i = 0; i < this.almacenes.length; i++) {

            if (this.almacenes[i] != null) {

                if (!this.almacenes[i].getProductos().EsVacio()) {

                    Producto aux = this.almacenes[i].getProductos().getpFirst();
                    for (int j = 0; j < this.almacenes[i].getProductos().getSize(); j++) {
                        if (aux != null) {
                            if (aux.getCantidad() > 0) {
                                content += aux.getNombre() + "\n";
                            }

                            aux = aux.getPnext();
                        }

                    }

                }
            }
        }

        return content;
    }

    public String[] listarAlmacenes(String producto) {
        String[] lista = new String[this.almacenes.length];
        int size = 0;
        for (int i = 0; i < this.almacenes.length; i++) {

            if (this.almacenes[i] != null) {

                if (!this.almacenes[i].getProductos().EsVacio()) {

                    Producto aux = this.almacenes[i].getProductos().getpFirst();
                    for (int j = 0; j < this.almacenes[i].getProductos().getSize(); j++) {
                        if (aux != null) {

                            if (producto.trim().equalsIgnoreCase(aux.getNombre()) && aux.getCantidad() > 0) {
                                lista[size] = this.almacenes[i].getNombre();
                                size++;
                            }
                            aux = aux.getPnext();
                        }

                    }

                }
            }
        }

        String[] lista2 = new String[size];

        for (int j = 0; j < size; j++) {
            lista2[j] = lista[j];
        }

        return lista2;

    }

    //cantidad maxima de productos que hay registrados
    public int cantidadMaxProducto(String producto) {
        int cantidad = 0;
        for (int i = 0; i < this.almacenes.length; i++) {

            if (this.almacenes[i] != null) {

                if (!this.almacenes[i].getProductos().EsVacio()) {

                    Producto aux = this.almacenes[i].getProductos().getpFirst();
                    for (int j = 0; j < this.almacenes[i].getProductos().getSize(); j++) {
                        if (aux != null) {

                            if (producto.trim().equalsIgnoreCase(aux.getNombre())) {

                                cantidad += aux.getCantidad();
                            }
                            aux = aux.getPnext();
                        }

                    }

                }
            }
        }

        return cantidad;

    }

    //recorrido en amplitud
    public String recorrerAnchura(String nombre) {
        int valOrigen;
        Integer w = null;
        int intW;
        int[] arrVisitados = null;
        Almacen[] verts = this.almacenes; //arreglo con los vertices del grafo
        String temp = "";

        try {
            valOrigen = this.getIndex(nombre);

            if (valOrigen < 0) {
                throw new Exception("Vertice no existe");
            }

            Cola cola = new Cola();
            arrVisitados = new int[this.almacenesSize];

            for (int i = 0; i < this.almacenesSize; i++) {
                arrVisitados[i] = -1; //Los vértices se marcan con -1

            };

            arrVisitados[valOrigen] = 0; //vertice de partida se inicializa en 0
            cola.insertar(valOrigen); //se encola un nodo con el índice del vértice 

            for (int i = 0; i < this.almacenesSize; i++) {

                if (arrVisitados[i] == -1) {
                    System.out.println(!cola.esVacio());
                    while (!cola.esVacio()) {

                        w = (Integer) cola.extraer();

                        intW = w;

//                      System.out.println("Vertice" + verts[intW].getNombre() + "visitado");
                        Almacen vert = verts[intW];
//                        Lista lista = vert.getProductos();

                        temp += "- Almacen" + " " + vert.getNombre() + "\n";
                        temp = vert.getProductos().ImprimirReporte(temp);
                        temp += "\n\n";

                        //Se encolan los adyacentes
                        for (int j = 0; j < this.almacenesSize; j++) {

                            if ((w != j) && (existeArista(w, j) && arrVisitados[j] == -1)) { //Utilizar la funcion de añadir a la matriz                            

                                int valNodo = this.getIndex(verts[j].getNombre());
                                cola.insertar(valNodo);
                                arrVisitados[j] = 0;
                            }

                        }

                    }

                }

            }

            return temp;

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error, no se pudo recorrer el grafo [Anchura]");
        }
        return temp;

    }

    //recorrido en profundidad
    public String recorrerProfundidad(String nombre) {
        int valOrigen, valSig = 0;
        int[] arrVisitados = null;
        Almacen[] verts = this.almacenes;
        Pila pila = new Pila();
        arrVisitados = new int[this.almacenesSize];
        String temp = "";

        try {
            valOrigen = this.getIndex(nombre);

            if (valOrigen < 0) {
                throw new Exception("Vertice no existe");
            }

            for (int i = 0; i < this.almacenesSize; i++) {
                arrVisitados[i] = -1; //Los vértices se marcan con -1            
            }

            arrVisitados[valOrigen] = 0; //vertice de partida se inicializa en 0

            pila.apilar(valOrigen);

            while (!pila.isEmpty()) {
                Integer sig;
                sig = (Integer) pila.desapilar();
                valSig = sig;
//                System.out.println("Vertice" + " " + verts[valSig].getNombre()+ " " + "visitado");

                Almacen vert = verts[valSig];
                temp += "- Almacen" + " " + vert.getNombre() + "\n";
                temp = vert.getProductos().ImprimirReporte(temp);
                temp += "\n\n";

                for (int j = 0; j < this.almacenesSize; j++) {
                    if ((valSig != j) && (this.existeArista(valSig, j) && arrVisitados[j] == -1)) {
                        int valNodo = this.getIndex(verts[j].getNombre());
                        pila.apilar(valNodo);
                        arrVisitados[j] = 0;
                    }
                }
            }

            return temp;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no se pudo recorrer el grafo [Profundidad]");
        }

        return temp;
    }

}
