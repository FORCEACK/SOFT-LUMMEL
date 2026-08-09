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
            if (modelo.estaUsuarioEnLinea(username)) {
                JOptionPane.showMessageDialog(vistaLogin, 
                    "El usuario '" + username + "' ya tiene una sesión activa en el sistema.\nPor favor, cierra la sesión actual antes de ingresar en otro equipo.", 
                    "Sesión Duplicada", 
                    JOptionPane.WARNING_MESSAGE);
                    
                return; // Detenemos la ejecución aquí, NO lo dejamos entrar
            }
            // ---> FIN DE LA VALIDACIÓN <---
            
            // --- GUARDAR LA SESIÓN DE USUARIO (NOMBRE E ID) ---
            MODELO.SesionActual.usuarioLogueado = username; 
            
            // Recuperación del ID dinámico para asociar Ventas y Cortes de Caja
            int idObtenido = modelo.obtenerIdUsuario(username);
            MODELO.SesionActual.idUsuarioLogueado = idObtenido;
            
            // Cambiamos el estado a 1 (Conectado) en la Base de Datos
            modelo.actualizarEstadoSesion(username, 1);

            // ---> INICIO DE LO NUEVO: RECUPERAR ROL Y APLICAR PERMISOS <---
            
            MODELO.ConsultasPanelUsuarios consultasUsuarios = new MODELO.ConsultasPanelUsuarios();
            String[] datosUsuario = consultasUsuarios.buscarUsuarioPorId(idObtenido);
            
            String rolDelUsuario = "Vendedor"; // Rol por defecto por seguridad
            if (datosUsuario != null && datosUsuario[6] != null) {
                rolDelUsuario = datosUsuario[6]; // La posición 6 trae el nombre del rol desde tu BD
            }
            vistaLogin.dispose(); 
            
            PRINCIPAL menu = new PRINCIPAL();
            
            // Invocamos el método de los permisos justo ANTES de hacer la ventana visible
            menu.aplicarPermisos(rolDelUsuario);
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            
        } else {
            // Error: Mandamos la alerta
            JOptionPane.showMessageDialog(vistaLogin, "ACCESO DENEGADO...", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
        }
    }}