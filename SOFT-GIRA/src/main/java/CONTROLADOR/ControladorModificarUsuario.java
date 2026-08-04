/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasPanelUsuarios;
import VISTA.FrmModificarUsuario;
import javax.swing.JOptionPane;

public class ControladorModificarUsuario {

    // 1. Método que pide al Modelo que busque al usuario
    public String[] buscarUsuario(int idBuscar) {
        ConsultasPanelUsuarios modelo = new ConsultasPanelUsuarios();
        return modelo.buscarUsuarioPorId(idBuscar);
    }

    // 2. Método para procesar los datos editados y mandarlos a guardar
    public void procesarModificacion(String idStr, String nombre, String app, String apm, String tel, String user, String rolSeleccionado, FrmModificarUsuario vista) {
        
        // Evitamos que dejen campos vacíos por error
        if(nombre.isEmpty() || app.isEmpty() || user.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor llena los campos obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertimos el ID (texto) a número, y el Rol a su ID numérico
        int idUsuario = Integer.parseInt(idStr);
        int idRol = 2; // Vendedor por defecto
        if(rolSeleccionado.equalsIgnoreCase("Administrador")) {
            idRol = 1;
        } else if(rolSeleccionado.equalsIgnoreCase("Almacen")) { // Ajusta si tienes otros roles
            idRol = 3;
        }

        // Mandamos a actualizar
        ConsultasPanelUsuarios modelo = new ConsultasPanelUsuarios();
        boolean exito = modelo.actualizarUsuario(idUsuario, nombre, app, apm, tel, user, idRol);

        if(exito) {
            JOptionPane.showMessageDialog(vista, "¡Usuario modificado correctamente!");
            vista.dispose(); // Cerramos la ventana
        } else {
            JOptionPane.showMessageDialog(vista, "Error al modificar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    }

