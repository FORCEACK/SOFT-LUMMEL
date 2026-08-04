package MODELO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {

    // Al agregar 'static' aquí, las variables pertenecen a la clase
    private static final String url = "jdbc:mysql://26.91.85.79:3306/lummel_db";
    private static final String user = "robot";
    private static final String pass = "basededatos12345"; 
    
    // Al agregar 'static' aquí, puedes llamarlo sin hacer un 'new ConexionBD()'
    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con SOFT-GIRA BD: " + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}