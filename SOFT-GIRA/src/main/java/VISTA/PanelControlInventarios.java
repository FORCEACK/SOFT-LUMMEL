
package VISTA;

import CONTROLADOR.ProductoDAO;
import java.awt.event.MouseAdapter;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author kille
 */
public class PanelControlInventarios extends javax.swing.JPanel {

    private ProductoDAO productoDAO = new ProductoDAO();

    /**
     * Creates new form ControlInventario
     */
    public PanelControlInventarios() {
        initComponents();
        cargarTablaProductos();
        tablaInventario.getColumnModel().getColumn(8).setCellRenderer(new SemaforoCellRenderer());
    }

    public void cargarTablaProductos() {
        productoDAO.listarProductosEnTabla(tablaInventario);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(31, 34, 111));
        jButton1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar-producto.png"))); // NOI18N
        jButton1.setText("Nuevo Producto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(31, 34, 111));
        jButton2.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        jButton2.setText("Modificar/Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(31, 34, 111));
        jButton3.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar.png"))); // NOI18N
        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(31, 34, 111));
        jButton4.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sobresalir.png"))); // NOI18N
        jButton4.setText("Reporte de Iventario");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(31, 34, 111));
        jButton5.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/politica-de-devoluciones.png"))); // NOI18N
        jButton5.setText("Devoluciones");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Còdijo", "Producto", "unidad", "Presentaciòn", "Costo", "P. Venta", "Stock ", "Minimo", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tablaInventario);

        jPanel1.setBackground(new java.awt.Color(31, 34, 111));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PANEL DE INVENTARIO");
        jPanel1.add(jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(7, 7, 7)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
String filtro = jTextField1.getText().trim();
        if (filtro.isEmpty()) {
            cargarTablaInventario();
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tablaInventario.getModel();
        modelo.setRowCount(0);

        String sql = """
            SELECT a.articulo_id, a.nombre, u.nombre AS presentacion, a.precio_Origen, a.precio_Venta, a.stock, a.stockMinimo 
            FROM Articulo a 
            INNER JOIN UnidadMedida u ON a.idMedida = u.idMedida 
            WHERE a.nombre LIKE ? OR a.articulo_id = ?
            """;

        try (java.sql.Connection con = MODELO.ConexionBD.conectar();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, "%" + filtro + "%");
            pst.setString(2, filtro);
            
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("articulo_id");
                    String nombre = rs.getString("nombre");
                    String presentacion = rs.getString("presentacion");
                    double costo = rs.getDouble("precio_Origen");
                    double precio = rs.getDouble("precio_Venta");
                    double stock = rs.getDouble("stock");
                    double minimo = rs.getDouble("stockMinimo");
                    
                    String estado = (stock <= 0) ? "Agotado" : (stock <= minimo) ? "Stock Bajo" : "Disponible";

                    modelo.addRow(new Object[]{id, nombre, presentacion, costo, precio, stock, minimo, estado});
                }
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      int filaSeleccionada = tablaInventario.getSelectedRow();

        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto de la tabla para registrar la devolución.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idArticulo = Integer.parseInt(tablaInventario.getValueAt(filaSeleccionada, 0).toString());
            String nombreArticulo = tablaInventario.getValueAt(filaSeleccionada, 1).toString();
            double stockActual = Double.parseDouble(tablaInventario.getValueAt(filaSeleccionada, 5).toString());

            String[] tiposDevolucion = {"Devolución de Cliente (Entra al inventario +)", "Devolución a Proveedor (Sale del inventario -)"};
            int tipoSeleccionado = javax.swing.JOptionPane.showOptionDialog(
                this,
                "Producto: " + nombreArticulo + "\nStock actual: " + stockActual + "\n\nSelecciona el tipo de devolución:",
                "Gestión de Devoluciones",
                javax.swing.JOptionPane.DEFAULT_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null, tiposDevolucion, tiposDevolucion[0]
            );

            if (tipoSeleccionado == 0 || tipoSeleccionado == 1) {
                String inputCantidad = javax.swing.JOptionPane.showInputDialog(this, "Ingresa la cantidad a devolver:");

                if (inputCantidad != null && !inputCantidad.trim().isEmpty()) {
                    double cantidad = Double.parseDouble(inputCantidad.trim());

                    if (cantidad <= 0) {
                        javax.swing.JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double nuevoStock = (tipoSeleccionado == 0) ? (stockActual + cantidad) : (stockActual - cantidad);

                    if (nuevoStock < 0) {
                        javax.swing.JOptionPane.showMessageDialog(this, "El stock no puede quedar en negativo.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String sql = "UPDATE Articulo SET stock = ? WHERE articulo_id = ?";
                    try (java.sql.Connection con = MODELO.ConexionBD.conectar();
                         java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

                        pst.setDouble(1, nuevoStock);
                        pst.setInt(2, idArticulo);
                        pst.executeUpdate();

                        String mensajeExito = (tipoSeleccionado == 0) 
                            ? "¡Devolución de cliente registrada! Stock sumado: +" + cantidad 
                            : "¡Devolución a proveedor registrada! Stock restado: -" + cantidad;

                        javax.swing.JOptionPane.showMessageDialog(this, mensajeExito);
                        cargarTablaInventario();

                    } catch (java.sql.SQLException e) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar la base de datos: " + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingresa una cantidad numérica válida.", "Error de formato", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        jTextField1.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// 1. Obtener la ventana principal contenedora
    java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);

    // 2. Crear una ventana modal (JDialog)
    javax.swing.JDialog dialog = new javax.swing.JDialog(parentWindow, "Nuevo Producto", java.awt.Dialog.ModalityType.APPLICATION_MODAL);

    // 3. Insertar el panel N_Proc dentro de la ventana
    VISTA.N_Proc panel = new VISTA.N_Proc();
    dialog.setContentPane(panel);

    // 4. Ajustar tamaño y centrar en la pantalla
    dialog.pack();
    dialog.setLocationRelativeTo(parentWindow);
    dialog.setResizable(false);

    // 5. Mostrar la ventana emergente
    dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 

int filaSeleccionada = tablaInventario.getSelectedRow();

        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto de la tabla para eliminar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idArticulo = Integer.parseInt(tablaInventario.getValueAt(filaSeleccionada, 0).toString());
            String nombreArticulo = tablaInventario.getValueAt(filaSeleccionada, 1).toString();

            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                this, 
                "¿Estás seguro de ELIMINAR PERMANENTEMENTE el producto:\n\"" + nombreArticulo + "\"?\n(Se eliminarán los registros de detalle de venta asociados).", 
                "Confirmar Eliminación Total", 
                javax.swing.JOptionPane.YES_NO_OPTION, 
                javax.swing.JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                try (java.sql.Connection con = MODELO.ConexionBD.conectar()) {
                    try (java.sql.Statement st = con.createStatement()) {
                        st.execute("SET FOREIGN_KEY_CHECKS = 0;");
                    }

                    String sqlDetalle = "DELETE FROM detalleventa WHERE articulo_id = ?";
                    try (java.sql.PreparedStatement pstDetalle = con.prepareStatement(sqlDetalle)) {
                        pstDetalle.setInt(1, idArticulo);
                        pstDetalle.executeUpdate();
                    }

                    String sqlArticulo = "DELETE FROM Articulo WHERE articulo_id = ?";
                    try (java.sql.PreparedStatement pstArticulo = con.prepareStatement(sqlArticulo)) {
                        pstArticulo.setInt(1, idArticulo);
                        pstArticulo.executeUpdate();
                    }

                    try (java.sql.Statement st = con.createStatement()) {
                        st.execute("SET FOREIGN_KEY_CHECKS = 1;");
                    }

                    javax.swing.JOptionPane.showMessageDialog(this, "¡Producto eliminado permanentemente!");
                    cargarTablaInventario();

                } catch (java.sql.SQLException e) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar en la base de datos: " + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar la selección: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  int filaSeleccionada = tablaInventario.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto de la tabla.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idArticulo = Integer.parseInt(tablaInventario.getValueAt(filaSeleccionada, 0).toString());
            String nombreActual = tablaInventario.getValueAt(filaSeleccionada, 1).toString();
            double costoActual = Double.parseDouble(tablaInventario.getValueAt(filaSeleccionada, 3).toString().replace("$", "").trim());
            double precioActual = Double.parseDouble(tablaInventario.getValueAt(filaSeleccionada, 4).toString().replace("$", "").trim());
            double stockActual = Double.parseDouble(tablaInventario.getValueAt(filaSeleccionada, 5).toString());
            double minimoActual = Double.parseDouble(tablaInventario.getValueAt(filaSeleccionada, 6).toString());

            javax.swing.JTextField txtNombre = new javax.swing.JTextField(nombreActual, 20);
            javax.swing.JTextField txtCosto = new javax.swing.JTextField(String.valueOf(costoActual), 10);
            javax.swing.JTextField txtPrecio = new javax.swing.JTextField(String.valueOf(precioActual), 10);
            javax.swing.JTextField txtStock = new javax.swing.JTextField(String.valueOf(stockActual), 10);
            javax.swing.JTextField txtMinimo = new javax.swing.JTextField(String.valueOf(minimoActual), 10);

            javax.swing.JPanel panelEdicion = new javax.swing.JPanel(new java.awt.GridLayout(0, 2, 10, 10));
            panelEdicion.add(new javax.swing.JLabel("Nombre:"));
            panelEdicion.add(txtNombre);
            panelEdicion.add(new javax.swing.JLabel("Precio Origen ($):"));
            panelEdicion.add(txtCosto);
            panelEdicion.add(new javax.swing.JLabel("Precio Venta ($):"));
            panelEdicion.add(txtPrecio);
            panelEdicion.add(new javax.swing.JLabel("Stock actual:"));
            panelEdicion.add(txtStock);
            panelEdicion.add(new javax.swing.JLabel("Stock Mínimo:"));
            panelEdicion.add(txtMinimo);

            int resultado = javax.swing.JOptionPane.showConfirmDialog(
                this, panelEdicion, "Modificar / Editar Producto (ID: " + idArticulo + ")",
                javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.PLAIN_MESSAGE
            );

            if (resultado == javax.swing.JOptionPane.OK_OPTION) {
                String nuevoNombre = txtNombre.getText().trim();
                double nuevoCosto = Double.parseDouble(txtCosto.getText().trim());
                double nuevoPrecio = Double.parseDouble(txtPrecio.getText().trim());
                double nuevoStock = Double.parseDouble(txtStock.getText().trim());
                double nuevoMinimo = Double.parseDouble(txtMinimo.getText().trim());

                if (nuevoNombre.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "UPDATE Articulo SET nombre = ?, precio_Origen = ?, precio_Venta = ?, stock = ?, stockMinimo = ? WHERE articulo_id = ?";
                try (java.sql.Connection con = MODELO.ConexionBD.conectar();
                     java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
                    
                    pst.setString(1, nuevoNombre);
                    pst.setDouble(2, nuevoCosto);
                    pst.setDouble(3, nuevoPrecio);
                    pst.setDouble(4, nuevoStock);
                    pst.setDouble(5, nuevoMinimo);
                    pst.setInt(6, idArticulo);
                    
                    pst.executeUpdate();

                    javax.swing.JOptionPane.showMessageDialog(this, "¡Producto modificado correctamente!");
                    cargarTablaInventario();

                } catch (java.sql.SQLException e) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar la base de datos: " + e.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos.", "Error de formato", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte de inventario como...");
        fileChooser.setSelectedFile(new java.io.File("Inventario_SoftGira.csv"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File archivoToSave = fileChooser.getSelectedFile();
            
            try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(archivoToSave))) {
                DefaultTableModel modelo = (DefaultTableModel) tablaInventario.getModel();
                int numCols = modelo.getColumnCount();
                int numRows = modelo.getRowCount();
                
                for (int i = 0; i < numCols; i++) {
                    pw.print(modelo.getColumnName(i) + (i == numCols - 1 ? "" : ","));
                }
                pw.println();
                
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        Object valor = modelo.getValueAt(i, j);
                        pw.print((valor != null ? valor.toString() : "") + (j == numCols - 1 ? "" : ","));
                    }
                    pw.println();
                }
                
                javax.swing.JOptionPane.showMessageDialog(this, "¡Inventario exportado exitosamente! 📊");
                
            } catch (java.io.IOException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al exportar el archivo: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
       
    }

    private void cargarTablaInventario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
  // Clase para renderizar los colores de semáforo en la columna de Estado
    public class SemaforoCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            try {
                // Índices actualizados para 9 columnas: Stock (6) y Mínimo (7)
                double stock = Double.parseDouble(table.getValueAt(row, 6).toString());
                double minimo = Double.parseDouble(table.getValueAt(row, 7).toString());

                if (!isSelected) {
                    if (stock <= 0) {
                        setBackground(new java.awt.Color(255, 204, 204)); // Fondo rojo suave
                        setForeground(new java.awt.Color(153, 0, 0));     // Texto rojo oscuro
                        setText("Agotado");
                    } else if (stock <= minimo) {
                        setBackground(new java.awt.Color(255, 255, 204)); // Fondo amarillo/naranja
                        setForeground(new java.awt.Color(153, 102, 0));   // Texto naranja oscuro
                        setText("Por Agotarse");
                    } else {
                        setBackground(new java.awt.Color(204, 255, 204)); // Fondo verde suave
                        setForeground(new java.awt.Color(0, 102, 0));     // Texto verde oscuro
                        setText("Disponible");
                    }
                }
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setFont(getFont().deriveFont(java.awt.Font.BOLD)); // Negrita opcional
            } catch (Exception e) {
                setText(value != null ? value.toString() : "");
            }

            return this;
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaInventario;
    // End of variables declaration//GEN-END:variables
}
