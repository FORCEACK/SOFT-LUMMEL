/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ConsultasCompras;
import VISTA.COMPRAS;
import javax.swing.table.DefaultTableModel;

public class ControladorCompras {
    
    private COMPRAS vistaCompras;
    private ConsultasCompras modeloCompras;
    
    public ControladorCompras(COMPRAS vistaCompras, ConsultasCompras modeloCompras) {
        this.vistaCompras = vistaCompras;
        this.modeloCompras = modeloCompras;
    }
    
    // Método para iniciar la vista de compras
    public void iniciarVista() {
        // Obtenemos el modelo de la tabla 
        DefaultTableModel modeloTabla = (DefaultTableModel) vistaCompras.tablaDetalleCompra.getModel();
        
        // Le pedimos al modelo (BD) que llene esa tabla
        modeloCompras.mostrarCompras(modeloTabla);
    }
}
