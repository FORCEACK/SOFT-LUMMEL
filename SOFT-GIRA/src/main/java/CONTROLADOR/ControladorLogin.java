/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasUsuario;
import VISTA.PRINCIPAL;
import VISTA.USUARIO;
import javax.swing.JOptionPane;

public class ControladorLogin {

    // ¡AQUÍ ESTÁ LA LÍNEA QUE FALTABA! El método que envuelve toda la lógica
    public void procesarLogin(String username, String password, USUARIO vistaLogin) {
        
        // 1. Filtro básico de seguridad (Validar vacíos)
        if (username.isEmpty() || password.isEmpty() || username.equals("Ingresar Usuario")) {
            JOptionPane.showMessageDialog(vistaLogin, "Por favor, introduce tu usuario y contraseña.");
            return;
        }

        // 2. Llamamos al MODELO para que haga la conexión y el SQL
        ConsultasUsuario modelo = new ConsultasUsuario();
        boolean accesoConcedido = modelo.autenticarUsuario(username, password);

        // 3. Tomamos una decisión basada en la respuesta del Modelo
        if (accesoConcedido) {
            
            // --- NUEVO: GUARDAR LA SESIÓN Y PONERLO ACTIVO (1) EN LA BD ---
            // Guardamos globalmente quién entró al sistema (Código de tu compañero)
            MODELO.SesionActual.usuarioLogueado = username; 
            
            // Cambiamos el estado a 1 (Conectado) en la Base de Datos
            modelo.actualizarEstadoSesion(username, 1);

            // Éxito: Cerramos la vista de login y abrimos el menú principal
            vistaLogin.dispose(); 
            
            PRINCIPAL menu = new PRINCIPAL();
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            
        } else {
            // Error: Mandamos la alerta (Código de tu compañero)
            JOptionPane.showMessageDialog(vistaLogin, "ACCESO DENEGADO...", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
        }        
    } // Fin del método procesarLogin

} // Fin de la clase ControladorLogin