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
        initComponents(); // Código generado por NetBeans
        configurarAtajosTecladoPV();
        estilizarPanelDerecho();
       // --- ASIGNAR USUARIO LOGUEADO AL JLABEL ---
        try {
            if (MODELO.SesionActual.usuarioLogueado != null && !MODELO.SesionActual.usuarioLogueado.isEmpty()) {
                usuario.setText("Usuario: " + MODELO.SesionActual.usuarioLogueado);
            } else {
                usuario.setText("Usuario ID: " + MODELO.SesionActual.idUsuarioLogueado);
            }
        } catch (Exception e) {
            usuario.setText("Usuario Activo");
        }

        // 1. Limpiar las filas nulas por defecto que crea el diseñador de NetBeans
        DefaultTableModel modeloTablaInicial = (DefaultTableModel) jTable1.getModel();
        modeloTablaInicial.setRowCount(0);

        // 2. Configuración del JTextArea en el panel de Resumen de Pago
        if (txtAreaTicketPreview == null) {
            txtAreaTicketPreview = new javax.swing.JTextArea();
        }
        txtAreaTicketPreview.setEditable(false);
        txtAreaTicketPreview.setFont(new java.awt.Font("Monospaced", 0, 11));

        if (jScrollPane2 != null) {
            jScrollPane2.setViewportView(txtAreaTicketPreview);
        }

        // Renderizar vista previa inicial centrada
        actualizarVistaPreviaTicket();

        // 3. Configuración inicial del menú flotante de autocompletado
        listaSugerencias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        popupSugerencias.add(new javax.swing.JScrollPane(listaSugerencias));
        popupSugerencias.setFocusable(false);

        listaSugerencias.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1 || evt.getClickCount() == 2) {
                    Punto_Venta.this.seleccionarProductoSugerido();
                }
            }
        });

        // 4. Listener para recalcular subtotales y actualizar la vista previa
        jTable1.getModel().addTableModelListener(new javax.swing.event.TableModelListener() {
            private boolean actualizando = false;

            @Override
            public void tableChanged(javax.swing.event.TableModelEvent e) {
                if (actualizando) return;

                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                int row = e.getFirstRow();
                int column = e.getColumn();
                int totalFilas = modelo.getRowCount();

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
                            // Ignorar caracteres parciales mientras el usuario edita
                        } finally {
                            actualizando = false;
                        }
                    }
                }
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
        jPanelEncabezado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        usuario = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelDerechoGlobal = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaTicketPreview = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        impresion = new java.awt.Checkbox();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanelIzquierdoContenedor.setLayout(new java.awt.BorderLayout());

        jPanelNorteIzq.setLayout(new java.awt.BorderLayout());

        jPanelEncabezado.setBackground(new java.awt.Color(31, 34, 111));
        jPanelEncabezado.setPreferredSize(new java.awt.Dimension(800, 75));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PUNTO DE VENTA  |");
        jPanelEncabezado.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CAJA PRINCIPAL");
        jPanelEncabezado.add(jLabel2);

        jPanelNorteIzq.add(jPanelEncabezado, java.awt.BorderLayout.NORTH);

        usuario.setText("jLabel3");
        jPanel1.add(usuario);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/lupa.png"))); // NOI18N
        jPanel1.add(jLabel7);

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabel4.setText("DESCRIPCION");
        jPanel1.add(jLabel4);

        txtBuscador.setPreferredSize(new java.awt.Dimension(420, 32));
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyPressed(evt);
            }
        });
        jPanel1.add(txtBuscador);

        jButton1.setBackground(new java.awt.Color(31, 34, 111));
        jButton1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cajero-automatico.png"))); // NOI18N
        jButton1.setText("F_Corte");
        jButton1.setPreferredSize(new java.awt.Dimension(200, 38));
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

        jPanelDerechoGlobal.setPreferredSize(new java.awt.Dimension(350, 700));
        jPanelDerechoGlobal.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("RESUMEN DE PAGO");
        jPanelDerechoGlobal.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(260, 500));

        txtAreaTicketPreview.setColumns(20);
        txtAreaTicketPreview.setRows(5);
        jScrollPane2.setViewportView(txtAreaTicketPreview);

        jPanelDerechoGlobal.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 180));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jButton2.setBackground(new java.awt.Color(31, 34, 111));
        jButton2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/metodo-de-pago.png"))); // NOI18N
        jButton2.setText("Pagar / Cobrar");
        jButton2.setPreferredSize(new java.awt.Dimension(350, 60));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, java.awt.BorderLayout.PAGE_END);

        impresion.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        impresion.setLabel("Imprimir Ticket al guardar");
        jPanel3.add(impresion, java.awt.BorderLayout.CENTER);

        jPanelDerechoGlobal.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanelIzquierdoContenedor.add(jPanelDerechoGlobal, java.awt.BorderLayout.LINE_END);

        jPanel4.add(jPanelIzquierdoContenedor, java.awt.BorderLayout.CENTER);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
DefaultTableModel modeloCarrito = (DefaultTableModel) jTable1.getModel();

if (modeloCarrito.getRowCount() == 0) {
    JOptionPane.showMessageDialog(this, "El carrito de compras está vacío.", "Atención", JOptionPane.WARNING_MESSAGE);
    return;
}

int idUsuarioActual = MODELO.SesionActual.idUsuarioLogueado;
CONTROLADOR.CorteCajaDao corteDao = new CONTROLADOR.CorteCajaDao();
int idCorteActual = corteDao.obtenerIdCorteAbierto(idUsuarioActual);

if (idCorteActual <= 0) {
    JOptionPane.showMessageDialog(this, "No hay un corte de caja abierto para este usuario.", "Caja Cerrada", JOptionPane.WARNING_MESSAGE);
    return;
}

int idClienteFinal = seleccionarClientePorNombre();
if (idClienteFinal == -1) {
    return; 
}

double totalVenta = 0.0;
for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
    Object subVal = modeloCarrito.getValueAt(i, 4);
    if (subVal != null && !subVal.toString().trim().isEmpty()) {
        try {
            String subLimpio = subVal.toString().replaceAll("[^0-9.-]", "").trim();
            totalVenta += Double.parseDouble(subLimpio);
        } catch (NumberFormatException e) {}
    }
}

// Variable para rastrear el descuento
double descuentoMonto = 0.0;

// Validación de Mayorista
String tipoCliente = obtenerTipoCliente(idClienteFinal);
if (tipoCliente.equalsIgnoreCase("Mayorista")) {
    int resp = JOptionPane.showConfirmDialog(
        this,
        "El cliente seleccionado es MAYORISTA.\n¿Desea aplicar un porcentaje de descuento a esta venta?",
        "Descuento Mayorista",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );

    if (resp == JOptionPane.YES_OPTION) {
        String inputDescuento = JOptionPane.showInputDialog(
            this,
            "Total actual: $" + String.format("%.2f", totalVenta) + "\nIngrese el % de descuento a aplicar:",
            "Descuento Mayorista",
            JOptionPane.QUESTION_MESSAGE
        );

        if (inputDescuento != null && !inputDescuento.trim().isEmpty()) {
            try {
                double porcentaje = Double.parseDouble(inputDescuento.replaceAll("[^0-9.-]", "").trim());
                if (porcentaje > 0 && porcentaje <= 100) {
                    descuentoMonto = totalVenta * (porcentaje / 100.0);
                    totalVenta -= descuentoMonto;
                    JOptionPane.showMessageDialog(
                        this,
                        "Descuento del " + porcentaje + "% aplicado.\nNuevo Total: $" + String.format("%.2f", totalVenta),
                        "Descuento Aplicado",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Formato incorrecto. Se cobrará total completo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

String[] opcionesPago = {"Efectivo", "Tarjeta"};
String tipoPagoSeleccionado = (String) JOptionPane.showInputDialog(
    this,
    "Total a pagar: $" + String.format("%.2f", totalVenta) + "\nSeleccione el método de pago:",
    "Método de Pago",
    JOptionPane.QUESTION_MESSAGE,
    null,
    opcionesPago,
    opcionesPago[0]
);

if (tipoPagoSeleccionado == null) return;

double pagoEfectivo = 0.0;
double cambio = 0.0;

if (tipoPagoSeleccionado.equalsIgnoreCase("Efectivo")) {
    String inputEfectivo = JOptionPane.showInputDialog(
        this,
        "Total a pagar: $" + String.format("%.2f", totalVenta) + "\nIngrese con cuánto paga el cliente:",
        "Cobrar en Efectivo",
        JOptionPane.QUESTION_MESSAGE
    );

    if (inputEfectivo == null) return;

    try {
        String textoLimpio = inputEfectivo.replaceAll("[^0-9.-]", "").trim();
        pagoEfectivo = textoLimpio.isEmpty() ? totalVenta : Double.parseDouble(textoLimpio);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (pagoEfectivo < totalVenta) {
        JOptionPane.showMessageDialog(this, "El dinero entregado es menor al total de la venta.", "Fondos insuficientes", JOptionPane.WARNING_MESSAGE);
        return;
    }

    cambio = pagoEfectivo - totalVenta;
} else {
    pagoEfectivo = totalVenta;
    cambio = 0.0;
}

int idDocumentoGenerado = CONTROLADOR.ControladorVentas.registrarVentaCompleta(
    jTable1, 
    totalVenta, 
    pagoEfectivo, 
    cambio, 
    tipoPagoSeleccionado, 
    idUsuarioActual,
    idCorteActual,
    idClienteFinal, // <--- SE AGREGÓ LA COMA AQUÍ
    descuentoMonto
);

if (idDocumentoGenerado != -1) {
    // Obtener el nombre del cliente para mandarlo al ticket
    String nombreCliente = obtenerNombreCliente(idClienteFinal);

    // Llamada actualizada con cliente y descuento
    cobrarYGenerarTicket(jTable1, impresion, totalVenta, tipoPagoSeleccionado, pagoEfectivo, cambio, idDocumentoGenerado, nombreCliente, descuentoMonto);

    modeloCarrito.setRowCount(0);

    JOptionPane.showMessageDialog(
        this,
        "¡Venta realizada con éxito!\n\n" +
        "N° Ticket: " + idDocumentoGenerado + "\n" +
        "Cliente: " + nombreCliente + "\n" +
        "Total: $" + String.format("%.2f", totalVenta),
        "Venta Exitosa",
        JOptionPane.INFORMATION_MESSAGE
    );
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtBuscadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyPressed
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

        try (Connection con = MODELO.ConexionBD.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, "%" + textoFiltro + "%");
            pst.setString(2, textoFiltro);
            ResultSet rs = pst.executeQuery();

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
                popupSugerencias.show(txtBuscador, 0, txtBuscador.getHeight());
                popupSugerencias.setPopupSize(txtBuscador.getWidth(), 100);
                txtBuscador.requestFocusInWindow();
            } else {
                popupSugerencias.setVisible(false);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_txtBuscadorKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try {
            Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            Corte_Caja ventanaCorte = new Corte_Caja((java.awt.Frame) parentWindow, true);
            ventanaCorte.setLocationRelativeTo(parentWindow); 
            ventanaCorte.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir el corte de caja: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        try (Connection con = MODELO.ConexionBD.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idArticulo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("articulo_id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio_Venta");
                double cantidad = 1.0;
                double subtotal = precio * cantidad;

                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

                // Si el producto ya está en el carrito, sumar la cantidad
                boolean encontrado = false;
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    Object valId = modelo.getValueAt(i, 0);
                    if (valId != null && Integer.parseInt(valId.toString().trim()) == id) {
                        double cantActual = Double.parseDouble(modelo.getValueAt(i, 2).toString().trim());
                        double nuevaCant = cantActual + 1;
                        modelo.setValueAt(nuevaCant, i, 2);
                        modelo.setValueAt(nuevaCant * precio, i, 4);
                        encontrado = true;
                        break;
                    }
                }

                // Agregar fila nueva si no existe en la tabla
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el artículo: " + e.getMessage());
        }
    }
// Método principal que recibe el cliente y el descuento
private void actualizarVistaPreviaTicket(String nombreCliente, double descuentoMonto) {
    if (txtAreaTicketPreview == null) return;

    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
    int rowCount = modelo.getRowCount();

    StringBuilder ticket = new StringBuilder();
    int anchoTotal = 40; // Ancho para centrado en el panel derecho

    ticket.append(centrarTexto("SISTEMA SOFT-GIRA", anchoTotal)).append("\n");
    ticket.append("----------------------------------------\n");
    
    // Mostrar Cliente
    String clienteMostrar = (nombreCliente != null && !nombreCliente.trim().isEmpty()) 
            ? nombreCliente 
            : "Público en General";
    ticket.append("Cliente: ").append(clienteMostrar).append("\n");
    
    ticket.append("----------------------------------------\n");
    ticket.append(String.format("%-20s %5s %13s\n", "Articulo", "Cnt", "Sub"));
    ticket.append("----------------------------------------\n");

    double subtotalAcumulado = 0.0;
    for (int i = 0; i < rowCount; i++) {
        Object descObj = modelo.getValueAt(i, 1);
        Object cantObj = modelo.getValueAt(i, 2);
        Object subObj = modelo.getValueAt(i, 4);

        if (descObj != null && cantObj != null && subObj != null) {
            String desc = descObj.toString();
            if (desc.length() > 20) desc = desc.substring(0, 20); 
            try {
                double cant = Double.parseDouble(cantObj.toString());
                double sub = Double.parseDouble(subObj.toString());
                subtotalAcumulado += sub;
                ticket.append(String.format("%-20s %5.1f $%11.2f\n", desc, cant, sub));
            } catch (NumberFormatException e) {}
        }
    }

    ticket.append("----------------------------------------\n");

    // Desglose si existe descuento
    if (descuentoMonto > 0) {
        ticket.append(String.format("%-25s $%12.2f\n", "SUBTOTAL:", subtotalAcumulado));
        ticket.append(String.format("%-25s-$%12.2f\n", "DESCUENTO:", descuentoMonto));
    }

    double totalFinal = subtotalAcumulado - descuentoMonto;
    ticket.append(centrarTexto(String.format("TOTAL: $%.2f", totalFinal), anchoTotal)).append("\n");
    ticket.append("----------------------------------------\n");
    ticket.append(centrarTexto("¡Gracias por su compra!", anchoTotal)).append("\n");

    txtAreaTicketPreview.setText(ticket.toString());
}

// Sobrecarga por defecto (mantiene compatibilidad con llamadas sin parámetros)
private void actualizarVistaPreviaTicket() {
    actualizarVistaPreviaTicket("Público en General", 0.0);
}

    private String centrarTexto(String texto, int anchoLinea) {
        if (texto.length() >= anchoLinea) return texto;
        int padding = (anchoLinea - texto.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(texto);
        return sb.toString();
    }

   private void cobrarYGenerarTicket(JTable jTable1, java.awt.Checkbox chkImprimir, double totalCompra, String tipoPago, double efectivo, double cambio, int idVenta, String nombreCliente, double descuentoMonto) {
    if (chkImprimir != null && !chkImprimir.getState()) {
        return;
    }

    try {
        String os = System.getProperty("os.name").toLowerCase();
        boolean esWindows = os.contains("win");

        if (!esWindows) {
            try {
                Runtime.getRuntime().exec(new String[]{"cancel", "-a", "Ticketera"});
            } catch (Exception ex) {}
        }

        byte[] initPrinter = new byte[]{0x1B, 0x40};          
        byte[] cutPaper = new byte[]{0x1D, 0x56, 0x42, 0x00}; 

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        int rowCount = modelo.getRowCount();

        StringBuilder ticket = new StringBuilder();

        ticket.append("\n");
        ticket.append("================================\n");
        ticket.append("   LUMMEL SYSTEM INTEGRATION    \n");
        ticket.append("      PUNTO DE VENTA CAJA       \n");
        ticket.append("================================\n");
        ticket.append("Folio:   ").append(idVenta).append("\n");
        ticket.append("Fecha:   ").append(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");

        String cajeroActual = (MODELO.SesionActual.usuarioLogueado != null && !MODELO.SesionActual.usuarioLogueado.isEmpty()) 
                ? MODELO.SesionActual.usuarioLogueado 
                : "Administrador";
        ticket.append("Cajero:  ").append(cajeroActual).append("\n");
        ticket.append("Cliente: ").append(nombreCliente).append("\n");
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

                if (descripcion.length() > 16) {
                    descripcion = descripcion.substring(0, 16);
                }

                ticket.append(String.format("%-16s %4.0f $%7.2f\n", descripcion, cantidad, subtotal));
            }
        }

        ticket.append("--------------------------------\n");
        
        // Si hubo descuento, mostrar Subtotal y Descuento
        if (descuentoMonto > 0) {
            ticket.append(String.format("SUBTOTAL:          $%7.2f\n", totalCompra + descuentoMonto));
            ticket.append(String.format("DESCUENTO:        -$%7.2f\n", descuentoMonto));
        }

        ticket.append(String.format("TOTAL A PAGAR:     $%7.2f\n", totalCompra));

        if (tipoPago.equalsIgnoreCase("Efectivo")) {
            ticket.append(String.format("EFECTIVO RECIBIDO: $%7.2f\n", efectivo));
            ticket.append(String.format("CAMBIO:            $%7.2f\n", cambio));
        } else {
            ticket.append("METODO DE PAGO:    TARJETA\n");
        }

        ticket.append("================================\n");
        ticket.append("    ¡GRACIAS POR SU COMPRA!     \n");
        ticket.append("\n\n\n\n"); 

        javax.print.PrintService[] servicios = javax.print.PrintServiceLookup.lookupPrintServices(null, null);
        javax.print.PrintService impresoraTicketera = null;

        for (javax.print.PrintService servicio : servicios) {
            String nombre = servicio.getName().toLowerCase();
            if (nombre.contains("ticketera") || nombre.contains("pos")) {
                impresoraTicketera = servicio;
                break;
            }
        }

        if (impresoraTicketera == null) {
            impresoraTicketera = javax.print.PrintServiceLookup.lookupDefaultPrintService();
        }

        if (impresoraTicketera == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la impresora 'Ticketera' ni una predeterminada.", "Error de Impresora", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String encoding = esWindows ? "CP850" : "ISO-8859-1";
        byte[] bytesTexto = ticket.toString().getBytes(encoding);

        java.io.ByteArrayOutputStream streamFinal = new java.io.ByteArrayOutputStream();
        streamFinal.write(initPrinter);
        streamFinal.write(bytesTexto);
        streamFinal.write(cutPaper);

        javax.print.DocPrintJob trabajo = impresoraTicketera.createPrintJob();
        javax.print.Doc doc = new javax.print.SimpleDoc(streamFinal.toByteArray(), javax.print.DocFlavor.BYTE_ARRAY.AUTOSENSE, null);

        trabajo.print(doc, null);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al imprimir el ticket: " + e.getMessage(), "Error de Impresión", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private int seleccionarClientePorNombre() {
    final int[] idClienteSeleccionado = {1}; // 1 = ID predeterminado (Público en General)
    final boolean[] confirmado = {false};

    // Crear ventana emergente para buscar cliente
    java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
    javax.swing.JDialog dialog = new javax.swing.JDialog((java.awt.Frame) parentWindow, "Asignar Cliente", true);
    dialog.setLayout(new java.awt.BorderLayout(10, 10));

    javax.swing.JPanel panelPrincipal = new javax.swing.JPanel(new java.awt.BorderLayout(5, 5));
    panelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

    javax.swing.JLabel lblInstruccion = new javax.swing.JLabel("Escriba el Nombre del Cliente:");
    panelPrincipal.add(lblInstruccion, java.awt.BorderLayout.NORTH);

    javax.swing.JTextField txtNombreCliente = new javax.swing.JTextField();
    panelPrincipal.add(txtNombreCliente, java.awt.BorderLayout.CENTER);

    // Menú flotante (Pop-up) para mostrar las sugerencias de nombres
    javax.swing.JPopupMenu popupCliente = new javax.swing.JPopupMenu();
    javax.swing.JList<String> listaClientes = new javax.swing.JList<>();
    java.util.List<Integer> idsClientes = new java.util.ArrayList<>();

    popupCliente.add(new javax.swing.JScrollPane(listaClientes));
    popupCliente.setFocusable(false);

    // Evento al escribir en la caja de texto: consulta a la BD por la columna 'Nombre'
    txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
            // Tecla Enter: selecciona la primera opción del desplegable
            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                if (popupCliente.isVisible() && listaClientes.getModel().getSize() > 0) {
                    listaClientes.setSelectedIndex(0);
                    int idx = listaClientes.getSelectedIndex();
                    if (idx >= 0 && idx < idsClientes.size()) {
                        idClienteSeleccionado[0] = idsClientes.get(idx);
                        txtNombreCliente.setText(listaClientes.getSelectedValue());
                    }
                    popupCliente.setVisible(false);
                }
                return;
            }

            String texto = txtNombreCliente.getText().trim();
            if (texto.isEmpty()) {
                popupCliente.setVisible(false);
                idClienteSeleccionado[0] = 1; // Público en general si se borra
                return;
            }

            javax.swing.DefaultListModel<String> model = new javax.swing.DefaultListModel<>();
            idsClientes.clear();

            // Consulta SQL sobre la tabla 'Cliente' y la columna 'Nombre'
            String sql = "SELECT id_cliente, Nombre FROM Cliente WHERE Nombre LIKE ?";
            try (Connection con = MODELO.ConexionBD.conectar();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, "%" + texto + "%");
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    idsClientes.add(rs.getInt("id_cliente"));
                    model.addElement(rs.getString("Nombre"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            listaClientes.setModel(model);

            // Mostrar el menú desplegable si hay coincidencias
            if (model.getSize() > 0) {
                popupCliente.show(txtNombreCliente, 0, txtNombreCliente.getHeight());
                popupCliente.setPopupSize(txtNombreCliente.getWidth(), 120);
                txtNombreCliente.requestFocusInWindow();
            } else {
                popupCliente.setVisible(false);
            }
        }
    });

    // Seleccionar cliente al hacer clic con el mouse en la lista
    listaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int idx = listaClientes.getSelectedIndex();
            if (idx >= 0 && idx < idsClientes.size()) {
                idClienteSeleccionado[0] = idsClientes.get(idx);
                txtNombreCliente.setText(listaClientes.getSelectedValue());
                popupCliente.setVisible(false);
            }
        }
    });

    // Botones del diálogo
    javax.swing.JPanel panelBotones = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
    javax.swing.JButton btnAceptar = new javax.swing.JButton("Aceptar");
    javax.swing.JButton btnCancelar = new javax.swing.JButton("Cancelar");

    btnAceptar.addActionListener(e -> {
        confirmado[0] = true;
        dialog.dispose();
    });

    btnCancelar.addActionListener(e -> {
        confirmado[0] = false;
        dialog.dispose();
    });

    panelBotones.add(btnAceptar);
    panelBotones.add(btnCancelar);

    dialog.add(panelPrincipal, java.awt.BorderLayout.CENTER);
    dialog.add(panelBotones, java.awt.BorderLayout.SOUTH);
    dialog.pack();
    dialog.setSize(480, 160);
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);

    if (!confirmado[0]) {
        return -1; // Retorna -1 si se cancela la operación
    }

    return idClienteSeleccionado[0]; // Retorna el id_cliente encontrado
}
    
    // --------------------------------------------------------------------------
// PEGA EL MÉTODO AQUÍ:
// --------------------------------------------------------------------------
private String obtenerTipoCliente(int idCliente) {
    String tipo = "Menudeo";
    String sql = "SELECT tipo_cliente FROM Cliente WHERE id_cliente = ?";
    
    try (Connection con = MODELO.ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setInt(1, idCliente);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            tipo = rs.getString("tipo_cliente");
        }
    } catch (SQLException e) {
        try (Connection con = MODELO.ConexionBD.conectar();
             PreparedStatement pst = con.prepareStatement("SELECT tipo FROM Cliente WHERE id_cliente = ?")) {
            pst.setInt(1, idCliente);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tipo = rs.getString("tipo");
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar tipo de cliente: " + ex.getMessage());
        }
    }
    return tipo;
}
    
    // Configuración de los atajos de teclado F1 y F2
private void configurarAtajosTecladoPV() {
    // Atajo F1 -> Eliminar producto seleccionado de la tabla
    this.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0), "accionF1");
    this.getActionMap().put("accionF1", new javax.swing.AbstractAction() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            eliminarProductoSeleccionadoF1();
        }
    });

    // Atajo F2 -> Registrar nuevo cliente (Menudeo / Mayorista)
    this.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0), "accionF2");
    this.getActionMap().put("accionF2", new javax.swing.AbstractAction() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            registrarClienteF2();
        }
    });
}

// Acción F1: Eliminar fila seleccionada de la tabla de venta
private void eliminarProductoSeleccionadoF1() {
    int filaSeleccionada = jTable1.getSelectedRow(); 

    if (filaSeleccionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Selecciona un producto de la lista para eliminarlo.", 
            "Aviso (F1)", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable1.getModel();
    modelo.removeRow(filaSeleccionada);

    // Actualiza el ticket y el total acumulado en pantalla
    actualizarVistaPreviaTicket();
}

// Acción F2: Pestaña emergente para nuevo cliente con ComboBox
private void registrarClienteF2() {
    javax.swing.JTextField txtNombreCliente = new javax.swing.JTextField(20);
    javax.swing.JComboBox<String> cmbTipoCliente = new javax.swing.JComboBox<>(new String[]{"Menudeo", "Mayorista"});

    javax.swing.JPanel panelDialogo = new javax.swing.JPanel(new java.awt.GridLayout(0, 1, 5, 5));
    panelDialogo.add(new javax.swing.JLabel("Nombre completo del Cliente:"));
    panelDialogo.add(txtNombreCliente);
    panelDialogo.add(new javax.swing.JLabel("Tipo de Cliente:"));
    panelDialogo.add(cmbTipoCliente);

    int opcion = javax.swing.JOptionPane.showConfirmDialog(
        this, 
        panelDialogo, 
        "Registrar Nuevo Cliente (F2)", 
        javax.swing.JOptionPane.OK_CANCEL_OPTION, 
        javax.swing.JOptionPane.PLAIN_MESSAGE
    );

    if (opcion == javax.swing.JOptionPane.OK_OPTION) {
        String nombre = txtNombreCliente.getText().trim();
        String tipo = cmbTipoCliente.getSelectedItem().toString();

        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "El nombre del cliente no puede estar vacío.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cambia 'tipo_cliente' por el nombre exacto de la columna en tu MySQL
        String sql = "INSERT INTO Cliente (Nombre, tipo_cliente) VALUES (?, ?)";
        try (java.sql.Connection con = MODELO.ConexionBD.conectar();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nombre);
            pst.setString(2, tipo);
            pst.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(this, "¡Cliente '" + nombre + "' (" + tipo + ") registrado exitosamente!");

        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al registrar cliente: " + ex.getMessage(), "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
private void estilizarPanelDerecho() {
    // 1. Margen de 35px a la izquierda para centrar el texto en el JTextArea del ticket
    txtAreaTicketPreview.setMargin(new java.awt.Insets(15, 35, 15, 15));
    txtAreaTicketPreview.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 13));

    // 2. Configurar jPanel3 solo para 2 filas (Checkbox y Botón Cobrar)
    jPanel3.removeAll();
    jPanel3.setLayout(new java.awt.GridLayout(2, 1, 10, 10));
    jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

    // 3. Agregar únicamente los 2 componentes activos
    jPanel3.add(impresion);
    jPanel3.add(jButton2);

    jPanel3.revalidate();
    jPanel3.repaint();
}

private String obtenerNombreCliente(int idCliente) {
    String nombre = "Público en General";
    String sql = "SELECT Nombre FROM Cliente WHERE id_cliente = ?";
    
    try (Connection con = MODELO.ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setInt(1, idCliente);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nombre = rs.getString("Nombre");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener nombre del cliente: " + e.getMessage());
    }
    return nombre;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Checkbox impresion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelDerechoGlobal;
    private javax.swing.JPanel jPanelEncabezado;
    private javax.swing.JPanel jPanelIzquierdoContenedor;
    private javax.swing.JPanel jPanelNorteIzq;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtAreaTicketPreview;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
