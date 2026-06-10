
package VISTA;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PanelControlInventarios extends JPanel {

    private Color primaryColor = new Color(28, 43, 90);
    private Color selectionColor = new Color(200, 220, 240);

    public PanelControlInventarios() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // --- Barra de Herramientas Superior ---
        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setBackground(Color.WHITE);
        toolbar.setBorder(new EmptyBorder(10, 15, 10, 15));

        JPanel toolButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        toolButtons.setOpaque(false);
        
        String[] tools = {"Nuevo Producto", "Ajuste de Stock", "Bitácora de Movimientos", "Exportar Reporte"};
        String[] toolIcons = {"📄", "⚖️", "📋", "📤"};
        for (int i = 0; i < tools.length; i++) {
            JLabel btn = new JLabel();
            btn.setText("<html><center>"+toolIcons[i]+"<br>"+tools[i]+"</center></html>");
            btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
            btn.setHorizontalAlignment(SwingConstants.CENTER);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            toolButtons.add(btn);
        }
        toolbar.add(toolButtons, BorderLayout.WEST);

        // --- Barra de Búsqueda ---
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);
        JTextField searchField = new JTextField(15);
        searchField.setText("Buscar");
        searchField.setForeground(Color.GRAY);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        JLabel searchIcon = new JLabel("🔍");
        searchPanel.add(searchField);
        searchPanel.add(searchIcon);
        toolbar.add(searchPanel, BorderLayout.EAST);

        add(toolbar, BorderLayout.NORTH);

        // --- Tabla de Datos ---
        String[] columns = {"CÓDIGO", "PRODUCTO", "PRESENTACIÓN", "STOCK ACTUAL", "LÍMITE MÍNIMO", "PROVEEDOR", "ESTADO"};
        Object[][] data = {
            {"PROD001", "Detergente Líquido XYZ", "A granel", "120 L", "100 L", "A", "ACTIVO"},
            {"PROD002", "Detergente Garrafa (5L)", "A granel", "15", "20", "B", "CRÍTICO"},
            {"PROD003", "Cloro XY (A granel)", "A granel", "10 L", "15 L", "A", "STOCK BAJO"},
            {"PROD004", "Detergente Líquido XYZ", "A granel", "50 L", "50 L", "B", "STOCK BAJO"},
            {"PROD006", "Detergente Garrafa (5L)", "A granel", "15 L", "20 L", "B", "CRÍTICO"},
            {"PROD007", "Detergente Garrafa (5L)", "A granel", "10 L", "25 L", "B", "STOCK BAJO"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.setShowVerticalLines(false);
        table.setSelectionBackground(selectionColor);
        table.setSelectionForeground(Color.BLACK);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setFillsViewportHeight(true);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(primaryColor);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 12));
        tableHeader.setPreferredSize(new Dimension(0, 45));
        ((DefaultTableCellRenderer)tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);

        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) value;
                JLabel label = (JLabel) c;
                label.setText(" " + status);
                
                if ("ACTIVO".equals(status)) {
                    label.setIcon(new CircleIcon(new Color(76, 175, 80)));
                } else if ("CRÍTICO".equals(status)) {
                    label.setIcon(new CircleIcon(new Color(244, 67, 54)));
                } else if ("STOCK BAJO".equals(status)) {
                    label.setIcon(new CircleIcon(new Color(255, 152, 0)));
                }
                return c;
            }
        });

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // --- Paginación Inferior ---
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paginationPanel.setBackground(new Color(235, 235, 225));
        paginationPanel.setBorder(new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JButton btnFirst = new JButton("|<");
        JButton btnPrev = new JButton("<");
        JTextField txtPage = new JTextField("1", 2);
        txtPage.setHorizontalAlignment(JTextField.CENTER);
        JLabel lblOf = new JLabel("of 12");
        JButton btnNext = new JButton(">");
        JButton btnLast = new JButton(">|");

        Component[] pagComponents = {btnFirst, btnPrev, txtPage, lblOf, btnNext, btnLast};
        for (Component comp : pagComponents) {
            if (comp instanceof JButton) {
                ((JButton)comp).setMargin(new Insets(2, 5, 2, 5));
                ((JButton)comp).setBackground(Color.WHITE);
                ((JButton)comp).setFocusPainted(false);
            }
            paginationPanel.add(comp);
        }

        add(paginationPanel, BorderLayout.SOUTH);
    }

    // Helper para los puntitos de colores
    static class CircleIcon implements Icon {
        private Color color;
        public CircleIcon(Color color) { this.color = color; }
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillOval(x, y + 2, 10, 10);
            g2.dispose();
        }
        @Override public int getIconWidth() { return 12; }
        @Override public int getIconHeight() { return 14; }
    }
}
