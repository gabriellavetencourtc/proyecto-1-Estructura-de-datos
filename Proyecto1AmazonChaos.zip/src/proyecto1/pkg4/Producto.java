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
public class Producto {

    private String nombre;
    private int cantidad;
    private Producto pNext;
    

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.pNext = null;
    }

    public Producto(String[] productos) {
        this.nombre = productos[0];
        this.cantidad = Integer.parseInt(productos[1]);
        this.pNext = null;

    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        System.out.println("entre");
        this.cantidad = cantidad;
    }
    
//reserva los productos para que no se resten de las listas hasta que le den al boton de finalizar compra
    public void reservar(int cantidad, Compra compra, int distancia, Almacen almacen) {

        if (cantidad <= this.cantidad) {

            compra.cantidadComprar -= cantidad;
            compra.agregarCarrito(almacen, cantidad, this, distancia);
            compra.content2 += "       - " + "Almacen: " + almacen.getNombre() + " " + ",Cantidad: " + Integer.toString(cantidad) + " " + ",Distancia: " + Integer.toString(distancia) + "KM" + "\n";

        } else {
            compra.content2 += "       - " + "Almacen: " + almacen.getNombre() + " " + ",Cantidad: " + Integer.toString(cantidad) + " " + ",Distancia: " + Integer.toString(distancia) + "KM" + "\n";
            compra.cantidadComprar -= this.cantidad;
            compra.agregarCarrito(almacen, this.cantidad, this, distancia);

        }
    }

    public void comprar(int cantidad) {

        if (cantidad <= this.cantidad) {
            this.cantidad -= cantidad;

        } else {

            this.cantidad = 0;

        }
    }

    public Producto getPnext() {
        return pNext;
    }

    public void setPnext(Producto pnext) {
        this.pNext = pnext;
    }

}
