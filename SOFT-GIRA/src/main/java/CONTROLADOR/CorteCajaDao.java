
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CorteCajaDao {

    // Verificar si el usuario tiene una caja abierta actualmente
    public int obtenerIdCorteAbierto(int idUsuario, Connection conexion) {
        String sql = "SELECT id_Corte FROM cortecaja WHERE id_Usuario = ? AND estatus = 'ABIERTO' LIMIT 1";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_Corte");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar caja abierta: " + e.getMessage());
        }
        return 0; // 0 significa que no hay caja abierta
    }

    // 1. ABRIR CAJA
    public boolean abrirCaja(int idUsuario, double fondoInicial, Connection conexion) {
        // Validar si ya tiene una abierta
        if (obtenerIdCorteAbierto(idUsuario, conexion) > 0) {
            return false; // Ya tiene una caja abierta
        }

        String sql = "INSERT INTO cortecaja (id_Usuario, fecha_apertura, fondo_inicial, estatus) VALUES (?, NOW(), ?, 'ABIERTO')";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setDouble(2, fondoInicial);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al abrir caja: " + e.getMessage());
            return false;
        }
    }

    // 2. REALIZAR CORTE Y CERRAR CAJA
    public boolean cerrarCaja(int idCorte, int idUsuario, double efectivoEnCajaFisico, Connection conexion) {
        // Consultar ventas hechas por el usuario desde la apertura de este corte
        String sqlTotales = "SELECT " +
                "COALESCE(SUM(pago_efectivo), 0) AS ventas_efectivo, " +
                "COALESCE(SUM(pago_tarjeta), 0) AS ventas_tarjeta, " +
                "COALESCE(SUM(total_venta), 0) AS total_general " +
                "FROM documentoventa " +
                "WHERE id_Usuario = ? AND fecha_venta >= (SELECT fecha_apertura FROM cortecaja WHERE id_Corte = ?)";

        String sqlFondo = "SELECT fondo_inicial FROM cortecaja WHERE id_Corte = ?";
        
        String sqlUpdate = "UPDATE cortecaja SET " +
                "fecha_cierre = NOW(), " +
                "total_ventas_efectivo = ?, " +
                "total_ventas_tarjeta = ?, " +
                "total_ventas_general = ?, " +
                "total_efectivo_esperado = ?, " +
                "efectivo_en_caja = ?, " +
                "diferencia = ?, " +
                "estatus = 'CERRADO' " +
                "WHERE id_Corte = ?";

        try {
            double fondoInicial = 0;
            double vEfectivo = 0;
            double vTarjeta = 0;
            double vGeneral = 0;

            // Obtener fondo inicial
            try (PreparedStatement psF = conexion.prepareStatement(sqlFondo)) {
                psF.setInt(1, idCorte);
                try (ResultSet rsF = psF.executeQuery()) {
                    if (rsF.next()) {
                        fondoInicial = rsF.getDouble("fondo_inicial");
                    }
                }
            }

            // Obtener sumatorias de ventas de documentoventa
            try (PreparedStatement psT = conexion.prepareStatement(sqlTotales)) {
                psT.setInt(1, idUsuario);
                psT.setInt(2, idCorte);
                try (ResultSet rsT = psT.executeQuery()) {
                    if (rsT.next()) {
                        vEfectivo = rsT.getDouble("ventas_efectivo");
                        vTarjeta = rsT.getDouble("ventas_tarjeta");
                        vGeneral = rsT.getDouble("total_general");
                    }
                }
            }

            // Cálculos matemáticos del arqueo
            double efectivoEsperado = fondoInicial + vEfectivo;
            double diferencia = efectivoEnCajaFisico - efectivoEsperado;

            // Guardar cambios en la base de datos
            try (PreparedStatement psU = conexion.prepareStatement(sqlUpdate)) {
                psU.setDouble(1, vEfectivo);
                psU.setDouble(2, vTarjeta);
                psU.setDouble(3, vGeneral);
                psU.setDouble(4, efectivoEsperado);
                psU.setDouble(5, efectivoEnCajaFisico);
                psU.setDouble(6, diferencia);
                psU.setInt(7, idCorte);
                psU.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error al cerrar caja: " + e.getMessage());
            return false;
        }
    }
}