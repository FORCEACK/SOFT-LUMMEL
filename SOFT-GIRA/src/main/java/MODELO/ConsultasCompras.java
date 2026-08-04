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
                
                // NOTA: Si en tu tabla 'articulo' la columna del nombre se llama diferente 
                // (ej. 'descripcion' o 'nombre_articulo'), cámbialo en la línea de abajo.
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
        // ¡ATENCIÓN! Revisa 'a.id_articulo' y asegúrate de que sea el nombre correcto en tu BD
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

    // -------------------------------------------------------------------------
    // ---> AQUÍ EMPIEZA EL CÓDIGO NUEVO DEL PASO 1 (Buscador de Productos) <---
    // -------------------------------------------------------------------------

    // Método para buscar un artículo por su ID
    public String[] buscarArticuloPorID(String idArticulo) {
        // Arreglo para guardar: [0] ID, [1] Nombre, [2] Precio de Origen
        String[] datosArticulo = new String[3]; 
        
        // Hacemos la consulta SQL apuntando a las columnas exactas de tu tabla Articulo
        String sql = "SELECT articulo_id, nombre, precio_Origen FROM Articulo WHERE articulo_id = ?";
        
        try {
            // Usamos tu método conectar() para mantener el estándar de tu proyecto
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
}