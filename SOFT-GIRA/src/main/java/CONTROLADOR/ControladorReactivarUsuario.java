/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasPanelUsuarios;
import javax.swing.JOptionPane;
import java.awt.Component;

public class ControladorReactivarUsuario {

    public boolean procesarReactivacion(Component vista) {
        
        // 1. Pedimos el nombre de usuario (username)
        String usernameIngresado = JOptionPane.showInputDialog(vista, 
                "Ingresa el NOMBRE DE USUARIO de la cuenta que deseas REACTIVAR:", 
                "Reactivar Usuario", 
                JOptionPane.QUESTION_MESSAGE);
        
        // 2. Validamos que no haya cancelado y que haya escrito algo
        if (usernameIngresado != null && !usernameIngresado.trim().isEmpty()) {
            
            // 3. Llamamos al modelo
            ConsultasPanelUsuarios modelo = new ConsultasPanelUsuarios();
            boolean exito = modelo.reactivarUsuario(usernameIngresado.trim());
            
            if (exito) {
                JOptionPane.showMessageDialog(vista, "¡El usuario '" + usernameIngresado + "' ha sido reactivado con éxito!\nYa puede volver a iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró ningún usuario inactivo con el nombre: " + usernameIngresado, "Atención", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        
        return false;
    }
}