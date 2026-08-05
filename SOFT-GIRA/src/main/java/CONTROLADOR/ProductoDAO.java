package CONTROLADOR;

import MODELO.ConexionBD;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mendez
 */
public class ProductoDAO {
public List<Object[]> listarProductosParaTabla() {
    List<Object[]> lista = new ArrayList<>();
String sql = "SELECT a.articulo_id AS codigo, a.nombre AS producto, u.nombre AS presentacion, a.precio_Origen AS costo, a.precio_Venta, a.stock, a.stockMinimo AS minimo FROM Articulo a INNER JOIN UnidadMedida u ON a.idMedida = u.idMedida ORDER BY a.articulo_id ASC";    
    // Intentamos conectar
    Connection con = ConexionBD.conectar();

    // 🛑 Validación indispensable: si no hay conexión, abortamos antes de tronar
    if (con == null) {
        System.out.println("⚠️ No se pudo obtener la conexión a la base de datos.");
        return lista; // Regresa la lista vacía de forma segura
    }

    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            // Se leen usando los ALIAS definidos en la consulta SQL de tu compañero
            int codigo = rs.getInt("codigo"); // Asegúrate de que sea int
            String producto = rs.getString("producto");
            String presentacion = rs.getString("presentacion");
            double costo = rs.getDouble("costo");
            double precio = rs.getDouble("precio_Venta");
            int stock = rs.getInt("stock");
            int minimo = rs.getInt("minimo");

            String estado = (stock <= 0) ? "⚪ AGOTADO" : (stock <= minimo) ? "🔴 BAJO" : "🟢 OK";
            String acciones = "⚙️ Opciones";

            Object[] fila = new Object[]{
                codigo, producto, presentacion, costo, precio, stock, minimo, estado, acciones
            };

            lista.add(fila);
        }

    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Error al consultar la tabla Articulo: " + e.getMessage(), 
            "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return lista;
}
}