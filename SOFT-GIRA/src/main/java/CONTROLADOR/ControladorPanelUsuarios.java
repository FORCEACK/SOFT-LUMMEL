/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasPanelUsuarios;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class ControladorPanelUsuarios {
    // Método que recibe tu jTable1 vacío y lo llena de información
    public void llenarTabla(JTable tabla) {
        
        // 1. Llamamos al Modelo para que haga la conexión y el SQL
        ConsultasPanelUsuarios modelo = new ConsultasPanelUsuarios();
        
        // 2. Recibimos el "molde" ya lleno con los datos de la base de datos
        DefaultTableModel modeloLleno = modelo.cargarTabla();
        
        // 3. Se lo inyectamos a tu tabla visual
        tabla.setModel(modeloLleno);
    }
}
    
