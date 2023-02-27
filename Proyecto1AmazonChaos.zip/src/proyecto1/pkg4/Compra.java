/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.pkg4;

import javax.swing.JOptionPane;

/**
 *
 * @author gabriellavetencourt
 */
public class Compra extends javax.swing.JFrame {

    /**
     * Creates new form Compra
     */
    Mapa m;
    String listaProductos = "";
    int tamaño;
    String[] arreglo;
    Dijkstra d;
    int cantidadComprar = 0;
    Almacen[] almacenesRuta;
    ItemCompra[] items;
    String content2 = "";
    int x;
    String[] listaAlmacenes;
    int[] cantidades;

    public Compra(Mapa m) {

        initComponents();
        this.m = m;
        this.almacenesRuta = new Almacen[m.getGrafo().almacenes.length];
        this.arreglo = m.getGrafo().listarProductos().split("\n");
        this.tamaño = m.getGrafo().listarProductos().split("\n").length;
        this.cantidadComprar = 0;
        this.cantidades = new int[this.arreglo.length];
        this.eliminarRepetidos();
        this.cantidadTotal();
        for (int i = 0; i < tamaño; i++) {
            this.listaProductos += arreglo[i] + "   Cantidad: " + this.cantidades[i] + "\n";
        }
        this.jTextAreaProductos.setEditable(false);
        this.jTextAreaResumenCompra.setEditable(false);
        this.fin.setVisible(false);
        this.anadir.setVisible(false);
        jTextAreaProductos.setText(listaProductos);
        this.items = new ItemCompra[50];
        this.listaAlmacenes = this.getAlmacenList();
        this.escogerAlmacen();

    }

    //almacen origen, al cual van a llegar todos los productos para ESA compra
    public void escogerAlmacen() {
        this.x = JOptionPane.showOptionDialog(null, "Seleccione el almacen en donde desea comprar el producto.",
                "Selección de Almacen",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, listaAlmacenes, listaAlmacenes[0]);
        this.content2 += "ALMACEN SELECCIONADO: " + listaAlmacenes[x] + "\n\n";
        jTextAreaResumenCompra.setText(content2);
        this.anadir.setVisible(true);
    }

    public void eliminarRepetidos() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = i + 1; j < tamaño; j++) {
                if (arreglo[i].equals(arreglo[j])) {
                    arreglo[j] = arreglo[tamaño - 1];
                    tamaño--;
                    j--;
                }
            }
        }
    }

    public int getCantProd(String nombre) {
        int cantidad = 0;

        for (int i = 0; i < this.m.getGrafo().getAlmacenesSize(); i++) {
            if (!this.m.getGrafo().almacenes[i].getProductos().EsVacio()) {
                Producto aux = this.m.getGrafo().almacenes[i].getProductos().getpFirst();
                for (int j = 0; j < this.m.getGrafo().almacenes[i].getProductos().getSize(); j++) {
                    if (aux.getNombre().equalsIgnoreCase(nombre)) {
                        cantidad += aux.getCantidad();
                    }
                    aux = aux.getPnext();

                }
            }

        }
        return cantidad;
    }

    //la cantidad total existente para un producto sin importar el almacen
    public void cantidadTotal() {

        for (int i = 0; i < cantidades.length; i++) {
            cantidades[i] = this.getCantProd(this.arreglo[i]);

        }
    }

    public boolean existeProducto(String nombre) {
        boolean found = false;
        for (int i = 0; i < tamaño; i++) {
            System.out.println(nombre + " " + arreglo[i]);
            if (nombre.trim().equalsIgnoreCase(arreglo[i])) {
                found = true;
                break;
            }
        }

        return found;
    }

    public void vaciarArreglo() {
        for (int i = 0; i < this.almacenesRuta.length; i++) {
            this.almacenesRuta[i] = null;
        }
    }

    //chequea si existe la ruta
    public boolean check(String nombre) {
        boolean found = false;
        for (int i = 0; i < this.almacenesRuta.length; i++) {
            if (this.almacenesRuta[i] != null) {
                if (this.almacenesRuta[i].getNombre().equalsIgnoreCase(nombre)) {
                    found = true;
                }
            }
        }
        return found;
    }

    
    public void agregar(Almacen a) {
        for (int i = 0; i < this.almacenesRuta.length; i++) {
            if (this.almacenesRuta[i] == null) {
                this.almacenesRuta[i] = a;
                break;
            }
        }

    }

    //esto da la ruta mas corta que va a hacer para llevar un producto al almacen del que compro
    public void solicitud(int x, String[] almacenes, String producto) {

        int posD = m.getGrafo().getIndex(almacenes[x]);
        int pos = 0;
        int[] distancias = new int[m.getGrafo().almacenes.length];
        String[] rutas = new String[m.getGrafo().almacenes.length];
        int rutaMinima = 999999999;
        Almacen almacenCercano = m.getGrafo().buscarAlmacen(almacenes[x]);
        String rutaFinal = "";
        String[] rutaArreglo;

        for (int i = 0; i < almacenes.length; i++) {
            String ruta = "";

            if (i != x && m.getGrafo().almacenes[i].existeProducto(producto) && !this.check(almacenes[i])) {
                System.out.println("mjmcmcmmc");
                pos = m.getGrafo().getIndex(almacenes[i]);

                d = new Dijkstra(m.getGrafo(), pos, posD, m);
                d.caminoMinimos();
                d.recuperaCamino(posD);
                for (int k = 0; k < d.getRuta().length; k++) {
//                    System.out.print(d.getRuta()[k] + " ");
                    if (d.getRuta()[k] != 999) {
                        ruta = ruta + " " + m.getGrafo().getAlmacenes()[d.getRuta()[k]].getCodigoA();
                    } else {
                        break;
                    }

                }
                if (d.getResultado() < rutaMinima) {
                    rutaMinima = d.getResultado();
                    rutaFinal = ruta;
                    rutaArreglo = ruta.split(" ");
                    System.out.println(rutaArreglo[0]);
                    almacenCercano = m.getGrafo().buscarAlmacen(rutaArreglo[1]);

                }
                System.out.println("La ruta mas corta" + ruta + "\n es " + d.getResultado() + "KM");
            }

        }
        m.getGrafo().buscarAlmacen(almacenCercano.getNombre()).getProductos().reservarProducto(producto, this.cantidadComprar, this, rutaMinima, almacenCercano);
        this.content2 += "        La ruta mínima es " + rutaFinal + "\n";
        this.agregar(almacenCercano);

        System.out.println("La ruta mas corta FINAL" + rutaFinal + "\n es " + rutaMinima + "KM " + this.cantidadComprar);

//        JOptionPane.showMessageDialog(m, "La ruta mas corta" + ruta + "\n es " + d.getResultado() + "KM");
    }

    //chequea si puedes agg el producto al carrito
    public boolean checkCarrito(Almacen almacen, Producto producto) {
        boolean found = false;

        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                if (this.items[i].getAlmacen().getNombre().equalsIgnoreCase(almacen.getNombre()) && this.items[i].getProducto().getNombre().equalsIgnoreCase(producto.getNombre())) {
                    found = true;
                    break;
                }
            }

        }

        return found;
    }

    
    public void agregarCarrito(Almacen almacen, int cantidad, Producto producto, int distancia) {

        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == null) {
                if (!this.checkCarrito(almacen, producto)) {
                    this.items[i] = new ItemCompra(almacen, producto, cantidad, distancia);
                    break;
                }

            }
        }

    }


    public String[] getAlmacenList() {
        String[] lista = new String[m.getGrafo().almacenes.length];
        int cont = 0;
        for (int i = 0; i < lista.length; i++) {
            if (m.getGrafo().almacenes[i] != null) {
                lista[i] = m.getGrafo().almacenes[i].getNombre();
                cont++;
            }

        }
        String[] lista2 = new String[cont];

        for (int j = 0; j < lista.length; j++) {
            if (m.getGrafo().almacenes[j] != null) {
                lista2[j] = lista[j];

            }

        }

        return lista2;
    }

    public void imprimir() {

        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                System.out.println(this.items[i].getProducto().getNombre() + " " + this.items[i].getAlmacen().getNombre() + " " + this.items[i].getCantidad() + " " + this.items[i].getDistancia() + "KM");
            }
        }
    }

    public void comprar() {
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                this.items[i].getAlmacen().getProductos().comprarProducto(this.items[i].getProducto().getNombre(), this.items[i].getCantidad());
            }
        }

        JOptionPane.showMessageDialog(this, "Su compra ha sido exitosa.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaProductos = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        inputCantidad = new javax.swing.JTextField();
        inputProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        anadir = new javax.swing.JButton();
        fin = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaResumenCompra = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaProductos.setColumns(20);
        jTextAreaProductos.setRows(5);
        jScrollPane1.setViewportView(jTextAreaProductos);

        jLabel1.setText("Lista de Productos");

        jLabel2.setText("Cantidad");

        jLabel3.setText("Nombre del Producto");

        anadir.setText("Añadir");
        anadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirActionPerformed(evt);
            }
        });

        fin.setText("Finalizar");
        fin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finActionPerformed(evt);
            }
        });

        jTextAreaResumenCompra.setColumns(20);
        jTextAreaResumenCompra.setRows(5);
        jScrollPane3.setViewportView(jTextAreaResumenCompra);

        jLabel4.setText("Resumen de Compra");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1)
                .addGap(414, 414, 414)
                .addComponent(jLabel4))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3))
                    .addComponent(inputProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2))
                    .addComponent(inputCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(anadir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(fin)))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(inputProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(inputCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(anadir)
                        .addGap(11, 11, 11)
                        .addComponent(fin))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //anade al carrito
    private void anadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirActionPerformed
        // TODO add your handling code here:

        try {
            String producto = inputProducto.getText();
            int cantidad = Integer.parseInt(inputCantidad.getText());
            this.cantidadComprar = cantidad;
            if (cantidad >= 0) {
                if (this.existeProducto(producto)) {

                    if (m.getGrafo().cantidadMaxProducto(producto) >= cantidad) {
                        this.content2 += "- " + Integer.toString(cantidad) + " " + producto + "(s)" + "\n";
                        this.fin.setVisible(true);
                        m.getGrafo().buscarAlmacen(listaAlmacenes[x]).getProductos().reservarProducto(producto, cantidad, this, 0, m.getGrafo().buscarAlmacen(listaAlmacenes[x]));
                        if (this.cantidadComprar > 0) {

                            do {
                                this.solicitud(x, listaAlmacenes, producto);
                            } while (this.cantidadComprar > 0);

                        }

                        this.imprimir();
                        content2 += "\n\n";
                        inputCantidad.setText("");
                        inputProducto.setText("");
                        jTextAreaResumenCompra.setText(content2);
                    } else {
                        JOptionPane.showMessageDialog(this, "La cantidad del producto es mayor a la disponible en la red.");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "El producto no existe.");
                }
            } else {

                JOptionPane.showMessageDialog(this, "Ingrese un número positivo.");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Algo salió mal.");
        }


    }//GEN-LAST:event_anadirActionPerformed

    //elimina los productos de las listas
    private void finActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finActionPerformed
        // TODO add your handling code here:
        try {
            this.comprar();
            this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Algo salió mal.");
        }

    }//GEN-LAST:event_finActionPerformed

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
            java.util.logging.Logger.getLogger(Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anadir;
    private javax.swing.JButton fin;
    private javax.swing.JTextField inputCantidad;
    private javax.swing.JTextField inputProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaProductos;
    private javax.swing.JTextArea jTextAreaResumenCompra;
    // End of variables declaration//GEN-END:variables
}
