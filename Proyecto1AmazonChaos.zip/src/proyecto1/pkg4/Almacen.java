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
public class Almacen {
    
    private String nombre;
    private String codigoA;
    private Lista productos;

    public Almacen(String nombre) {
        this.nombre = nombre;
        this.codigoA=nombre;
        this.productos=new Lista();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCodigoA() {
        return codigoA;
    }

    public void setCodigoA(String codigoA) {
        this.codigoA = codigoA;
    }
    
     
    public Lista getProductos() {
        return productos;
    }

    public void setProductos(Lista productos) {
        this.productos = productos;
    }
    
    //REVISA SI EXISTE PROD POR NOMBRE
    public boolean existeProducto(String producto){
    boolean found=false;
    if(!this.productos.EsVacio()){
          Producto aux = this.productos.getpFirst();
            for (int i = 0; i < this.productos.getSize(); i++) {
                if(aux.getNombre().equalsIgnoreCase(producto)){
                    found=true;
                }
                aux = aux.getPnext();

            }
    }
    
    return found;
}

    
    
}
