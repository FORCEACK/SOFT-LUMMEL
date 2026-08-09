/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ConsultasCompras extends ConexionBD {
    
    // Método para cargar los detalles de compra en la tabla de la vista
    public void mostrarCompras(DefaultTableModel modeloTabla) {
        // Limpiamos la tabla antes de cargar datos nuevos
        modeloTabla.setRowCount(0);
        
        // Consulta SQL con INNER JOIN para traer el nombre del artículo
        // Y calculamos el subtotal directamente (cantidad * precio)
        String sql = "SELECT d.articulo_id, a.nombre, d.cantidad, d.precio, (d.cantidad * d.precio) AS subtotal " +
                     "FROM detallecompra d " +
                     "INNER JOIN articulo a ON d.articulo_id = a.articulo_id"; 
                     
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Object[] fila = new Object[5];
                fila[0] = rs.getString("articulo_id");
                fila[1] = rs.getString("nombre"); 
                fila[2] = rs.getInt("cantidad");
                fila[3] = rs.getDouble("precio");
                fila[4] = rs.getDouble("subtotal");
                
                modeloTabla.addRow(fila);
            }
            con.close();
        } catch (Exception e) {
            System.err.println("Error al cargar los detalles de compra: " + e.getMessage());
        }
    }

    // Nuevo método para filtrar por fecha
    public void mostrarComprasPorFecha(DefaultTableModel modeloTabla, java.util.Date fechaSeleccionada) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        // Formateamos la fecha de Java al formato de MySQL (AÑO-MES-DIA)
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(fechaSeleccionada);

        // Consulta uniendo las 3 tablas. 
        String sql = "SELECT d.articulo_id, a.nombre, d.cantidad, d.precio, (d.cantidad * d.precio) AS subtotal " +
             "FROM detallecompra d " +
             "INNER JOIN articulo a ON d.articulo_id = a.articulo_id " + // <- Aquí estaba el error
             "INNER JOIN compra c ON d.id_Compra = c.id_Compra " +
             "WHERE DATE(c.fecha_compra) = ?";

        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fechaFormateada); // Le pasamos la fecha elegida
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Object[] fila = new Object[5];
                fila[0] = rs.getString("articulo_id");
                fila[1] = rs.getString("nombre"); // Revisa que la columna se llame 'nombre'
                fila[2] = rs.getInt("cantidad");
                fila[3] = rs.getDouble("precio");
                fila[4] = rs.getDouble("subtotal");
                modeloTabla.addRow(fila);
            }
            con.close();
        } catch (Exception e) {
            System.err.println("Error al cargar por fecha: " + e.getMessage());
        }
    }
    // =========================================================================
    // MÉTODO PARA CONFIRMAR COMPRA Y ACTUALIZAR STOCK (TRANSACCIÓN EN LOTE)
    // =========================================================================
    public boolean registrarCompraYActualizarStock(String proveedor, java.util.Date fecha, double total, javax.swing.table.DefaultTableModel modeloTabla) {
        java.sql.Connection con = null;
        
        try {
            con = MODELO.ConexionBD.conectar();
            con.setAutoCommit(false); // Apagamos el autoguardado para iniciar la transacción
            String sqlUpdateStock = "UPDATE Articulo SET stock = stock + ? WHERE articulo_id = ?";
            
            try (java.sql.PreparedStatement psStock = con.prepareStatement(sqlUpdateStock)) {
                
                // Recorremos todas las filas de la tabla de la vista
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    // Extraemos el ID (Columna 0) y la Cantidad comprada (Columna 2)
                    int idArticulo = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString()); 
                    double cantidadComprada = Double.parseDouble(modeloTabla.getValueAt(i, 3).toString()); 

                    psStock.setDouble(1, cantidadComprada);
                    psStock.setInt(2, idArticulo);
                    
                    // addBatch() agrupa las sentencias para enviarlas todas juntas a la base de datos
                    psStock.addBatch(); 
                }
                
                // Ejecutamos todas las actualizaciones de stock de un solo golpe
                psStock.executeBatch(); 
            }

            con.commit(); 
            return true;

        } catch (Exception e) {
            if (con != null) {
                try { con.rollback(); } catch (Exception ex) {} // Deshacemos si hay error
            }
            System.err.println("Error al procesar compra y stock: " + e.getMessage());
            return false;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); con.close(); } catch (Exception e) {}
            }
        }
    }

    // -------------------------------------------------------------------------
    // ---> (Buscador de Productos) <---
    // -------------------------------------------------------------------------

    // Método para buscar un artículo por su ID
    public String[] buscarArticuloPorID(String idArticulo) {
        // Arreglo para guardar: [0] ID, [1] Nombre, [2] Precio de Origen
        String[] datosArticulo = new String[3]; 
        
        // Hacemos la consulta SQL apuntando a las columnas exactas de tu tabla Articulo
        String sql = "SELECT articulo_id, nombre, precio_Origen FROM Articulo WHERE articulo_id = ?";
        
        try {
            // Usamos método conectar() para mantener el estándar de tu proyecto
            Connection con = conectar(); 
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idArticulo);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                datosArticulo[0] = rs.getString("articulo_id");
                datosArticulo[1] = rs.getString("nombre");
                datosArticulo[2] = rs.getString("precio_Origen"); 
            } else {
                return null; // Si no encuentra nada, regresa nulo
            }
            
            con.close();
            return datosArticulo;
            
        } catch (Exception e) {
            System.err.println("Error al buscar artículo: " + e.getMessage());
            return null;
        }
    }
    // Método para obtener la lista de proveedores desde la Base de Datos
    public java.util.ArrayList<String> obtenerProveedores() {
        java.util.ArrayList<String> listaProveedores = new java.util.ArrayList<>();
        String sql = "SELECT Razon_Social FROM Proveedor";
        
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                // Agregamos cada Razón Social que encuentre a nuestra lista
                listaProveedores.add(rs.getString("Razon_Social"));
            }
            con.close();
            
        } catch (Exception e) {
            System.err.println("Error al cargar proveedores: " + e.getMessage());
        }
        
        return listaProveedores;
    }
    // =========================================================================
    // MÉTODO PARA REGISTRAR UN NUEVO PROVEEDOR (TRANSACCIÓN SQL)
    // =========================================================================
    public boolean registrarProveedor(String nombre, String app, String apm, String razonSocial, String rfc) {
        String sql = "INSERT INTO Proveedor (Razon_Social, nombre, app, apm, RFC) VALUES (?, ?, ?, ?, ?)";
        
        try {
            java.sql.Connection con = conectar();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, razonSocial);
            ps.setString(2, nombre);
            ps.setString(3, app);
            
            // Si el Apellido Materno o RFC vienen vacíos, mandamos un NULL para que MySQL no marque error
            ps.setString(4, apm.isEmpty() ? null : apm);
            ps.setString(5, rfc.isEmpty() ? null : rfc);
            
            int filasAfectadas = ps.executeUpdate();
            con.close();
            
            return filasAfectadas > 0;
            
        } catch (Exception e) {
            System.err.println("Error al registrar proveedor: " + e.getMessage());
            return false;
        }
    }
}