/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 *
 * @author gabriellavetencourt
 */
public class Mapa extends JPanel {


    private ArrayList<Integer> aux_nRuta = new ArrayList();
    private ArrayList<Integer> aux_nRuta2 = new ArrayList();

    private ArrayList<Integer> aux_distancia = new ArrayList();

    private ArrayList aux_codigoA = new ArrayList();
    private ArrayList<Integer> aux_posX = new ArrayList();
    private ArrayList<Integer> aux_posY = new ArrayList();
    private ArrayList aux_codigoO = new ArrayList();
    private ArrayList aux_codigoD = new ArrayList();
  


    Almacen auxOrigen;
    Almacen auxDestino;

    private Vector<Circulo> Circulo; //utilizo la clase vector para hacer un array de objetos (import)
    private Vector<Arista> Arista;
    private Point p1;
    private Point p2;
    private Ruta ruta;
    private String codigoA;
    private Grafo grafo;
    private String nRuta;

    int pos = 0;
    int num = 0;

    public boolean crearNodo = false;

    public Mapa() {

        this.Circulo = new Vector<>();
        this.Arista = new Vector<>();
        this.grafo = new Grafo(this);
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.PINK));
        this.setSize(800, 550);
        this.p1 = null;
        this.p2 = null;
        this.setBackground(Color.WHITE);
        this.setLocation(125, 50);

       

      

    }
    public void reset(){
        this.Circulo = new Vector<>();
        this.Arista = new Vector<>();
        this.grafo = new Grafo(this);
        this.p1 = null;
        this.p2 = null;
    }
   
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circulo objCirculo : Circulo) {
            objCirculo.Almacen1(g);
        }
        for (Arista linea : Arista) {
            //linea.existeArista(matrizA, pos1, pos2);
            linea.pintarL(g);
        }
    }

    public Vector<Circulo> getCirculo() {
        return Circulo;
    }

    public void setCirculo(Vector<Circulo> Circulo) {
        this.Circulo = Circulo;
    }

    public Vector<Arista> getArista() {
        return Arista;
    }

    public void setArista(Vector<Arista> Arista) {
        this.Arista = Arista;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public String getCodigoA() {
        return codigoA;
    }

    public void setCodigoA(String codigoA) {
        this.codigoA = codigoA;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isCrearNodo() {
        return crearNodo;
    }

    public void setCrearNodo(boolean crearNodo) {
        this.crearNodo = crearNodo;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public String getnRuta() {
        return nRuta;
    }

    public void setnRuta(String nRuta) {
        this.nRuta = nRuta;
    }

    public ArrayList<Integer> getAux_nRuta() {
        return aux_nRuta;
    }

    public void setAux_nRuta(ArrayList<Integer> aux_nRuta) {
        this.aux_nRuta = aux_nRuta;
    }

    public ArrayList<Integer> getAux_nRuta2() {
        return aux_nRuta2;
    }

    public void setAux_nRuta2(ArrayList<Integer> aux_nRuta2) {
        this.aux_nRuta2 = aux_nRuta2;
    }

    public ArrayList<Integer> getAux_distancia() {
        return aux_distancia;
    }

    public void setAux_distancia(ArrayList<Integer> aux_distancia) {
        this.aux_distancia = aux_distancia;
    }

    public Almacen getAuxOrigen() {
        return auxOrigen;
    }

    public void setAuxOrigen(Almacen auxOrigen) {
        this.auxOrigen = auxOrigen;
    }

    public Almacen getAuxDestino() {
        return auxDestino;
    }

    public void setAuxDestino(Almacen auxDestino) {
        this.auxDestino = auxDestino;
    }

    public ArrayList getAux_codigoA() {
        return aux_codigoA;
    }

    public void setAux_codigoA(ArrayList aux_codigoA) {
        this.aux_codigoA = aux_codigoA;
    }

    public ArrayList<Integer> getAux_posX() {
        return aux_posX;
    }

    public void setAux_posX(ArrayList<Integer> aux_posX) {
        this.aux_posX = aux_posX;
    }

    public ArrayList<Integer> getAux_posY() {
        return aux_posY;
    }

    public void setAux_posY(ArrayList<Integer> aux_posY) {
        this.aux_posY = aux_posY;
    }

    public ArrayList getAux_codigoO() {
        return aux_codigoO;
    }

    public void setAux_codigoO(ArrayList aux_codigoO) {
        this.aux_codigoO = aux_codigoO;
    }

    public ArrayList getAux_codigoD() {
        return aux_codigoD;
    }

    public void setAux_codigoD(ArrayList aux_codigoD) {
        this.aux_codigoD = aux_codigoD;
    }

    
    
    
    
    
    

}
