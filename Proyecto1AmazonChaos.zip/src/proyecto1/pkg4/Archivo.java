/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;


import java.io.*;

/**
 *
 * @author gabriellavetencourt
 */
public class Archivo {

    String texto;
    String[] arreglo;

    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;

    public Archivo() {
    }

    
    public String Abrir(File archivo) {
        String contenido = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                contenido += caracter;
            }
        } catch (Exception e) {

        }
        return contenido;
    }



  
}
