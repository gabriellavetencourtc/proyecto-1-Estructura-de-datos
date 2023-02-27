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
public class ItemCompra {
    
    //cada item para comprar con todos los datos
    
   private Almacen almacen;
   private Producto producto;
   private int cantidad;
   private int distancia;

    public ItemCompra(Almacen almacen, Producto producto, int cantidad, int distancia) {
        this.almacen = almacen;
        this.producto = producto;
        this.cantidad = cantidad;
        this.distancia = distancia;
    }
        public ItemCompra(Almacen almacen, Producto producto, int cantidad) {
        this.almacen = almacen;
        this.producto = producto;
        this.cantidad = cantidad;
        this.distancia = 0;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    
}
