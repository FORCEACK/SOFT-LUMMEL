package VISTA;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import MODELO.ConsultasCompras;
import CONTROLADOR.ControladorCompras;
public class PRINCIPAL extends javax.swing.JFrame {
<<<<<<< HEAD

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

=======
    /**
     * Creates new form PRINCIPAL
     */
    public PRINCIPAL() {
       initComponents();
        configurarAnimaciones(); 
        
        // Esta línea hace que la ventana inicie maximizada automáticamente
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); 

        // --- NUEVO CÓDIGO PARA AUTO-AJUSTAR LA IMAGEN ---
        
        // 1. Aseguramos que el panel blanco use todo el espacio para la imagen
        panelVistas.setLayout(new java.awt.BorderLayout());
        panelVistas.add(jLabel15, java.awt.BorderLayout.CENTER);
        
        // 2. Cargamos la imagen original (Ojo: revisa que la ruta coincida con la tuya)
        java.awt.Image logoOriginal = new javax.swing.ImageIcon(getClass().getResource("/iconos/LOGOTIPO EMPRESA.png")).getImage();
        
        // 3. Le agregamos un "escuchador" a la etiqueta para detectar cada vez que la pantalla cambia de tamaño
        jLabel15.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Obtenemos el nuevo ancho y alto del espacio blanco
                int ancho = jLabel15.getWidth();
                int alto = jLabel15.getHeight();
                
                // Validamos que haya espacio para evitar errores
                if (ancho > 0 && alto > 0) {
                    // Escala la imagen al tamaño exacto de la pantalla (SCALE_SMOOTH mantiene buena calidad)
                    java.awt.Image logoEscalado = logoOriginal.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
                    jLabel15.setIcon(new javax.swing.ImageIcon(logoEscalado));
                }
            }
        });
    }
    
    private void configurarAnimaciones() {
        Color colorHover = new Color(114, 136, 174); 

        // Aplicamos el hover a los paneles que son "opciones" del menú
        configurarHover(jPanel1, colorHover); // Cerrar Sesion
        configurarHover(jPanel2, colorHover); // Punto de Venta
        configurarHover(jPanel4, colorHover); // Control de Inventarios
        configurarHover(jPanel5, colorHover); // Finanzas
        configurarHover(jPanel6, colorHover); // Compras
        configurarHover(jPanel7, colorHover); // Usuarios
        configurarHover(jPanel8, colorHover); // Soporte
        
        // --- EVENTOS DE CLIC PARA CAMBIAR DE VENTANA ---
       
        
 jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
           @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            // Si el panel está habilitado, entonces sí mostramos la pantalla
            if (jPanel7.isEnabled()) {
                mostrarPanel(new PanelUsuarios()); 
            }
        }
        });
        // Evento para el botón de CERRAR SESIÓN (jPanel1)
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // 1. Preguntar al usuario si realmente quiere salir
                int confirmacion = javax.swing.JOptionPane.showConfirmDialog(null, 
                        "¿Estás seguro que deseas cerrar sesión?", 
                        "Confirmar Cierre de Sesión", 
                        javax.swing.JOptionPane.YES_NO_OPTION,
                        javax.swing.JOptionPane.QUESTION_MESSAGE);
                
                // 2. Si dice que SÍ (YES_OPTION)
                if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                    
                    // --- LO QUE AGREGÓ TU COMPAÑERO: Actualizar el estatus a 0 en la BD y limpiar sesión ---
                    if (!MODELO.SesionActual.usuarioLogueado.isEmpty()) {
                        MODELO.ConsultasUsuario modeloUsuario = new MODELO.ConsultasUsuario();
                        modeloUsuario.actualizarEstadoSesion(MODELO.SesionActual.usuarioLogueado, 0);
                        
                        // Limpiamos la variable global para que quede vacía
                        MODELO.SesionActual.usuarioLogueado = "";
                    }
                    
                    // Cierra esta ventana (PRINCIPAL)
                    dispose(); 
                    
                    // Abre tu ventana de Login 
                    VISTA.USUARIO ventanaLogin = new VISTA.USUARIO();
                    ventanaLogin.setVisible(true);
                    ventanaLogin.setLocationRelativeTo(null); // La centra en la pantalla
                }
            }
        });
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
           @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            // Mandamos a llamar al nuevo JPanel usando el método de incrustación
            
        }
        });
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
           @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            // Mandamos a llamar al nuevo JPanel usando el método de incrustación
            mostrarPanel(new FINANZAS()); 
        }
        });
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
           @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            // Mandamos a llamar al nuevo JPanel usando el método de incrustación
            mostrarPanel(new COMPRAS()); 
            // Dentro del evento de clic de tu botón COMPRAS en PRINCIPAL.java
        COMPRAS vista = new COMPRAS();
        ConsultasCompras modelo = new ConsultasCompras(); 
        ControladorCompras controlador = new ControladorCompras(vista, modelo);

        controlador.iniciarVista();
        // Y aquí ya muestras tu panel en la interfaz...
        }
        });
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
           @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            // Mandamos a llamar al nuevo JPanel usando el método de incrustación
           
        }
        });
        
        // Evento para abrir USUARIOS
>>>>>>> companero/main
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
<<<<<<< HEAD
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

=======
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

>>>>>>> companero/main
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

<<<<<<< HEAD
        jPanelMenuLateral.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 250, 50));
=======
        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 270, 50));
>>>>>>> companero/main

        jPanel4.setBackground(new java.awt.Color(206, 208, 225));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("CONTROL DE INVENTARIOS");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ControlDeInventarios.png"))); // NOI18N
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

<<<<<<< HEAD
        jPanelMenuLateral.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 250, 50));
=======
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
>>>>>>> companero/main

        jPanel8.setBackground(new java.awt.Color(206, 208, 225));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("SOPORTE");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Soporte.png"))); // NOI18N
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

<<<<<<< HEAD
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
=======
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
>>>>>>> companero/main

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
<<<<<<< HEAD
=======
    // Método para mostrar paneles dinámicos en la vista principal a pantalla completa
    // Método para mostrar paneles dinámicos en la vista principal a pantalla completa
    private void mostrarPanel(JPanel p) {
        // 1. Limpiamos el contenedor
        panelVistas.removeAll();
        
        // 2. Le asignamos un BorderLayout para que fuerce al panel a ocupar el 100% del espacio
        panelVistas.setLayout(new java.awt.BorderLayout());
        
        // 3. ENVOLVEMOS EL PANEL EN UN SCROLL PANE (Esto soluciona el corte inferior)
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(p);
        scroll.setBorder(null); // Quitamos los bordes para que luzca limpio
        
        // 4. Agregamos el Scroll en el CENTRO en lugar del panel directamente
        panelVistas.add(scroll, java.awt.BorderLayout.CENTER);
        
        // 5. Refrescamos para mostrar los cambios
        panelVistas.revalidate();
        panelVistas.repaint();
    }
    

    // Método para regresar a la vista del logo principal
    public void restaurarVistaLogo() {
        // 1. Limpiamos el panel de vistas
        panelVistas.removeAll();
        
        // 2. Mantenemos el BorderLayout
        panelVistas.setLayout(new java.awt.BorderLayout());
        
        // 3. Centramos el logo para que luzca bien en el espacio
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        // 4. Volvemos a agregar el JLabel que contiene tu imagen de Grupo Gira
        panelVistas.add(jLabel15, java.awt.BorderLayout.CENTER);
        
        // 5. Refrescamos la pantalla
        panelVistas.revalidate();
        panelVistas.repaint();
    }
>>>>>>> companero/main

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
<<<<<<< HEAD
    private javax.swing.JPanel Mostrador;
=======
>>>>>>> companero/main
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
<<<<<<< HEAD
    private javax.swing.JPanel jPanelMenuLateral;
    // End of variables declaration//GEN-END:variables

    void bloquearMenuLateral(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
=======
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelVistas;
    // End of variables declaration//GEN-END:variables
}
>>>>>>> companero/main
