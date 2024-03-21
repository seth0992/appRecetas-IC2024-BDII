/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelJDBC.CategoriaJDBC;
import modelJDBC.IngredienteJDBC;

/**
 *
 * @author seth
 */
public class frmIngredientes extends javax.swing.JFrame {

    boolean nuevo = true; //Indicador para saber si es una ingrediente nueva
    int id = 0; //Almacenara el Id del ingrediente a modificar/eliminar
    IngredienteJDBC ingredientejdbc = new IngredienteJDBC(); // Instancia de jdbc para el uso de los métodos

    /**
     * Creates new form frmIngredientes
     */
    public frmIngredientes() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreIng = new javax.swing.JTextField();
        txtUnidadMedidaIng = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnLimpiarIng = new javax.swing.JButton();
        btnEliminarIng = new javax.swing.JButton();
        btnGuardarIng = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaIngredientes = new javax.swing.JTable();
        txtBuscarIng = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Control de Ingredientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Ingredientes"));

        jLabel3.setText("Nombre del Ingediente:");

        jLabel4.setText("Unidad de Medida");

        btnLimpiarIng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clean.png"))); // NOI18N
        btnLimpiarIng.setText("Limpiar");
        btnLimpiarIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarIngActionPerformed(evt);
            }
        });

        btnEliminarIng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        btnEliminarIng.setText("Eliminar");
        btnEliminarIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarIngActionPerformed(evt);
            }
        });

        btnGuardarIng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/save.png"))); // NOI18N
        btnGuardarIng.setText("Guardar");
        btnGuardarIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarIngActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNombreIng))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtUnidadMedidaIng))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardarIng, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarIng, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarIng, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombreIng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUnidadMedidaIng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarIng, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiarIng, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarIng, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Ingredientes "));

        tblListaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblListaIngredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblListaIngredientesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaIngredientes);

        txtBuscarIng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarIngKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarIngKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Buscar:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarIng)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarIng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblListaIngredientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaIngredientesMousePressed

        id = Integer.parseInt(tblListaIngredientes.getValueAt(tblListaIngredientes.getSelectedRow(), 0).toString());
        txtNombreIng.setText(tblListaIngredientes.getValueAt(tblListaIngredientes.getSelectedRow(), 1).toString());
        txtUnidadMedidaIng.setText(tblListaIngredientes.getValueAt(tblListaIngredientes.getSelectedRow(), 2).toString());

        if (id > 0) {
            nuevo = false;
        }
    }//GEN-LAST:event_tblListaIngredientesMousePressed

    private void txtBuscarIngKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarIngKeyPressed

    }//GEN-LAST:event_txtBuscarIngKeyPressed

    private void txtBuscarIngKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarIngKeyReleased
        String filtro = txtBuscarIng.getText();
        //Se invoca el método para cargar los datos pero se le pasa como parametro el texto a buscar
        cargarDatos(filtro);
    }//GEN-LAST:event_txtBuscarIngKeyReleased

    private void btnLimpiarIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarIngActionPerformed
        limpiarDatos();
    }//GEN-LAST:event_btnLimpiarIngActionPerformed

    private void btnEliminarIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarIngActionPerformed
        if(id == 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un ingrediente para eliminarla", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(this, "Esta segura que desea eliminar el ingrediente seleccionada?");

        if(opcion == 0){
            ingredientejdbc.eliminarIngrediente(id);
            JOptionPane.showMessageDialog(this, "Se elimino el ingrediente");
            limpiarDatos();
            cargarDatos(null);
        }
    }//GEN-LAST:event_btnEliminarIngActionPerformed

    private void btnGuardarIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarIngActionPerformed

        String nombreIng = txtNombreIng.getText(); //Obtener el dato del textfield
        String unidadMedida = txtUnidadMedidaIng.getText();
        
        if (nombreIng.equals("")) {
            JOptionPane.showMessageDialog(this, "Debes digitar una nombre para la ingrediente");
            return;
        }
          if (unidadMedida.equals("")) {
            JOptionPane.showMessageDialog(this, "Debes digitar una unidad de medida para la ingrediente");
            return;
        }
        int row = 0;
        if (nuevo) {

            row = ingredientejdbc.registrarIngredientes(nombreIng,unidadMedida); //Llamar al metodo que encarga de registrar la categoria

            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Se Registro el ingrediente");
            } else {
                JOptionPane.showMessageDialog(this, "No Se Registro el Ingrediente", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            row = ingredientejdbc.modificarIngrediente(id, nombreIng, unidadMedida); //Llamar al metodo que encarga de registrar la categoria
            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Se modifico el ingrediente");
            } else {
                JOptionPane.showMessageDialog(this, "No Se modifico el ingrediente", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        limpiarDatos();
        cargarDatos(null);
    }//GEN-LAST:event_btnGuardarIngActionPerformed
   
    public void cargarDatos(String ingrediente) {

        //VCarga el modelo de la tabla con sus datos, gracias al metodo ConsultarCategoria del JDBC
        DefaultTableModel modelo = ingredientejdbc.consultarIngrediente(ingrediente);
        tblListaIngredientes.setModel(modelo);
    }
    
    public void limpiarDatos() {
        txtNombreIng.setText("");
        txtUnidadMedidaIng.setText("");
        txtBuscarIng.setText("");
        id = 0;
        nuevo = true;
    }
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
            java.util.logging.Logger.getLogger(frmIngredientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmIngredientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmIngredientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmIngredientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmIngredientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarIng;
    private javax.swing.JButton btnGuardarIng;
    private javax.swing.JButton btnLimpiarIng;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaIngredientes;
    private javax.swing.JTextField txtBuscarIng;
    private javax.swing.JTextField txtNombreIng;
    private javax.swing.JTextField txtUnidadMedidaIng;
    // End of variables declaration//GEN-END:variables
}
