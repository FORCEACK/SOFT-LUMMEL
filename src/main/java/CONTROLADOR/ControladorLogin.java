package CONTROLADOR;

import MODELO.ConsultasUsuario;
import VISTA.PRINCIPAL;
import VISTA.USUARIO;
import javax.swing.JOptionPane;

public class ControladorLogin {

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
            
            // --- GUARDAR LA SESIÓN DE USUARIO (NOMBRE E ID) ---
            MODELO.SesionActual.usuarioLogueado = username; 
            
            // Recuperación del ID dinámico para asociar Ventas y Cortes de Caja
            int idObtenido = modelo.obtenerIdUsuario(username);
            MODELO.SesionActual.idUsuarioLogueado = idObtenido;
            
            // Cambiamos el estado a 1 (Conectado) en la Base de Datos
            modelo.actualizarEstadoSesion(username, 1);

            // Éxito: Cerramos la vista de login y abrimos el menú principal
            vistaLogin.dispose(); 
            
            PRINCIPAL menu = new PRINCIPAL();
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            
        } else {
            // Error: Mandamos la alerta
            JOptionPane.showMessageDialog(vistaLogin, "ACCESO DENEGADO...", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
        }        
    } // Fin del método procesarLogin

} // Fin de la clase ControladorLogin