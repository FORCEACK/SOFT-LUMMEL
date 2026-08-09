/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasPanelUsuarios;
import VISTA.FrmNuevoUsuario;
import javax.swing.JOptionPane;
import java.nio.charset.StandardCharsets;

public class ControladorNuevoUsuario {
    
    public void procesarRegistro(String nombre, String app, String apm, String tel, String user, String passPlana, String rolSeleccionado, FrmNuevoUsuario vistaNuevo) {
        
        // 1. Validar que los campos más importantes no estén vacíos
        if(nombre.isEmpty() || app.isEmpty() || user.isEmpty() || passPlana.isEmpty()) {
            JOptionPane.showMessageDialog(vistaNuevo, "Por favor llena todos los campos obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Convertir el texto del ComboBox a su ID numérico correspondiente
        // OJO: Ajusta las palabras y los números según los IDs que tu compañero puso en la tabla 'Rol'
        int idRol = 2; // Por defecto, asumimos que es Vendedor (ID 2)
        if(rolSeleccionado.equalsIgnoreCase("Administrador")) {
            idRol = 1;
        } else if (rolSeleccionado.equalsIgnoreCase("Almacen")) { // Ajusta si tienes otros roles
            idRol = 3; 
        }

        // 3. Encriptar la contraseña ingresada
        String passHash = hashPassword(passPlana);

        // 4. Mandar todo al Modelo para que haga la Transacción SQL
        ConsultasPanelUsuarios modelo = new ConsultasPanelUsuarios();
        boolean exito = modelo.registrarUsuario(nombre, app, apm, tel, user, passHash, idRol);

        // 5. Tomar decisión según el resultado de la Base de Datos
        if(exito) {
            JOptionPane.showMessageDialog(vistaNuevo, "¡Usuario registrado correctamente en SOFT-GIRA!");
            vistaNuevo.dispose(); // Cierra la ventana del formulario
        } else {
            JOptionPane.showMessageDialog(vistaNuevo, "Error al registrar el usuario en la Base de Datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================================
    // MÉTODO DE ENCRIPTACIÓN SHA-256
    // =========================================================================
    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            return null;
        }
    }
}
