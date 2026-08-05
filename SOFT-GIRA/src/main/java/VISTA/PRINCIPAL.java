package VISTA;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PRINCIPAL extends javax.swing.JFrame {

    public static boolean cajaAbierta = false;
    public static double fondoInicialGuardado = 0.0;
    public static double ventasEfectivoDia = 0.0;
    public static double ventasTarjetaDia = 0.0;

    public PRINCIPAL() {
        initComponents();
        // PEGA EL CÓDIGO AQUÍ ABAJO:
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jPanelMenuLateral, java.awt.BorderLayout.WEST);
        jPanel3.add(Mostrador, java.awt.BorderLayout.CENTER);
    
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        configurarAnimaciones();
    }

    public void mostrarPanel(JPanel p) {
        Mostrador.removeAll();
        Mostrador.setLayout(new java.awt.BorderLayout());
        
        JPanel panelContenedor = new JPanel(new java.awt.BorderLayout());
        panelContenedor.setBackground(new java.awt.Color(249, 250, 255));
        panelContenedor.add(p, java.awt.BorderLayout.CENTER);
        
        Mostrador.add(panelContenedor, java.awt.BorderLayout.CENTER);
        Mostrador.revalidate();
        Mostrador.repaint();
    }

    private void configurarAnimaciones() {
        Color colorHover = new Color(114, 136, 174);

        configurarHover(jPanel2, colorHover); 
        configurarHover(jPanel4, colorHover); 
        configurarHover(jPanel8, colorHover); 
        configurarHover(jPanel6, colorHover); 
        configurarHover(jPanel5, colorHover); 
        configurarHover(jPanel7, colorHover); 
        
  // Botón PUNTO DE VENTA / CORTE DE CAJA (Valida si la caja está abierta)
jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (!cajaAbierta) {
            // 1. Primero cargamos el Punto de Venta en el fondo para que no se vea blanco
            mostrarPanel(new Punto_Venta());
            
            // 2. Después abrimos la ventana flotante del corte de caja encima
            Corte_Caja ventanaCorte = new Corte_Caja(PRINCIPAL.this, true);
            ventanaCorte.setLocationRelativeTo(PRINCIPAL.this); 
            ventanaCorte.setVisible(true);
        } else {
            // Si la caja ya está abierta, muestra el Punto de Venta normal
            mostrarPanel(new Punto_Venta());
        }
    }
});
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarPanel(new PanelControlInventarios());
            }
        });

        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               mostrarPanel(new Soporte());
            }
        });

        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               // mostrarPanel(new Compras()); 
            }
        });

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               // mostrarPanel(new Finanzas()); 
            }
        });

        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               // mostrarPanel(new Usuarios()); 
            }
        });
    }

    private void configurarHover(JPanel panel, Color colorHover) {
        Color original = panel.getBackground();

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(colorHover);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBackground(original);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanelMenuLateral = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Mostrador = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(31, 34, 111));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanelMenuLateral.setBackground(new java.awt.Color(31, 34, 111));
        jPanelMenuLateral.setPreferredSize(new java.awt.Dimension(270, 500));
        jPanelMenuLateral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(206, 208, 225));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("PUNTO DE VENTA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/PuntoDeVenta.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanelMenuLateral.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 250, 50));

        jPanel4.setBackground(new java.awt.Color(206, 208, 225));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("CONTROL DE INVENTARIOS");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ControlDeInventarios.png"))); // NOI18N
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanelMenuLateral.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 250, 50));

        jPanel8.setBackground(new java.awt.Color(206, 208, 225));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("SOPORTE");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Soporte.png"))); // NOI18N
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanelMenuLateral.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 250, 50));

        jPanel6.setBackground(new java.awt.Color(206, 208, 225));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("COMPRAS");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Compras.png"))); // NOI18N
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanelMenuLateral.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 250, 50));

        jPanel5.setBackground(new java.awt.Color(206, 208, 225));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("FINANZAS");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/finanzas.png"))); // NOI18N
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanelMenuLateral.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 250, 50));

        jPanel7.setBackground(new java.awt.Color(206, 208, 225));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("USUARIOS");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Usuarios.png"))); // NOI18N
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanelMenuLateral.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 250, 50));

        jPanel3.add(jPanelMenuLateral, java.awt.BorderLayout.LINE_START);

        Mostrador.setBackground(new java.awt.Color(249, 250, 255));
        Mostrador.setLayout(new java.awt.BorderLayout());
        jPanel3.add(Mostrador, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PRINCIPAL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Mostrador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelMenuLateral;
    // End of variables declaration//GEN-END:variables

    void bloquearMenuLateral(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}