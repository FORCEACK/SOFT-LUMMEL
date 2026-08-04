/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
            return false; // Si la base de datos falla (ej. se cae el VPN), negamos el acceso por seguridad
        }
    }
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
    // 2. MÉTODO DE ENCRIPTACIÓN SHA-256 (Mudado desde la Vista)
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
}
    
