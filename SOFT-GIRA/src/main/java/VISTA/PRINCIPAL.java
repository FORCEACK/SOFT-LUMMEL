package VISTA;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import MODELO.ConsultasCompras;
import CONTROLADOR.ControladorCompras;
public class PRINCIPAL extends javax.swing.JFrame {
public static boolean cajaAbierta = false;
    public static double fondoInicialGuardado = 0.0;
    public static double ventasEfectivoDia = 0.0;
    public static double ventasTarjetaDia = 0.0;

    public PRINCIPAL() {
        initComponents();
        configurarAnimaciones(); 
        
          // Ventana maximizada
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Configuración del panel donde se muestran los módulos
        panelVistas.setLayout(new java.awt.BorderLayout());

        // Cargar logo
        java.awt.Image logoOriginal = new javax.swing.ImageIcon(
                getClass().getResource("/iconos/LOGOTIPO EMPRESA.png")
        ).getImage();

        // Ajustar automáticamente el logo
        jLabel15.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {

                int ancho = jLabel15.getWidth();
                int alto = jLabel15.getHeight();

                if (ancho > 0 && alto > 0) {

                    java.awt.Image logoEscalado =
                            logoOriginal.getScaledInstance(
                                    ancho,
                                    alto,
                                    java.awt.Image.SCALE_SMOOTH
                            );

                    jLabel15.setIcon(
                            new javax.swing.ImageIcon(logoEscalado)
                    );
                }
            }
        });
    }

    private void configurarAnimaciones() {
        Color colorHover = new Color(114, 136, 174); 

        // Aplicamos el hover a los paneles del menú lateral
        configurarHover(jPanel1, colorHover); // Cerrar Sesión
        configurarHover(jPanel2, colorHover); // Punto de Venta
        configurarHover(jPanel4, colorHover); // Control de Inventarios
        configurarHover(jPanel5, colorHover); // Finanzas
        configurarHover(jPanel6, colorHover); // Compras
        configurarHover(jPanel7, colorHover); // Usuarios
        configurarHover(jPanel8, colorHover); // Soporte
        
        // --- EVENTOS DE CLIC PARA CADA MÓDULO ---

        // 1. PUNTO DE VENTA / CORTE DE CAJA
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!cajaAbierta) {
                    mostrarPanel(new Punto_Venta());
                    Corte_Caja ventanaCorte = new Corte_Caja(PRINCIPAL.this, true);
                    ventanaCorte.setLocationRelativeTo(PRINCIPAL.this); 
                    ventanaCorte.setVisible(true);
                } else {
                    mostrarPanel(new Punto_Venta());
                }
            }
        });

        // 2. CONTROL DE INVENTARIOS
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarPanel(new PanelControlInventarios());
            }
        });

        // 3. SOPORTE
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               mostrarPanel(new Soporte());
            }
        });

        // 4. COMPRAS
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarPanel(new COMPRAS()); 
                COMPRAS vista = new COMPRAS();
                ConsultasCompras modelo = new ConsultasCompras(); 
                ControladorCompras controlador = new ControladorCompras(vista, modelo);
                controlador.iniciarVista();
            }
        });

        // 5. FINANZAS
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarPanel(new FINANZAS()); 
            }
        });

        // 6. USUARIOS
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (jPanel7.isEnabled()) {
                    mostrarPanel(new PanelUsuarios()); 
                }
            }
        });

        // 7. CERRAR SESIÓN
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int confirmacion = javax.swing.JOptionPane.showConfirmDialog(null, 
                        "¿Estás seguro que deseas cerrar sesión?", 
                        "Confirmar Cierre de Sesión", 
                        javax.swing.JOptionPane.YES_NO_OPTION,
                        javax.swing.JOptionPane.QUESTION_MESSAGE);
                
                if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                    if (!MODELO.SesionActual.usuarioLogueado.isEmpty()) {
                        MODELO.ConsultasUsuario modeloUsuario = new MODELO.ConsultasUsuario();
                        modeloUsuario.actualizarEstadoSesion(MODELO.SesionActual.usuarioLogueado, 0);
                        MODELO.SesionActual.usuarioLogueado = "";
                    }
                    dispose(); 
                    VISTA.USUARIO ventanaLogin = new VISTA.USUARIO();
                    ventanaLogin.setVisible(true);
                    ventanaLogin.setLocationRelativeTo(null);
                }
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

      panelContenido = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panelVistas = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelContenido.setBackground(new java.awt.Color(255, 255, 255));
        panelContenido.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(31, 34, 111));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(206, 208, 225));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("PUNTO DE VENTA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/PuntoDeVenta.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 270, 50));

        jPanel4.setBackground(new java.awt.Color(206, 208, 225));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("CONTROL DE INVENTARIOS");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ControlDeInventarios.png"))); // NOI18N
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 270, 50));

        jPanel5.setBackground(new java.awt.Color(206, 208, 225));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("FINANZAS");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/finanzas.png"))); // NOI18N
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 270, 50));

        jPanel6.setBackground(new java.awt.Color(206, 208, 225));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("COMPRAS");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Compras.png"))); // NOI18N
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 270, 50));

        jPanel7.setBackground(new java.awt.Color(206, 208, 225));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("USUARIOS");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Usuarios.png"))); // NOI18N
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 270, 50));

        jPanel8.setBackground(new java.awt.Color(206, 208, 225));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("SOPORTE");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Soporte.png"))); // NOI18N
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 270, 50));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/LOGOTIPO LUMMEL ICONO_1.png"))); // NOI18N
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/LOGOTIPO LUMMEL ICONO_1.png"))); // NOI18N
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, -1, -1));

        jPanel1.setBackground(new java.awt.Color(206, 208, 225));

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel16.setText("CERRAR SESION");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cerrar-sesion.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 270, 50));

        panelContenido.add(jPanel3, java.awt.BorderLayout.WEST);

        panelVistas.setBackground(new java.awt.Color(255, 255, 255));
        panelVistas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/LOGOTIPO EMPRESA.png"))); // NOI18N
        panelVistas.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 780, 459));

        panelContenido.add(panelVistas, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelContenido, java.awt.BorderLayout.CENTER);

        pack();

    }// </editor-fold>//GEN-END:initComponents

    
   public void mostrarPanel(JPanel p) {
        panelVistas.removeAll();
        panelVistas.setLayout(new java.awt.BorderLayout());
        
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(p);
        scroll.setBorder(null); 
        
        panelVistas.add(scroll, java.awt.BorderLayout.CENTER);
        panelVistas.revalidate();
        panelVistas.repaint();
    }

    public void restaurarVistaLogo() {
        panelVistas.removeAll();
        panelVistas.setLayout(new java.awt.BorderLayout());
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelVistas.add(jLabel15, java.awt.BorderLayout.CENTER);
        panelVistas.revalidate();
        panelVistas.repaint();
    }

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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelMenuLateral;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelVistas;
    // End of variables declaration//GEN-END:variables
    // End of variables declaration                   

    void bloquearMenuLateral(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



