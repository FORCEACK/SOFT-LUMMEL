package MODELO;

import MODELO.ConexionBD; // Importamos tu clase de conexión
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasUsuario {

    // =========================================================================
    // 1. MÉTODO PARA VALIDAR EL ACCESO EN LA BASE DE DATOS
    // =========================================================================
    public boolean autenticarUsuario(String username, String password) {
        // Encriptamos la contraseña recibida antes de ir a la BD
        String hashedPass = hashPassword(password);
        
        // La consulta SQL (Solo entran usuarios con estatus 1 = Activos)
        String sql = "SELECT * FROM Usuario WHERE username = ? AND password = ? AND estatus = 1";
        
        // Instanciamos tu conexión centralizada
        ConexionBD conexion = new ConexionBD();
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ps.setString(2, hashedPass);
            
            try (ResultSet rs = ps.executeQuery()) {
                // Si rs.next() es true, encontró al usuario. Si es false, datos incorrectos.
                return rs.next(); 
            }
            
        } catch (SQLException e) {
            System.err.println("Error en la consulta de autenticación: " + e.getMessage());
            return false; // Si la base de datos falla, negamos el acceso por seguridad
        }
    }

    // =========================================================================
    // 2. NUEVO MÉTODO: OBTENER ID DEL USUARIO POR SU USERNAME
    // =========================================================================
    public int obtenerIdUsuario(String username) {
        int idUsuario = -1;
        String sql = "SELECT id_Usuario FROM Usuario WHERE username = ?";
        
        ConexionBD conexion = new ConexionBD();
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idUsuario = rs.getInt("id_Usuario");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ID del usuario: " + e.getMessage());
        }
        return idUsuario;
    }

    // =========================================================================
    // 3. MÉTODO PARA ACTUALIZAR ESTADO DE SESIÓN (EN LÍNEA)
    // =========================================================================
    public boolean actualizarEstadoSesion(String username, int estadoEnLinea) {
        // Si estadoEnLinea == 1 (Inicia sesión), actualizamos la fecha y hora actual (NOW())
        String sql = (estadoEnLinea == 1) 
            ? "UPDATE Usuario SET en_linea = ?, ultimo_acceso = NOW() WHERE username = ?"
            : "UPDATE Usuario SET en_linea = ? WHERE username = ?";
            
        ConexionBD conexion = new ConexionBD();
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, estadoEnLinea);
            ps.setString(2, username);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado de sesión: " + e.getMessage());
            return false;
        }
    }

    // =========================================================================
    // 4. MÉTODO DE ENCRIPTACIÓN SHA-256
    // =========================================================================
    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            System.err.println("Error al encriptar la contraseña: " + ex.getMessage());
            return null;
        }
    }
    // =========================================================================
    // MÉTODO PARA VERIFICAR SI EL USUARIO YA ESTÁ EN LÍNEA
    // =========================================================================
    public boolean estaUsuarioEnLinea(String username) {
        String sql = "SELECT en_linea FROM Usuario WHERE username = ?";
        
        ConexionBD conexion = new ConexionBD();
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int enLinea = rs.getInt("en_linea");
                    return enLinea == 1; // Retorna true si ya está conectado
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar estado en línea: " + e.getMessage());
        }
        return false;
    }
}

    
