/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import MODELO.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

public class ConsultasPanelUsuarios {

    public DefaultTableModel cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE COMPLETO");
        modelo.addColumn("USUARIO");
        modelo.addColumn("ROL");
        modelo.addColumn("ESTADO");
        modelo.addColumn("ULTIMO ACCESO");

        // Obtenemos el usuario de la sesión activa actual
        String usuarioSesion = MODELO.SesionActual.usuarioLogueado;
        // Seleccionamos también el campo 'ultimo_acceso'
        String sql = "SELECT u.id_Usuario, "
                   + "CONCAT(p.Nombre, ' ', p.APP, ' ', p.APM) AS NombreCompleto, "
                   + "u.username, "
                   + "IFNULL(r.nombre_rol, 'Sin Asignar') AS Rol, "
                   + "u.estatus, " 
                   + "u.ultimo_acceso "
                   + "FROM Usuario u "
                   + "INNER JOIN Persona p ON u.id_Persona = p.id_Persona "
                   + "LEFT JOIN TIENE t ON u.id_Usuario = t.id_Usuario "
                   + "LEFT JOIN Rol r ON t.id_Rol = r.id_Rol "
                   + "WHERE u.estatus = 1 " // <--- ESTA ES LA LÍNEA QUE OCULTA A LOS INACTIVOS
                   + "ORDER BY u.id_Usuario ASC";
        ConexionBD conexion = new ConexionBD();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[6];
                String userBD = rs.getString("username");

                fila[0] = rs.getInt("id_Usuario");
                fila[1] = rs.getString("NombreCompleto");
                fila[2] = userBD;
                fila[3] = rs.getString("Rol");
                
               // --- LÓGICA DE ESTADO ---
                // Leemos el estatus directamente de la base de datos (1 = Activo, 0 = Inactivo)
                int estatusBD = rs.getInt("estatus");
                
                if (estatusBD == 1) {
                    fila[4] = "Activo";
                } else {
                    fila[4] = "Inactivo";
                }

                // --- FECHA Y HORA ÚLTIMO ACCESO ---
                java.sql.Timestamp fechaAcceso = rs.getTimestamp("ultimo_acceso");
                if (fechaAcceso != null) {
                    fila[5] = sdf.format(fechaAcceso);
                } else {
                    fila[5] = "Sin Registro";
                }

                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar la tabla de usuarios: " + e.getMessage());
        }
        
        return modelo;
    } 
    // =========================================================================
    // MÉTODO PARA REGISTRAR UN NUEVO USUARIO (TRANSACCIÓN SQL)
    // =========================================================================
    public boolean registrarUsuario(String nombre, String app, String apm, String tel,  String user, String passHash, int idRol) {
        
        ConexionBD conexion = new ConexionBD();
        Connection con = conexion.conectar();
        
        // Consultas de inserción
        String sqlPersona = "INSERT INTO Persona (Nombre, APP, APM, Telefono) VALUES (?, ?, ?, ?)";
        String sqlUsuario = "INSERT INTO Usuario (username, password, estatus, id_Persona) VALUES (?, ?, 1, ?)";
        String sqlTiene = "INSERT INTO TIENE (id_Usuario, id_Rol) VALUES (?, ?)";

        try {
            // Apagamos el autoguardado para asegurar que las 3 tablas se inserten juntas
            con.setAutoCommit(false); 

            // 1. Guardar Persona y recuperar su ID generado
            int idPersonaGenerado = 0;
            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psPersona.setString(1, nombre);
                psPersona.setString(2, app);
                psPersona.setString(3, apm);
                psPersona.setString(4, tel);
                psPersona.executeUpdate();

                ResultSet rsPersona = psPersona.getGeneratedKeys();
                if (rsPersona.next()) {
                    idPersonaGenerado = rsPersona.getInt(1);
                }
            }

            // 2. Guardar Usuario usando el ID de la Persona
            int idUsuarioGenerado = 0;
            try (PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psUsuario.setString(1, user);
                psUsuario.setString(2, passHash); // Recibirá la contraseña ya encriptada
                psUsuario.setInt(3, idPersonaGenerado);
                psUsuario.executeUpdate();
                
                ResultSet rsUsuario = psUsuario.getGeneratedKeys();
                if (rsUsuario.next()) {
                    idUsuarioGenerado = rsUsuario.getInt(1);
                }
            }

            // 3. Guardar el Rol en la tabla TIENE
            try (PreparedStatement psTiene = con.prepareStatement(sqlTiene)) {
                psTiene.setInt(1, idUsuarioGenerado);
                psTiene.setInt(2, idRol);
                psTiene.executeUpdate();
            }
            con.commit();
            return true;

        } catch (SQLException e) {
            // Si algo falla, se deshace todos los cambios para no dejar basura
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al deshacer la transacción: " + ex.getMessage());
            }
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        } finally {
            // Cerramos la conexión
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
    // =========================================================================
    // 1. MÉTODO PARA BUSCAR UN USUARIO POR SU ID
    // =========================================================================
    public String[] buscarUsuarioPorId(int idUsuario) {
        String[] datos = new String[7]; // Arreglo para guardar los datos encontrados
        
        String sql = "SELECT u.id_Usuario, p.Nombre, p.APP, p.APM, p.Telefono, u.username, r.nombre_rol "
                   + "FROM Usuario u "
                   + "INNER JOIN Persona p ON u.id_Persona = p.id_Persona "
                   + "LEFT JOIN TIENE t ON u.id_Usuario = t.id_Usuario "
                   + "LEFT JOIN Rol r ON t.id_Rol = r.id_Rol "
                   + "WHERE u.id_Usuario = ?";
                   
        ConexionBD conexion = new ConexionBD();
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, idUsuario);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    datos[0] = rs.getString("id_Usuario");
                    datos[1] = rs.getString("Nombre");
                    datos[2] = rs.getString("APP");
                    datos[3] = rs.getString("APM");
                    datos[4] = rs.getString("Telefono");
                    datos[5] = rs.getString("username");
                    datos[6] = rs.getString("nombre_rol");
                    return datos; // Regresamos los datos llenos
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por ID: " + e.getMessage());
        }
        return null; // Si no encuentra nada, regresa null
    }

    // =========================================================================
    // 2. MÉTODO PARA ACTUALIZAR LOS DATOS DEL USUARIO
    // =========================================================================
    public boolean actualizarUsuario(int idUsuario, String nombre, String app, String apm, String tel, String user, int idRol) {
        ConexionBD conexion = new ConexionBD();
        Connection con = conexion.conectar();
        
        // Consultas UPDATE usando JOIN para llegar a la Persona desde el Usuario
        String sqlPersona = "UPDATE Persona p INNER JOIN Usuario u ON p.id_Persona = u.id_Persona "
                          + "SET p.Nombre = ?, p.APP = ?, p.APM = ?, p.Telefono = ? WHERE u.id_Usuario = ?";
        String sqlUsuario = "UPDATE Usuario SET username = ? WHERE id_Usuario = ?";
        String sqlTiene = "UPDATE TIENE SET id_Rol = ? WHERE id_Usuario = ?";

        try {
            con.setAutoCommit(false); // Iniciamos transacción

            // 1. Actualizar Persona
            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona)) {
                psPersona.setString(1, nombre);
                psPersona.setString(2, app);
                psPersona.setString(3, apm);
                psPersona.setString(4, tel);
                psPersona.setInt(5, idUsuario);
                psPersona.executeUpdate();
            }

            // 2. Actualizar Usuario
            try (PreparedStatement psUsuario = con.prepareStatement(sqlUsuario)) {
                psUsuario.setString(1, user);
                psUsuario.setInt(2, idUsuario);
                psUsuario.executeUpdate();
            }

            // 3. Actualizar Rol
            try (PreparedStatement psTiene = con.prepareStatement(sqlTiene)) {
                psTiene.setInt(1, idRol);
                psTiene.setInt(2, idUsuario);
                psTiene.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        } finally {
            try { con.setAutoCommit(true); con.close(); } catch (SQLException e) {}
        }
    }
    // =========================================================================
    // 3. MÉTODO PARA ELIMINAR UN USUARIO POR COMPLETO (TRANSACCIÓN SQL)
    // =========================================================================
    public boolean eliminarUsuario(int idUsuario) {
        ConexionBD conexion = new ConexionBD();
        
        // Actualizamos el campo 'estatus' a 0 (Inactivo)
        String sql = "UPDATE Usuario SET estatus = 0 WHERE id_Usuario = ?";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            // executeUpdate devuelve el número de filas afectadas
            int filasAfectadas = ps.executeUpdate();
            
            // Si modificó al menos una fila, retorna true
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al desactivar usuario: " + e.getMessage());
            return false;
        }
    }
    // =========================================================================
    // 4. MÉTODO PARA ACTUALIZAR SOLO LA CONTRASEÑA
    // =========================================================================
    public boolean actualizarContrasena(int idUsuario, String nuevaPassHash) {
        ConexionBD conexion = new ConexionBD();
        String sql = "UPDATE Usuario SET password = ? WHERE id_Usuario = ?";
        
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, nuevaPassHash);
            ps.setInt(2, idUsuario);
            
            // Si executeUpdate es mayor a 0, significa que sí modificó la fila
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al cambiar contraseña: " + e.getMessage());
            return false;
        }
    }
    // =========================================================================
    // 5. MÉTODO PARA REACTIVAR UN USUARIO INACTIVO
    // =========================================================================
    public boolean reactivarUsuario(String username) {
        ConexionBD conexion = new ConexionBD();
        
        // Cambiamos el estatus a 1 (Activo) buscando por su nombre de usuario
        String sql = "UPDATE Usuario SET estatus = 1 WHERE username = ?";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Retorna true si encontró al usuario y lo actualizó

        } catch (SQLException e) {
            System.err.println("Error al reactivar usuario: " + e.getMessage());
            return false;
        }
    }
}
