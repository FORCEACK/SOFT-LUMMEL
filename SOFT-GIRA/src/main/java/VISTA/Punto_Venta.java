/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VISTA;

import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.print.PrintException;
/**
 *
 * @author mendez
 */
public class Punto_Venta extends javax.swing.JPanel {

    private javax.swing.JPopupMenu popupSugerencias = new javax.swing.JPopupMenu();
    private javax.swing.JList<String> listaSugerencias = new javax.swing.JList<>();
    private java.util.Vector<Integer> idsProductosEncontrados = new java.util.Vector<>();



    public Punto_Venta() {
        initComponents(); // <-- Código generado por NetBeans (No modificar dentro)
    // =========================================================================
    // TODO EL CÓDIGO PERSONALIZADO VA AQUÍ ABAJO (FUERA DE LA ZONA PROTEGIDA):
    // =========================================================================

    // Configuración del JTextArea en el panel de Resumen de Pago
    if (txtAreaTicketPreview == null) {
        txtAreaTicketPreview = new javax.swing.JTextArea();
    }
    txtAreaTicketPreview.setEditable(false);
    txtAreaTicketPreview.setFont(new java.awt.Font("Monospaced", 0, 11));
    
    // Vinculamos el área de texto al ScrollPane que hiciste en NetBeans 
    // (Asegúrate de cambiar "jScrollPane2" si en tu navegador se llama distinto, ej. jScrollPane1)
    if (jScrollPane2 != null) {
        jScrollPane2.setViewportView(txtAreaTicketPreview);
    }

    // Configuración inicial del menú flotante de autocompletado
    listaSugerencias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    popupSugerencias.add(new javax.swing.JScrollPane(listaSugerencias));
    popupSugerencias.setFocusable(false);

    listaSugerencias.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getClickCount() == 1 || evt.getClickCount() == 2) {
               Punto_Venta.this.seleccionarProductoSugerido();
            }
        }
    });


        // Listener para actualizar la vista previa del ticket en tiempo real (Corregido el formato)
        jTable1.getModel().addTableModelListener(new javax.swing.event.TableModelListener() {
            private boolean actualizando = false;

            @Override
            public void tableChanged(javax.swing.event.TableModelEvent e) {
                if (actualizando) return;

                javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable1.getModel();
                int row = e.getFirstRow();
                int column = e.getColumn();
                int totalFilas = modelo.getRowCount();

                // Si el evento indica una fila válida que realmente existe en el modelo actual
                if (row >= 0 && row < totalFilas) {
                    if (column == 2 || column == 3) {
                        actualizando = true;
                        try {
                            Object objCant = modelo.getValueAt(row, 2);
                            Object objPrec = modelo.getValueAt(row, 3);

                            if (objCant != null && objPrec != null && !objCant.toString().trim().isEmpty() && !objPrec.toString().trim().isEmpty()) {
                                double cantidad = Double.parseDouble(objCant.toString().trim());
                                double precio = Double.parseDouble(objPrec.toString().trim());
                                double subtotal = cantidad * precio;
                                modelo.setValueAt(subtotal, row, 4);
                            }
                        } catch (NumberFormatException ex) {
                            // Ignorar mientras el usuario escribe caracteres parciales
                        } finally {
                            actualizando = false;
                        }
                    }
                }
                
                // 1. Forzar acción del botón de cobrar
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        // 2. Forzar acción de la tecla Enter en el buscador
        txtBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (popupSugerencias.isVisible() && listaSugerencias.getModel().getSize() > 0) {
                    listaSugerencias.setSelectedIndex(0);
                    seleccionarProductoSugerido();
                }
            }
        });
        
                // Actualizar la vista previa siempre de forma segura
                actualizarVistaPreviaTicket();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanelIzquierdoContenedor = new javax.swing.JPanel();
        jPanelNorteIzq = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaTicketPreview = new javax.swing.JTextArea();
        jPanelEncabezado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelDerechoGlobal = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        impresion = new java.awt.Checkbox();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanelIzquierdoContenedor.setLayout(new java.awt.BorderLayout());

        jPanelNorteIzq.setLayout(new java.awt.BorderLayout());

        jPanel5.setMinimumSize(new java.awt.Dimension(100, 10));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(260, 500));

        txtAreaTicketPreview.setColumns(20);
        txtAreaTicketPreview.setRows(5);
        jScrollPane2.setViewportView(txtAreaTicketPreview);

        jPanel5.add(jScrollPane2);

        jPanelNorteIzq.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanelEncabezado.setBackground(new java.awt.Color(31, 34, 111));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PUNTO DE VENTA  |");
        jPanelEncabezado.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CAJA PRINCIPAL");
        jPanelEncabezado.add(jLabel2);

        jPanelNorteIzq.add(jPanelEncabezado, java.awt.BorderLayout.NORTH);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/lupa.png"))); // NOI18N
        jPanel1.add(jLabel7);

        jLabel4.setText("DESCRIPCION");
        jPanel1.add(jLabel4);

        txtBuscador.setPreferredSize(new java.awt.Dimension(300, 25));
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyPressed(evt);
            }
        });
        jPanel1.add(txtBuscador);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cajero-automatico.png"))); // NOI18N
        jButton1.setText("F_Corte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jPanelNorteIzq.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanelIzquierdoContenedor.add(jPanelNorteIzq, java.awt.BorderLayout.NORTH);

        jScrollPane1.setAutoscrolls(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Descripción", "Cantidad", "Precio", "Subtotal"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTable1);

        jPanelIzquierdoContenedor.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanelIzquierdoContenedor, java.awt.BorderLayout.CENTER);

        jPanelDerechoGlobal.setPreferredSize(new java.awt.Dimension(350, 700));
        jPanelDerechoGlobal.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("RESUMEN DE PAGO");
        jPanelDerechoGlobal.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 166));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setText("Aplicacion de Descuento     (f12)");
        jPanel3.add(jLabel6, java.awt.BorderLayout.CENTER);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/metodo-de-pago.png"))); // NOI18N
        jButton2.setText("Pagar / Cobrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, java.awt.BorderLayout.PAGE_END);

        impresion.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        impresion.setLabel("Imprimir Ticket al guardar");
        jPanel3.add(impresion, java.awt.BorderLayout.PAGE_START);

        jPanelDerechoGlobal.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel4.add(jPanelDerechoGlobal, java.awt.BorderLayout.EAST);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        javax.swing.table.DefaultTableModel modeloCarrito = (javax.swing.table.DefaultTableModel) jTable1.getModel();

        // 1. Validar que el carrito no esté vacío
        if (modeloCarrito.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "El carrito de compras está vacío.", "Atención", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Calcular el total de la venta recorriendo la tabla
        double totalVenta = 0.0;
        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
            Object subVal = modeloCarrito.getValueAt(i, 4); // Columna del subtotal
            if (subVal != null && !subVal.toString().trim().isEmpty()) {
                try {
                    totalVenta += Double.parseDouble(subVal.toString().trim());
                } catch (NumberFormatException e) {
                    // Ignorar error individual
                }
            }
        }

        // 3. Miniventana para seleccionar el método de pago (Efectivo o Tarjeta)
        String[] opcionesPago = {"Efectivo", "Tarjeta"};
        String tipoPagoSeleccionado = (String) javax.swing.JOptionPane.showInputDialog(
            this,
            "Total a pagar: $" + String.format("%.2f", totalVenta) + "\nSeleccione el método de pago:",
            "Método de Pago",
            javax.swing.JOptionPane.QUESTION_MESSAGE,
            null,
            opcionesPago,
            opcionesPago[0]
        );

        // Si el usuario cancela, se detiene la venta
        if (tipoPagoSeleccionado == null) {
            return;
        }

        double pagoEfectivo = 0.0;
        double cambio = 0.0;

        // 4. Si es Efectivo, pedir monto; si es Tarjeta, pasar automático
        if (tipoPagoSeleccionado.equalsIgnoreCase("Efectivo")) {

            String inputEfectivo = javax.swing.JOptionPane.showInputDialog(
                this,
                "Total a pagar: $" + String.format("%.2f", totalVenta) + "\nIngrese con cuánto paga el cliente:",
                "Cobrar en Efectivo",
                javax.swing.JOptionPane.QUESTION_MESSAGE
            );

            if (inputEfectivo == null) {
                return;
            }

            try {
                if (!inputEfectivo.trim().isEmpty()) {
                    pagoEfectivo = Double.parseDouble(inputEfectivo.trim());
                } else {
                    pagoEfectivo = totalVenta;
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar un valor numérico válido.", "Error de formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que el dinero cubra el total
            if (pagoEfectivo < totalVenta) {
                javax.swing.JOptionPane.showMessageDialog(this, "El dinero entregado es menor al total de la venta.", "Fondos insuficientes", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            cambio = pagoEfectivo - totalVenta;

        } else {
            pagoEfectivo = totalVenta;
            cambio = 0.0;
        }

   
// 5. Validar si existe un corte de caja abierto de forma general
    int idCorteAbierto = CONTROLADOR.ControladorCorte.obtenerCorteAbierto(); // Sin parámetros

    if (idCorteAbierto == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "No existe un corte de caja abierto en el sistema.", "Venta no permitida", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Definimos el usuario actual (puedes ajustarlo según tu lógica de sesión)
    int idUsuarioActual = 1; 

    // Registramos la venta completa
    int idDocumentoGenerado = CONTROLADOR.ControladorVentas.registrarVentaCompleta(jTable1, totalVenta, pagoEfectivo, cambio, tipoPagoSeleccionado, idUsuarioActual);

    // 6. Validar si se guardó con éxito, imprimir ticket y limpiar carrito
    if (idDocumentoGenerado != -1) {

        // Llamada al método auxiliar para imprimir el ticket de venta
        cobrarYGenerarTicket(jTable1, null, totalVenta, tipoPagoSeleccionado, pagoEfectivo, cambio, idDocumentoGenerado);

        // Limpiar la tabla del carrito
        modeloCarrito.setRowCount(0);

        // Miniventana final con el resumen de la venta
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "¡Venta realizada con éxito!\n\n" +
            "N° Ticket: " + idDocumentoGenerado + "\n" +
            "Método de Pago: " + tipoPagoSeleccionado + "\n" +
            "Total: $" + String.format("%.2f", totalVenta) + "\n" +
            (tipoPagoSeleccionado.equalsIgnoreCase("Efectivo") ?
                "Efectivo recibido: $" + String.format("%.2f", pagoEfectivo) + "\nSu cambio: $" + String.format("%.2f", cambio)
                : "Pago con Tarjeta procesado"),
            "Venta Exitosa",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtBuscadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyPressed
        // Si presiona ENTER y hay elementos, selecciona el primero automáticamente
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            if (popupSugerencias.isVisible() && listaSugerencias.getModel().getSize() > 0) {
                listaSugerencias.setSelectedIndex(0);
                seleccionarProductoSugerido();
            }
            return;
        }

        String textoFiltro = txtBuscador.getText().trim();
        if (textoFiltro.isEmpty()) {
            popupSugerencias.setVisible(false);
            return;
        }

        String sql = "SELECT articulo_id, nombre, precio_Venta, stock FROM Articulo WHERE nombre LIKE ? OR articulo_id = ?";
        javax.swing.DefaultListModel<String> modeloLista = new javax.swing.DefaultListModel<>();
        idsProductosEncontrados.clear();

        try (java.sql.Connection con = MODELO.ConexionBD.conectar();
            java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, "%" + textoFiltro + "%");
            pst.setString(2, textoFiltro);
            java.sql.ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("articulo_id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio_Venta");
                double stock = rs.getDouble("stock");

                idsProductosEncontrados.add(id);
                modeloLista.addElement(nombre + " - $" + precio + " (Stock: " + stock + ")");
            }

            listaSugerencias.setModel(modeloLista);

            if (!modeloLista.isEmpty()) {
                // Muestra el menú debajo del buscador de texto
                popupSugerencias.show(txtBuscador, 0, txtBuscador.getHeight());
                popupSugerencias.setPopupSize(txtBuscador.getWidth(), 100);
                txtBuscador.requestFocusInWindow();
            } else {
                popupSugerencias.setVisible(false);
            }

        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_txtBuscadorKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try {
          // Solo reutilizamos la variable que ya estaba arriba (sin repetir java.awt.Window)
          Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        
        // Abrimos el diálogo pasándole la ventana padre real y haciendo el casting correcto
        Corte_Caja ventanaCorte = new Corte_Caja((java.awt.Frame) parentWindow, true);
        
        // La centramos y la hacemos visible
        ventanaCorte.setLocationRelativeTo(parentWindow); 
        ventanaCorte.setVisible(true);
        
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al abrir el corte de caja: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void seleccionarProductoSugerido() {
        int index = listaSugerencias.getSelectedIndex();
        if (index >= 0 && index < idsProductosEncontrados.size()) {
            int idArticuloSeleccionado = idsProductosEncontrados.get(index);
            agregarArticuloPorId(idArticuloSeleccionado);
            popupSugerencias.setVisible(false);
            txtBuscador.setText("");
            txtBuscador.requestFocus();
        }
    }

    private void agregarArticuloPorId(int idArticulo) {
       String sql = "SELECT articulo_id, nombre, precio_Venta, stock FROM Articulo WHERE articulo_id = ?";
    
    try (java.sql.Connection con = MODELO.ConexionBD.conectar();
         java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setInt(1, idArticulo);
        java.sql.ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("articulo_id");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio_Venta");
            double stock = rs.getDouble("stock");
            double cantidad = 1.0; // Cantidad inicial por defecto
            double subtotal = precio * cantidad;

            javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable1.getModel();
            
            // Verificar si el producto ya está en la tabla para solo sumar la cantidad
            boolean encontrado = false;
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object valId = modelo.getValueAt(i, 0);
                if (valId != null && Integer.parseInt(valId.toString().trim()) == id) {
                    double cantActual = Double.parseDouble(modelo.getValueAt(i, 2).toString().trim());
                    double nuevaCant = cantActual + 1;
                    modelo.setValueAt(nuevaCant, i, 2);
                    modelo.setValueAt(nuevaCant * precio, i, 4); // Actualizar subtotal con seguridad
                    encontrado = true;
                    break;
                }
            }

            // Si no está, se añade una nueva fila asegurando que ningún valor sea nulo
            if (!encontrado) {
                modelo.addRow(new Object[]{
                    id, 
                    (nombre != null ? nombre : "Sin nombre"), 
                    cantidad, 
                    precio, 
                    subtotal
                });
            }
            
            popupSugerencias.setVisible(false);
            txtBuscador.setText("");
            txtBuscador.requestFocusInWindow();
        }
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al agregar el artículo: " + e.getMessage());
    }
}
  

    private void ejecutarGuardadoVentaYStock(javax.swing.table.DefaultTableModel modelo, double total, String tipoPago, double efectivo, double cambio) {
   String sqlStock = "UPDATE Articulo SET stock = stock - ? WHERE articulo_id = ?";
    try (java.sql.Connection con = MODELO.ConexionBD.conectar();
         java.sql.PreparedStatement pstStock = con.prepareStatement(sqlStock)) {
        
        int rowCount = modelo.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object objCant = modelo.getValueAt(i, 2);
            Object objId   = modelo.getValueAt(i, 0);
            
            if (objCant != null && objId != null && !objCant.toString().trim().isEmpty() && !objId.toString().trim().isEmpty()) {
                pstStock.setDouble(1, Double.parseDouble(objCant.toString().trim()));
                pstStock.setInt(2, Integer.parseInt(objId.toString().trim()));
                pstStock.addBatch();
            }
        }
        pstStock.executeBatch();
        modelo.setRowCount(0); // Limpia la tabla
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar stock: " + e.getMessage());
    }
}

  private void actualizarVistaPreviaTicket() {
    if (txtAreaTicketPreview == null) return;

    javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable1.getModel();
    int rowCount = modelo.getRowCount();

    StringBuilder ticket = new StringBuilder();
    
    // Usamos un ancho fijo de 32 caracteres igual que tu ticket real para que coincida el centrado
    ticket.append(centrarTexto("SISTEMA SOFT-GIRA", 32)).append("\n");
    ticket.append("--------------------------------\n");
    ticket.append(String.format("%-16s %4s %8s\n", "Articulo", "Cnt", "Sub"));
    ticket.append("--------------------------------\n");

    double total = 0.0;
    for (int i = 0; i < rowCount; i++) {
        Object descObj = modelo.getValueAt(i, 1);
        Object cantObj = modelo.getValueAt(i, 2);
        Object subObj = modelo.getValueAt(i, 4);

        if (descObj != null && cantObj != null && subObj != null) {
            String desc = descObj.toString();
            if (desc.length() > 16) desc = desc.substring(0, 16); 
            try {
                double cant = Double.parseDouble(cantObj.toString());
                double sub = Double.parseDouble(subObj.toString());
                total += sub;
                ticket.append(String.format("%-16s %4.1f $%7.2f\n", desc, cant, sub));
            } catch (NumberFormatException e) {}
        }
    }

    ticket.append("--------------------------------\n");
    ticket.append(String.format("TOTAL: $%.2f\n", total));
    ticket.append("--------------------------------\n");
    ticket.append(centrarTexto("¡Gracias por su compra!", 32)).append("\n");

    txtAreaTicketPreview.setText(ticket.toString());
}

// Método auxiliar rápido para centrar textos en la vista de ticket
private String centrarTexto(String texto, int anchoLinea) {
    if (texto.length() >= anchoLinea) return texto;
    int padding = (anchoLinea - texto.length()) / 2;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < padding; i++) sb.append(" ");
    sb.append(texto);
    return sb.toString();
}

private void cobrarYGenerarTicket(javax.swing.JTable jTable1, java.awt.Checkbox chkImprimir, double totalCompra, String tipoPago, double efectivo, double cambio, int idVenta) {
    try {
        // Limpiar la cola de CUPS por seguridad ante trabajos atorados
        try {
            Runtime.getRuntime().exec("cancel -a Ticketera");
        } catch (Exception ex) {
            // Ignorar
        }

        javax.swing.table.DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        int rowCount = modelo.getRowCount();

        StringBuilder ticket = new StringBuilder();
        
        // Encabezado exacto del diseño proporcionado (32 caracteres de ancho)
        ticket.append("\n");
        ticket.append("================================\n");
        ticket.append("   LUMMEL SYSTEM INTEGRATION    \n");
        ticket.append("     PUNTO DE VENTA CAJA        \n");
        ticket.append("================================\n");
        ticket.append("Folio: ").append(idVenta).append("\n");
        ticket.append("Fecha: ").append(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        ticket.append("Cajero: Administrador\n");
        ticket.append("--------------------------------\n");
        ticket.append(String.format("%-16s %4s %8s\n", "PRODUCTO", "CANT", "SUBTOTAL"));
        ticket.append("--------------------------------\n");

        for (int i = 0; i < rowCount; i++) {
            Object descObj = modelo.getValueAt(i, 1);
            Object cantObj = modelo.getValueAt(i, 2);
            Object subObj = modelo.getValueAt(i, 4);

            if (descObj != null && cantObj != null && subObj != null) {
                String descripcion = descObj.toString();
                double cantidad = Double.parseDouble(cantObj.toString().trim());
                double subtotal = Double.parseDouble(subObj.toString().trim());

                // Recortar descripción a 16 caracteres para mantener la alineación de la tabla
                if (descripcion.length() > 16) {
                    descripcion = descripcion.substring(0, 16);
                }

                ticket.append(String.format("%-16s %4.0f $%7.2f\n", descripcion, cantidad, subtotal));
            }
        }

        ticket.append("--------------------------------\n");
        ticket.append(String.format("TOTAL A PAGAR:     $%7.2f\n", totalCompra));
        
        if (tipoPago.equalsIgnoreCase("Efectivo")) {
            ticket.append(String.format("EFECTIVO RECIBIDO: $%7.2f\n", efectivo));
            ticket.append(String.format("CAMBIO:            $%7.2f\n", cambio));
        } else {
            ticket.append("METODO DE PAGO:    TARJETA\n");
        }
        
        ticket.append("================================\n");
        ticket.append("    ¡GRACIAS POR SU COMPRA!     \n");
        
        // Espacios de cortesía y avance para el corte del papel
        ticket.append("\n\n\n\n");

        // Buscar la impresora "Ticketera" en los servicios del sistema
        javax.print.PrintService[] servicios = javax.print.PrintServiceLookup.lookupPrintServices(null, null);
        javax.print.PrintService impresoraTicketera = null;

        for (javax.print.PrintService servicio : servicios) {
            if (servicio.getName().equalsIgnoreCase("Ticketera")) {
                impresoraTicketera = servicio;
                break;
            }
        }

        if (impresoraTicketera == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró la impresora 'Ticketera'.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Envío directo en modo RAW a la impresora
        javax.print.DocPrintJob trabajo = impresoraTicketera.createPrintJob();
        byte[] bytesTicket = ticket.toString().getBytes("ISO-8859-1");
        javax.print.Doc doc = new javax.print.SimpleDoc(bytesTicket, javax.print.DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
        
        trabajo.print(doc, null);

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al imprimir el ticket: " + e.getMessage(), "Error de Impresión", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Checkbox impresion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelDerechoGlobal;
    private javax.swing.JPanel jPanelEncabezado;
    private javax.swing.JPanel jPanelIzquierdoContenedor;
    private javax.swing.JPanel jPanelNorteIzq;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtAreaTicketPreview;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
