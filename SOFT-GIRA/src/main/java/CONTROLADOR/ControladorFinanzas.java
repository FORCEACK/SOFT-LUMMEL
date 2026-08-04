/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasFinanzas;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorFinanzas {
    
    // Instanciamos el modelo para usarlo en todos los métodos
    ConsultasFinanzas modeloFinanzas = new ConsultasFinanzas();

    public void buscarVentasPorDia(JTable tabla, String fechaSQL) {
        DefaultTableModel modeloLleno = modeloFinanzas.ventasPorDia(fechaSQL);
        tabla.setModel(modeloLleno);
    }
    
    public void buscarVentasPorRango(JTable tabla, String fechaInicio, String fechaFin) {
        DefaultTableModel modeloLleno = modeloFinanzas.ventasPorRango(fechaInicio, fechaFin);
        tabla.setModel(modeloLleno);
    }
    
    public void buscarVentasPorMes(JTable tabla, int mes, int anio) {
        DefaultTableModel modeloLleno = modeloFinanzas.ventasPorMes(mes, anio);
        tabla.setModel(modeloLleno);
    }
}