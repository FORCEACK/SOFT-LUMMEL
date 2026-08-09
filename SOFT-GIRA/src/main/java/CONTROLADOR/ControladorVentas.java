package CONTROLADOR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorVentas {

    // Sobrecarga 1: 6 parámetros
    public static int registrarVentaCompleta(
            JTable tabla,
            double total,
            double efectivo,
            double cambio,
            String tipoPago,
            int idUsuario) {
        return registrarVentaCompleta(tabla, total, efectivo, cambio, tipoPago, idUsuario, -1, 1, 0.0);
    }

    // Sobrecarga 2: 7 parámetros
    public static int registrarVentaCompleta(
            JTable tabla,
            double total,
            double efectivo,
            double cambio,
            String tipoPago,
            int idUsuario,
            int idCorte) {
        return registrarVentaCompleta(tabla, total, efectivo, cambio, tipoPago, idUsuario, idCorte, 1, 0.0);
    }

    // Sobrecarga 3: 8 parámetros
    public static int registrarVentaCompleta(
            JTable tabla,
            double total,
            double efectivo,
            double cambio,
            String tipoPago,
            int idUsuario,
            int idCorte,
            int idCliente) {
        return registrarVentaCompleta(tabla, total, efectivo, cambio, tipoPago, idUsuario, idCorte, idCliente, 0.0);
    }

    // Método principal: 9 parámetros (incluye el descuento)
    public static int registrarVentaCompleta(
            JTable tabla,
            double total,
            double efectivo,
            double cambio,
            String tipoPago,
            int idUsuario,
            int idCorte,
            int idCliente,
            double descuento) {

        String sqlVenta = """
            INSERT INTO DocumentoVenta
            (
                id_Cliente,
                id_Usuario,
                id_Corte,
                fecha_venta,
                total_venta,
                pago_efectivo,
                pago_tarjeta,
                efectivo_recibido,
                cambio,
                descuento
            )
            VALUES (?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?)
            """;

        String sqlDetalle = """
            INSERT INTO DetalleVenta
            (id_Documento, articulo_id, cantidad, precio_unitario)
            VALUES (?, ?, ?, ?)
            """;

        int idVentaGenerado = -1;

        try (Connection conexion = MODELO.ConexionBD.conectar()) {

            if (conexion == null) {
                return -1;
            }

            // Si no se proporcionó idCorte, buscar el abierto para el usuario
            if (idCorte <= 0) {
                CorteCajaDao corteDao = new CorteCajaDao();
                idCorte = corteDao.obtenerIdCorteAbierto(idUsuario, conexion);
            }

            if (idCorte <= 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "No existe un corte de caja abierto para el usuario actual.",
                    "Venta no permitida",
                    JOptionPane.WARNING_MESSAGE
                );
                return -1;
            }

            // Iniciamos la transacción manual
            conexion.setAutoCommit(false);

            try {
                // ==========================================
                // 1. GUARDAR CABECERA (DocumentoVenta)
                // ==========================================
                try (PreparedStatement pstVenta = conexion.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {

                    pstVenta.setInt(1, idCliente); 
                    pstVenta.setInt(2, idUsuario);
                    pstVenta.setInt(3, idCorte);
                    pstVenta.setDouble(4, total);

                    if (tipoPago.equalsIgnoreCase("Efectivo")) {
                        pstVenta.setDouble(5, total);
                        pstVenta.setDouble(6, 0.00);
                        pstVenta.setDouble(7, efectivo);
                        pstVenta.setDouble(8, cambio);
                    } else {
                        pstVenta.setDouble(5, 0.00);
                        pstVenta.setDouble(6, total);
                        pstVenta.setDouble(7, total);
                        pstVenta.setDouble(8, 0.00);
                    }

                    // Se envía el descuento
                    pstVenta.setDouble(9, descuento);

                    pstVenta.executeUpdate();

                    try (ResultSet rs = pstVenta.getGeneratedKeys()) {
                        if (rs.next()) {
                            idVentaGenerado = rs.getInt(1);
                        } else {
                            throw new SQLException("No se pudo obtener el ID de la venta.");
                        }
                    }
                }

                // ==========================================
                // 2. GUARDAR DETALLE (DetalleVenta)
                // ==========================================
                DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

                try (PreparedStatement pstDetalle = conexion.prepareStatement(sqlDetalle)) {
                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        Object idArticuloObj = modelo.getValueAt(i, 0);
                        Object cantObj = modelo.getValueAt(i, 2);
                        Object precioObj = modelo.getValueAt(i, 3);

                        if (idArticuloObj != null && cantObj != null && precioObj != null) {
                            int articuloId = Integer.parseInt(idArticuloObj.toString().trim());
                            double cantDouble = Double.parseDouble(cantObj.toString().trim());
                            int cantidad = (int) Math.round(cantDouble);
                            double precioUnitario = Double.parseDouble(precioObj.toString().trim());

                            pstDetalle.setInt(1, idVentaGenerado);
                            pstDetalle.setInt(2, articuloId);
                            pstDetalle.setInt(3, cantidad);
                            pstDetalle.setDouble(4, precioUnitario);
                            pstDetalle.addBatch();
                        }
                    }

                    pstDetalle.executeBatch();
                }

                // ==========================================
                // 3. CONFIRMAR TRANSACCIÓN
                // ==========================================
                conexion.commit();

                // Actualizar acumuladores en la interfaz principal
                try {
                    if (tipoPago.equalsIgnoreCase("Efectivo")) {
                        VISTA.PRINCIPAL.ventasEfectivoDia += total;
                    } else if (tipoPago.equalsIgnoreCase("Tarjeta") || tipoPago.startsWith("Tarjeta")) {
                        VISTA.PRINCIPAL.ventasTarjetaDia += total;
                    }
                } catch (Exception ex) {
                    System.err.println("Aviso: No se pudieron actualizar los acumuladores de la interfaz: " + ex.getMessage());
                }

            } catch (SQLException ex) {
                // Si ocurre un fallo, revertir transacción
                conexion.rollback();
                throw ex;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Error al registrar la venta:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE
            );
            idVentaGenerado = -1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Error en el formato numérico del carrito:\n" + e.getMessage(), 
                "Error de Datos", 
                JOptionPane.ERROR_MESSAGE
            );
            idVentaGenerado = -1;
        }

        return idVentaGenerado;
    }
}