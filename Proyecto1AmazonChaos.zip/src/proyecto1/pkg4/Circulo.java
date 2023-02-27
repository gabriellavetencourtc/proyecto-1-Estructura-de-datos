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
public class Circulo {
    
    private int x;
    private int y;
    String nombre;
    public static final int d=35; //diametro del circulo
    public Color color;

    public Circulo(int x, int y, String nombre) {
        this.x = x;
        this.y = y;
        this.nombre = nombre; //el nombre es el almacen 
        this.color = Color.PINK; 
    }
    
    public Circulo(int x, int y){
        this.x = x;
        this.y = y;
        this.nombre="";
        this.color = Color.WHITE; 
    }

   
    
    public void Almacen1(Graphics g){
        g.setColor(color);
        g.fillOval(x-d/2, y-d/2, d, d);//rellena el circulo con el color que se establece por el rectangulo especiificado (int x( le resto el radio a la pos), int y ( le resto el radio a la pos), altura(diametro), ancho)
        g.setColor(Color.BLACK);
        g.drawString(nombre, x-d/3, y+d/6); //para que me salga el nombre en el circulo
    }
    
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
            
    
    
    
}
