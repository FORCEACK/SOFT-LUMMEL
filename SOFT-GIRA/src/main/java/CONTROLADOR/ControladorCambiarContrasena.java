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
        
        String passHash = nuevaPass; 
        
        // Llamamos al método en ConsultasPanelUsuarios
        return modelo.actualizarContrasena(idUsuario, passHash);
    }
}