/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConsultasFinanzas {

    // Método de apoyo para no repetir la creación de las columnas
    private DefaultTableModel crearModeloBase() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Total");
        return modelo;
    }

    // =========================================================================
    // 1. CONSULTA POR DÍA EXACTO
    // =========================================================================
    public DefaultTableModel ventasPorDia(String fechaSQL) {
        DefaultTableModel modelo = crearModeloBase();
        
        // Se cambió a.codigo por a.articulo_id
        String sql = "SELECT a.articulo_id AS codigo, a.nombre AS Producto, dv.cantidad, dv.precio_unitario AS Precio, (dv.cantidad * dv.precio_unitario) AS Total "
                   + "FROM DetalleVenta dv "
                   + "INNER JOIN DocumentoVenta v ON dv.id_Documento = v.id_DocumentoVenta "
                   + "INNER JOIN Articulo a ON dv.articulo_id = a.articulo_id "
                   + "WHERE DATE(v.fecha_venta) = ?";
                   
        ConexionBD conexion = new ConexionBD();
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, fechaSQL);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[5];
                    fila[0] = rs.getInt("codigo"); // Ahora extrae el articulo_id numérico
                    fila[1] = rs.getString("Producto"); 
                    fila[2] = rs.getInt("cantidad");
                    fila[3] = rs.getDouble("Precio");
                    fila[4] = rs.getDouble("Total");
                    modelo.addRow(fila);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar ventas por día: " + e.getMessage());
        }
        return modelo;
    }

    // =========================================================================
    // 2. CONSULTA POR RANGO (SEMANAL O CUALQUIER PERIODO)
    // =========================================================================
    public DefaultTableModel ventasPorRango(String fechaInicio, String fechaFin) {
        DefaultTableModel modelo = crearModeloBase();
        
        String sql = "SELECT a.articulo_id AS codigo, a.nombre AS Producto, dv.cantidad, dv.precio_unitario AS Precio, (dv.cantidad * dv.precio_unitario) AS Total "
                   + "FROM DetalleVenta dv "
                   + "INNER JOIN DocumentoVenta v ON dv.id_Documento = v.id_DocumentoVenta "
                   + "INNER JOIN Articulo a ON dv.articulo_id = a.articulo_id "
                   + "WHERE DATE(v.fecha_venta) BETWEEN ? AND ?";
                   
        ConexionBD conexion = new ConexionBD();
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[5];
                    fila[0] = rs.getInt("codigo");
                    fila[1] = rs.getString("Producto");
                    fila[2] = rs.getInt("cantidad");
                    fila[3] = rs.getDouble("Precio");
                    fila[4] = rs.getDouble("Total");
                    modelo.addRow(fila);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar ventas por rango: " + e.getMessage());
        }
        return modelo;
    }

    // =========================================================================
    // 3. CONSULTA POR MES Y AÑO
    // =========================================================================
    public DefaultTableModel ventasPorMes(int mes, int anio) {
        DefaultTableModel modelo = crearModeloBase();
        
        String sql = "SELECT a.articulo_id AS codigo, a.nombre AS Producto, dv.cantidad, dv.precio_unitario AS Precio, (dv.cantidad * dv.precio_unitario) AS Total "
                   + "FROM DetalleVenta dv "
                   + "INNER JOIN DocumentoVenta v ON dv.id_Documento = v.id_DocumentoVenta "
                   + "INNER JOIN Articulo a ON dv.articulo_id = a.articulo_id "
                   + "WHERE MONTH(v.fecha_venta) = ? AND YEAR(v.fecha_venta) = ?";
                   
        ConexionBD conexion = new ConexionBD();
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[5];
                    fila[0] = rs.getInt("codigo");
                    fila[1] = rs.getString("Producto");
                    fila[2] = rs.getInt("cantidad");
                    fila[3] = rs.getDouble("Precio");
                    fila[4] = rs.getDouble("Total");
                    modelo.addRow(fila);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar ventas por mes: " + e.getMessage());
        }
        return modelo;
    }
}