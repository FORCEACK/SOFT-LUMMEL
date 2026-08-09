/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasPanelUsuarios;

public class ControladorCambiarContrasena {

    public boolean procesarCambioContrasena(int idUsuario, String nuevaPass) {
        // Instanciamos tu clase de consultas
        ConsultasPanelUsuarios modelo = new ConsultasPanelUsuarios();
        
        // ¡IMPORTANTE! 
        // Si estás encriptando las contraseñas en tu sistema (como sugiere el parámetro 'nuevaPassHash' de tu modelo),
        // asegúrate de encriptar 'nuevaPass' aquí antes de enviarla.
        // Ejemplo: String passHash = Seguridad.encriptar(nuevaPass);
        String passHash = nuevaPass; 
        
        // Llamamos al método que ya tienes en ConsultasPanelUsuarios
        return modelo.actualizarContrasena(idUsuario, passHash);
    }
}