package CONTROLADOR;

import MODELO.ConexionBD;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author mendez
 */
public class ProductoDAO {

    // ==========================================
    // 1. LISTAR PRODUCTOS EN TABLA
    // ==========================================
    public List<Object[]> listarProductosParaTabla() {
        List<Object[]> lista = new ArrayList<>();
        
        String sql = """
            SELECT 
                a.articulo_id AS codigo, 
                a.nombre AS producto, 
                a.descripcion AS presentacion, 
                u.nombre AS unidad_medida, 
                a.precio_Origen AS costo, 
                a.precio_Venta AS precio, 
                a.stock, 
                a.stockMinimo AS minimo 
            FROM Articulo a 
            LEFT JOIN UnidadMedida u ON a.idMedida = u.idMedida 
            ORDER BY a.articulo_id ASC
            """;    
        
        Connection con = ConexionBD.conectar();

        if (con == null) {
            System.out.println("⚠️ No se pudo obtener la conexión a la base de datos.");
            return lista; 
        }

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo"); 
                String producto = rs.getString("producto");
                String presentacion = rs.getString("presentacion");
                String unidadMedida = rs.getString("unidad_medida");
                double costo = rs.getDouble("costo");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                int minimo = rs.getInt("minimo");

                // Mensajes descriptivos con HTML para conservar color en Swing
             String estado;
if (stock <= 0) {
    estado = "Agotado";
} else if (stock <= minimo) {
    estado = "Por Agotarse";
} else {
    estado = "Disponible";
}

                Object[] fila = new Object[]{
                    codigo, 
                    producto, 
                    (presentacion != null ? presentacion : ""), 
                    (unidadMedida != null ? unidadMedida : ""), 
                    costo, 
                    precio, 
                    stock, 
                    minimo, 
                    estado
                };

                lista.add(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al consultar la tabla Articulo: " + e.getMessage(), 
                "Error SQL", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    // ==========================================
    // 2. REGISTRAR ARTÍCULO
    // ==========================================
    public boolean registrarArticulo(String nombre, String medidaNombre, double costo, double precioVenta, int stock, int stockMinimo, String categoriaNombre) {
        String sql = """
            INSERT INTO Articulo 
            (nombre, idMedida, precio_Origen, precio_Venta, stock, stockMinimo, categoria_id)
            VALUES (
                ?, 
                (SELECT idMedida FROM UnidadMedida WHERE nombre = ?), 
                ?, 
                ?, 
                ?, 
                ?, 
                (SELECT categoria_id FROM categoria WHERE Descripcion = ?)
            )
            """;

        Connection con = ConexionBD.conectar();
        if (con == null) return false;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, medidaNombre);
            ps.setDouble(3, costo);
            ps.setDouble(4, precioVenta);
            ps.setInt(5, stock);
            ps.setInt(6, stockMinimo);
            ps.setString(7, categoriaNombre);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al registrar el artículo: " + e.getMessage(), 
                "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ==========================================
    // 3. CARGAR CATEGORÍAS EN JCOMBOBOX
    // ==========================================
    public void cargarCategoriasCombo(JComboBox<String> combo) {
        String sql = "SELECT Descripcion FROM categoria ORDER BY categoria_id ASC";

        Connection con = ConexionBD.conectar();
        if (con == null) return;

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString("Descripcion"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al cargar las categorías: " + e.getMessage(), 
                "Error SQL", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ==========================================
    // 4. CARGAR UNIDADES DE MEDIDA EN JCOMBOBOX
    // ==========================================
    public void cargarUnidadesMedidaCombo(JComboBox<String> combo) {
        String sql = "SELECT nombre FROM UnidadMedida ORDER BY idMedida ASC";

        Connection con = ConexionBD.conectar();
        if (con == null) return;

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al cargar las unidades de medida: " + e.getMessage(), 
                "Error SQL", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ==========================================
    // 5. LLENAR JTABLE DIRECTAMENTE
    // ==========================================
    public void listarProductosEnTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        
        String[] titulos = {"Código", "Producto", "Presentación", "Unidad", "Costo", "P. Venta", "Stock", "Mínimo", "Estado"};
        modelo.setColumnIdentifiers(titulos);

        modelo.setRowCount(0);

        List<Object[]> lista = listarProductosParaTabla();

        for (Object[] fila : lista) {
            modelo.addRow(fila);
        }
    }
}