package MODELO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {

    // Centralizamos las credenciales aquí (Variables globales, privadas y estáticas)
    //private static final String url = "jdbc:mysql://192.168.0.11:3306/lummel_db";
//    private static final String url = "jdbc:mysql://26.91.85.79:3306/lummel_db";
//    private static final String user = "robot";
//    private static final String pass = "basededatos12345"; 

    private static final String url = "jdbc:mysql://localhost:3306/lummel_db";
private static final String user = "root"; 
private static final String pass = "";
    
    // Método ESTÁTICO que permite ser llamado directamente como ConexionBD.conectar()
    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con SOFT-GIRA BD: " + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

    // Método de prueba rápida opcional
    public static void main(String[] args) {
        Connection con = conectar();
        if (con != null) {
            JOptionPane.showMessageDialog(null, 
                "¡CONEXIÓN EXITOSA! 🎉\nLa aplicación se comunicó correctamente con MySQL.", 
                "Prueba de Conexión", 
                JOptionPane.INFORMATION_MESSAGE);
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                "❌ FALLÓ LA CONEXIÓN.\nNo se pudo establecer el puente con MySQL.", 
                "Prueba de Conexión", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}