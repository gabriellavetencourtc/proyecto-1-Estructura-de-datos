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
public class Ruta {
    
    private Almacen origen;
    private Almacen destino;
    private int distancia;
    private String ruta;
    private Circulo circulo;

    public Ruta(Almacen origen, Almacen destino, int distancia,  String ruta) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.circulo=circulo;
        this.ruta=ruta;
        
    }
    
  

    public Almacen getOrigen() {
        return origen;
    }

    public void setOrigen(Almacen origen) {
        this.origen = origen;
    }

    public Almacen getDestino() {
        return destino;
    }

    public void setDestino(Almacen destino) {
        this.destino = destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Circulo getCirculo() {
        return circulo;
    }

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    }

  
    public String getRuta() {
        return ruta;
    }

    public void setruta(String ruta) {
        this.ruta = ruta;
    }


    
}
