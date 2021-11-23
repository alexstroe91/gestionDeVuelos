/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionvuelos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DAM2A-28
 */
public class ventanaDescripcion extends javax.swing.JFrame {

    Connection conexion = null;

    /**
     * Creates new form ventanaDescripcion
     */
    public ventanaDescripcion() {
        initComponents();
        if (conectarBaseDatos()) {
            JOptionPane.showMessageDialog(this, "Conexion a la base de datos con exito.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
        }
    }

    ////////////////////////////////////////        METODOS        ////////////////////////////////////////
    public boolean conectarBaseDatos() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gestiondevuelos", "root", "");
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage() + " 2");
            return false;
        }
    }

    public void informaBD() {
        DatabaseMetaData dbmd = null;
        try {
            dbmd = conexion.getMetaData();
            String texto = ("                                                INFORMACION SOBRE LA BASE DE DATOS: \n"
                    + "\n    - NOMBRE: " + dbmd.getSchemas() + "\n"
                    + "\n    - DRIVER: " + dbmd.getDriverName() + "\n"
                    + "\n    - URL: " + dbmd.getURL() + "\n"
                    + "\n    - USUARIO: " + dbmd.getUserName() + "\n"
                    + "\n    - TABLA: " + dbmd.getTables(null, "gestiondevuelos", null, null));
            txtArea.setText(texto);
        } catch (SQLException ex) {
            Logger.getLogger(ventanaDescripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void informaTabla() {
        DatabaseMetaData dbmd = null;
        String texto = "";
        try {
            dbmd = conexion.getMetaData();
            ResultSet resultado = dbmd.getTables(null, "gestiondevuelos", "%", null);

            String esquema = "";
            while (resultado.next()) {
                ResultSet columna = dbmd.getColumns(null, "gestiondevuelos", resultado.getString("TABLE_NAME"), null);
                while (columna.next()) {
                    esquema += columna.getString("COLUMN_NAME") + "    ||||    ";

                }
                texto += ("                      -------------------       INFORMACION SOBRE LA TABLA:       -------------------\n"
                        + "\n    - CATALOGO: " + resultado.getString("TABLE_CAT") + "\n"
                        + "\n    - ESQUEMA: " + esquema + "\n"
                        + "\n    - NOMBRE TABLA: " + resultado.getString("TABLE_NAME") + "\n"
                        + "\n    - TIPO: " + resultado.getString("TABLE_TYPE") + "\n");
                txtArea.setText(texto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventanaDescripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void informaEstructura() {
        String tabla = "";
        String texto = "";
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet resultado = dbmd.getTables(null, "gestiondevuelos", null, null);

            while (resultado.next()) {
                texto += "                      -------------------       INFORMACION SOBRE LA ESTRUCTURA:       -------------------\n";
                texto += "\nNOMBRE DE LA TABLA: " + resultado.getString("TABLE_NAME");
                ResultSet resulcolum = dbmd.getColumns(null, "gestiondevuelos", tabla, null);
                while (resulcolum.next()) {
                    texto += ("\n    - NOMBRE COLUMNA: " + resulcolum.getString("COLUMN_NAME")
                            + "\n" + "    - TIPO COLUMNA: " + resulcolum.getString("TYPE_NAME")
                            + "\n" + "    - TAMAÑO: " + resulcolum.getString("COLUMN_SIZE")
                            + "\n" + "    - ES NULA: " + resulcolum.getString(("IS_NULLABLE")));
                }
            }
            txtArea.setText(texto);

        } catch (SQLException ex) {
            Logger.getLogger(ventanaDescripcion.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnTablas = new javax.swing.JButton();
        btnEstructura = new javax.swing.JButton();
        btnBaseDatos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("DESCRIPCION SOBRE LOS DATOS. METADATOS");

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        btnTablas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestionvuelos/imgTablas.jpg"))); // NOI18N
        btnTablas.setText("Tablas");
        btnTablas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTablas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasActionPerformed(evt);
            }
        });

        btnEstructura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestionvuelos/imgEstructura.gif"))); // NOI18N
        btnEstructura.setText("Estructuras");
        btnEstructura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEstructura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEstructura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstructuraActionPerformed(evt);
            }
        });

        btnBaseDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestionvuelos/imgBBDD.gif"))); // NOI18N
        btnBaseDatos.setText("\n\n\n\nBase de Datos");
        btnBaseDatos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBaseDatos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBaseDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaseDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnBaseDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(398, Short.MAX_VALUE)
                    .addComponent(btnEstructura, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBaseDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(btnTablas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnEstructura, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    ////////////////////////////////////////        BOTONES        ////////////////////////////////////////

    private void btnBaseDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaseDatosActionPerformed
        informaBD();
    }//GEN-LAST:event_btnBaseDatosActionPerformed

    private void btnTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasActionPerformed
        informaTabla();
    }//GEN-LAST:event_btnTablasActionPerformed

    private void btnEstructuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstructuraActionPerformed
        informaEstructura();
    }//GEN-LAST:event_btnEstructuraActionPerformed

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
            java.util.logging.Logger.getLogger(ventanaDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaDescripcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaseDatos;
    private javax.swing.JButton btnEstructura;
    private javax.swing.JButton btnTablas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
