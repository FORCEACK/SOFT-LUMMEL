/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasPanelUsuarios;
import javax.swing.JOptionPane;
import java.awt.Component;

public class ControladorEliminarUsuario {

    public boolean procesarEliminacion(int idBuscar, Component vista) {
        
        // 1. Lanzamos una alerta de confirmación (Botones Sí / No)
        int respuesta = JOptionPane.showConfirmDialog(vista, 
                "¿Estás seguro de que deseas ELIMINAR permanentemente al usuario con ID " + idBuscar + "?\nEsta acción no se puede deshacer.", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
                
        // 2. Si el administrador da clic en "Sí"
        if (respuesta == JOptionPane.YES_OPTION) {
            ConsultasPanelUsuarios modelo = new ConsultasPanelUsuarios();
            boolean exito = modelo.eliminarUsuario(idBuscar);
            
            if(exito) {
                JOptionPane.showMessageDialog(vista, "¡Usuario eliminado correctamente del sistema!");
                return true;
            } else {
                JOptionPane.showMessageDialog(vista, "Error: No se pudo eliminar el usuario o el ID no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        // Si le dio a "No" o cerró la ventana
        return false; 
    }
}