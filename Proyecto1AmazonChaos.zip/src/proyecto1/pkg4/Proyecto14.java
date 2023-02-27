/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;


import javax.swing.JFrame;

/**
 *
 * @author gabriellavetencourt
 */
public class Proyecto14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    
        // Definir ventana
        Mapa m=new Mapa();
   
       Agregar a=new Agregar(m);
      
      
       a.getContentPane().add(m);
 
       
        a.setVisible(true);
    }
    
}
