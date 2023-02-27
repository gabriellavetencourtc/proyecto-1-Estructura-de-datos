/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.*;

/**
 *
 * @author gabriellavetencourt
 */
public class Cargar extends javax.swing.JFrame {

    /**
     * Creates new form Cargar
     */
    private String aux_codigoO;
    private String aux_codigoD;
    Agregar agregar;
    private String aux_almacenes;
    private int aux_distancia;
    private String aux_producto;
    private int aux_cantidad;
    private String nombreAlmacen;

    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    Archivo a = new Archivo();
    Mapa m;

    public Cargar(Mapa m, Agregar agregar) {
        initComponents();
        this.setResizable(false);
        this.setTitle("Archivos");
        this.m = m;
        this.agregar = agregar;
          this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    //guarda el txt existente y si hay cambios en el proyecto puede guardar la info nueva y reescribir el txt
    public void Actualizar() {
       this.m.getGrafo().ImprimirTabla();
        int input = 1;
        do {
            if (seleccionado.showDialog(this, "Actualizar") == JFileChooser.APPROVE_OPTION) {
                File archivo = new File(seleccionado.getSelectedFile().toString());

            
                System.out.println(seleccionado.getSelectedFile());
                if (archivo.exists()) {
                    input = JOptionPane.showConfirmDialog(null, "El Archivo ya existe. ¿Desea Sobreescribirlo?");
                    // 0=yes, 1=no, 2=cancel

                }
                if (input == 0 || !archivo.exists()) {
                    try {
                        input = 0;
                        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write("Almacenes;");
                        bw.newLine();
                        for (int i = 0; i < m.getGrafo().getAlmacenes().length; i++) {
                            if (m.getGrafo().getAlmacenes()[i] != null) {
                                bw.write("Almacen " + m.getGrafo().getAlmacenes()[i].getNombre() + ":");
                                bw.newLine();
                                if (!m.getGrafo().getAlmacenes()[i].getProductos().EsVacio()) {
                                    Producto aux = m.getGrafo().getAlmacenes()[i].getProductos().getpFirst();
                                    for (int j = 0; j < m.getGrafo().getAlmacenes()[i].getProductos().getSize(); j++) {
                                        System.out.print(aux.getNombre() + " ");
                                        if (aux.getPnext() != null) {
                                            bw.write(aux.getNombre() + "," + Integer.toString(aux.getCantidad()));

                                        } else {
                                            bw.write(aux.getNombre() + "," + Integer.toString(aux.getCantidad()) + ";");
                                        }
                                        aux = aux.getPnext();
                                        bw.newLine();
                                    }

                                }
                                ;

                            }
                        }
                        bw.write("Rutas;");
                        bw.newLine();
                        for (int i = 0; i < m.getGrafo().getRuta().length; i++) {
                            //escribe los datos en el archivo
                            if (m.getGrafo().getRuta()[i] != null) {
                                bw.write(m.getGrafo().getRuta()[i].getOrigen().getNombre() + "," + m.getGrafo().getRuta()[i].getDestino().getNombre() + "," + m.getGrafo().getRuta()[i].getDistancia());
                                bw.newLine();
                            }
                        }

                        bw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Cargar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

  
            }
        } while (input == 1);

    }

    //abre el archivo y lo muestra en el text area y con eso se grafican los almacenes y rutas
    public void Abrir() {
        Random random = new Random();
        if (seleccionado.showDialog(this, "Abrir Archivo") == JFileChooser.APPROVE_OPTION) {

            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {

                    String contenido = a.Abrir(archivo);
                    jTextAreaArchivo.setText(contenido);
                    try {

                        BufferedReader bf = new BufferedReader(new FileReader(seleccionado.getSelectedFile().toString()));
                        String aux;
                        String bfRead = bf.readLine();
                        aux = bfRead;

                       
                        while (aux != null) {
                            if (aux.equalsIgnoreCase("Almacenes;")) {
                                do {
                                    aux = bf.readLine();

                                    if (!aux.contains(";") && aux.contains(":")) {
                                        aux = aux.replace(":", "");
                                        aux = aux.replace("Almacen", "");
                                        nombreAlmacen = aux.trim();

                                        m.getGrafo().agregarAlmacen(nombreAlmacen);
                                        m.getCirculo().add(new Circulo(random.nextInt(700), random.nextInt(500), nombreAlmacen.trim()));

                                        
                                    }

                                    m.repaint();
                                    if (aux.contains(",")) {
                                        aux = aux.replace(";", "");
                                        String[] arreglo = aux.split(",");
                                        aux_producto = arreglo[0];
                                        aux_cantidad = Integer.parseInt(arreglo[1]);

                                        m.getGrafo().buscarAlmacen(nombreAlmacen).getProductos().InsertarFinal(aux_producto, aux_cantidad);
                                    }
                                } while (!aux.equalsIgnoreCase("Rutas;"));
                            }
                            if (aux.contains("Rutas;")) {
                                do {
                                    aux = bf.readLine();
                                    if (aux != null) {
                                        String[] arreglo2 = aux.split(",");

                                        if (arreglo2.length == 3) {

                                            aux_codigoO = arreglo2[0];
                                            aux_codigoD = arreglo2[1];
                                            aux_distancia = Integer.parseInt(arreglo2[2]);

                                            m.getGrafo().agregarRuta(aux_codigoO, aux_codigoD, aux_distancia, aux_codigoO + aux_codigoD);
                                        }
                                    }
//                               
                                } while (aux != null);
                            }

                            aux = bf.readLine();
                        }

                        agregar.aggAlmacen.setEnabled(true);
                        agregar.jMenu3.setEnabled(true);
                        agregar.jButton3.setEnabled(true);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione un archivo de texto");
                }

            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abrirArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaArchivo = new javax.swing.JTextArea();
        GuardarArchivo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        abrirArchivo.setText("Abrir Archivo");
        abrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirArchivoActionPerformed(evt);
            }
        });

        jTextAreaArchivo.setColumns(20);
        jTextAreaArchivo.setRows(5);
        jScrollPane1.setViewportView(jTextAreaArchivo);

        GuardarArchivo.setText("Guardar/ Actualizar");
        GuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(abrirArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GuardarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(abrirArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GuardarArchivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoActionPerformed
        // TODO add your handling code here:

        if (m.getGrafo().esVacio()) {
            this.Abrir();
        } else {
            String[] options = {"Guardar", "Subir Archivo", "Cancelar"};
            int x = JOptionPane.showOptionDialog(null, "Desea guardar los cambios realizados, antes de cargar un nuevo archivo?",
                    "ALERTA",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            System.out.println(x);

            if (x == 0) {
                this.Actualizar();
                m.reset();
                this.Abrir();

            } else if (x == 1) {
                m.reset();
                this.Abrir();
            }
        }
     

    }//GEN-LAST:event_abrirArchivoActionPerformed

    private void GuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarArchivoActionPerformed

        this.Actualizar();
    }//GEN-LAST:event_GuardarArchivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GuardarArchivo;
    private javax.swing.JButton abrirArchivo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaArchivo;
    // End of variables declaration//GEN-END:variables
}
