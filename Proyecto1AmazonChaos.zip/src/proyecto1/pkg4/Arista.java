/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author gabriellavetencourt
 */
public class Arista {

    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private String ruta;
    private String distancia;
    public Color color;
    private boolean eliminar;

    public Arista(int x1, int y1, int x2, int y2, String ruta, String distancia) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.ruta = ruta;
        this.color=Color.PINK;
        this.eliminar=false;
        this.distancia=distancia;

    }
    
    public Arista(int x1, int y1, int x2, int y2, String ruta, boolean eliminar, String distancia) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.ruta = ruta;
        this.distancia=distancia;
        this.color=Color.WHITE;
        this.eliminar=true;
        

    }
    
    public void pintarL(Graphics g) {
        g.setColor(color);

        g.drawLine(x1, y1, x2, y2);

        int xMenor = menor(x1, x2);
        int xMayor = mayor(x1, x2);
        int yMenor = menor(y1, y2);
        int yMayor = mayor(y1, y2);

        int distanciaVertical = yMayor + yMenor;
        int distanciaHorizontal = xMayor + xMenor;
        
        g.setColor(Color.BLACK);
        
        g.drawString(ruta+" - "+distancia, distanciaHorizontal / 2, distanciaVertical / 2);
      

    }

    
    private int menor(int n1, int n2) {
        if (n1 < n2) {
            return n1;

        } else {
            return n2;
        }
    }

    private int mayor(int n1, int n2) {
        if (n1 > n2) {
            return n1;
        } else {
            return n2;
        }
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
