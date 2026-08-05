package CONTROLADOR;

import MODELO.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorCorte {

    /**
     * Busca el corte abierto de un usuario.
     */
public static int obtenerCorteAbierto() {
    String sql = """
        SELECT id_Corte
        FROM cortecaja
        WHERE estatus = 'ABIERTO'
        ORDER BY id_Corte DESC
        LIMIT 1
        """;

    try (Connection con = ConexionBD.conectar()) {
        if (con == null) {
            return -1;
        }
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_Corte");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener corte abierto: " + e.getMessage());
    }
    return -1;
}
}