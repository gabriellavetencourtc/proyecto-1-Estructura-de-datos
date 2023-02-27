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
public class Lista {

    private Producto pFirst;
    private Producto pLast;
    private int size;

    public Producto getpFirst() {
        return pFirst;
    }

    public void setpFirst(Producto pFirst) {
        this.pFirst = pFirst;
    }

    public Producto getPlast() {
        return pLast;
    }

    public void setPlast(Producto plast) {
        this.pLast = plast;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Lista() {
        this.pFirst = null;
        this.pLast = null;
        this.size = 0;
    }

    public boolean EsVacio() {
        return pFirst == null;
    }
    
    //encuentra productos por su nombre
public boolean encontrarProducto(String nombre){
    boolean found=false;
   if(!EsVacio()){
       Producto aux = pFirst;
            for (int i = 0; i < size; i++) {
                if(aux.getNombre().equalsIgnoreCase(nombre)){
                    found=true;
                }
                aux = aux.getPnext();

            }
   }
          
    return found;    
    
}


    public void InsertarFinal(String nombre, int cantidad) {

        Producto nuevo = new Producto(nombre, cantidad);

        if (EsVacio()) {
            pFirst = nuevo;
            pLast = nuevo;
        } else {
            Producto aux = pLast;
            aux.setPnext(nuevo);
            pLast = nuevo;

        }

        size += 1;

    }

    //imprime lista
    public void Imprimir() {

        if (!EsVacio()) {
            Producto aux = pFirst;
            for (int i = 0; i < size; i++) {
                System.out.print(aux.getNombre() + " " + aux.getCantidad());
                aux = aux.getPnext();

            }

        } else {
            System.out.println("La lista esta vacia");
        }
    }
    
    //imprime el producto con su cantidad en gestion stock
       public String ImprimirReporte(String temp) {

        if (!EsVacio()) {
            Producto aux = pFirst;
            for (int i = 0; i < size; i++) {
                temp+="      "+aux.getNombre() + " " + "  Cantidad: "+aux.getCantidad()+"\n";
                aux = aux.getPnext();

            }

        } else {
            System.out.println("La lista esta vacia");
        }
        
        return temp;
    }

       

       //reserva el producto para no eliminarlo hasta quee la compra no finalice 
    public void reservarProducto(String nombre, int cantidad, Compra compra, int distancia, Almacen almacen) {
 
        Producto aux = pFirst;
        for (int i = 0; i < size; i++) {
                   
            if (nombre.trim().equalsIgnoreCase(aux.getNombre().trim())) {
                aux.reservar(cantidad, compra, distancia,almacen);
                break;
            }

            aux = aux.getPnext();

        }

    }
    
    
        public void comprarProducto(String nombre, int cantidad) {
 
        Producto aux = pFirst;
        for (int i = 0; i < size; i++) {
                  
            if (nombre.trim().equalsIgnoreCase(aux.getNombre().trim())) {
                aux.comprar(cantidad);
                break;
            }

            aux = aux.getPnext();

        }

    }

}
