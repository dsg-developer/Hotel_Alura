package com.ds.hotel_alura.views;

import com.ds.hotel_alura.controllers.HuespedController;
import com.ds.hotel_alura.controllers.ReservasController;
import com.ds.hotel_alura.models.CallBack;
import com.ds.hotel_alura.models.huesped.HuespedDTO;
import com.ds.hotel_alura.models.reservas.ReservaDTO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    private ReservasController rc;
    private HuespedController hc;
    private JLabel placeHolder;
    private byte pestagna;
    int xMouse, yMouse;

    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        
        pestagna = -1;
        
        placeHolder = new JLabel("No. de reserva");
        placeHolder.setForeground(new Color(132, 132, 132));
        placeHolder.setFont(new Font("Roboto", Font.PLAIN, 12));
        placeHolder.setBounds(538, 128, 196, 32);
        placeHolder.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        placeHolder.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                placeHolderMouseClicked(me);
            }
        });
        contentPane.add(placeHolder);
        
        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtBuscar.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent ke){
                placeHolder.setVisible(false);
            }
            
            @Override
            public void keyReleased(KeyEvent ke){
                if(txtBuscar.getText().isEmpty())
                    placeHolder.setVisible(true);
            }
        });
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(326, 62, 310, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        rc = new ReservasController();
        hc = new HuespedController();

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        tbReservas.getTableHeader().setReorderingAllowed(false);
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        tbHuespedes.getTableHeader().setReorderingAllowed(false);
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        
        tabbedListener(panel);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!txtBuscar.getText().isEmpty()){
                    try {
                        String id = txtBuscar.getText();
                        if(pestagna == 1 && id.substring(0, 1).
                                equals("0")){
                            id = id.substring(2, id.length());
                        }
                        long reserva = Long.parseLong(id);
                        if(pestagna == 0){
                            CallBack<ReservaDTO> call = rc;
                            call.listener(new ReservaDTO(
                                    reserva,null, 
                                    null,0,null
                            ), (byte)2);
                        }else{
                            CallBack<HuespedDTO> callB = hc;
                            callB.listener(new HuespedDTO(
                                    0,null, 
                                    null,null,
                                    null,null, reserva
                            ), (byte)2);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, 
                            "No es permitido insertar letras en este campo "
                            + "Caracter no valido", "Información", 
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, 
                        "Se necesita que introduzca un No. de reserva para poder "
                        + "iniciar la busqueda", "Información", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int rowR = tbReservas.getSelectedRow();
                int rowH = tbHuespedes.getSelectedRow();
                if (rowR > -1 && pestagna == 0) {
                    ReservaDTO dto = new ReservaDTO(
                            ((Number) tbReservas.getValueAt(rowR, 0)).longValue(),
                            tbReservas.getValueAt(rowR, 1).toString(),
                            tbReservas.getValueAt(rowR, 2).toString(),
                            ((Number) tbReservas.getValueAt(rowR, 3)).intValue(),
                            tbReservas.getValueAt(rowR, 4).toString()
                    );
                    new EditAndUpdate(dto, null, pestagna, null, rc).setVisible(true);
                } else if (rowH > -1 && pestagna == 1) {
                    HuespedDTO dtoH = new HuespedDTO(
                            ((Number) tbHuespedes.getValueAt(rowH, 0)).longValue(),
                            tbHuespedes.getValueAt(rowH, 1).toString(),
                            tbHuespedes.getValueAt(rowH, 2).toString(),
                            tbHuespedes.getValueAt(rowH, 3).toString(),
                            tbHuespedes.getValueAt(rowH, 4).toString(),
                            tbHuespedes.getValueAt(rowH, 5).toString(),
                            Long.parseLong(tbHuespedes.getValueAt(rowH, 6).toString())
                    );
                    new EditAndUpdate(null, dtoH, pestagna, hc, null).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar uno de los "
                            + "registros para poder editar", "Información",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

            }
        });
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                int rowR = tbReservas.getSelectedRow();
                int rowH = tbHuespedes.getSelectedRow();
                if (rowR > -1 && pestagna == 0) {
                    ReservaDTO dto = new ReservaDTO(
                            ((Number) tbReservas.getValueAt(rowR, 0)).longValue(),
                            tbReservas.getValueAt(rowR, 1).toString(),
                            tbReservas.getValueAt(rowR, 2).toString(),
                            ((Number) tbReservas.getValueAt(rowR, 3)).intValue(),
                            tbReservas.getValueAt(rowR, 4).toString()
                    );
                    CallBack<ReservaDTO> c = rc;
                    c.listener(dto, (byte)3);
                } else if (rowH > -1 && pestagna == 1) {
                    HuespedDTO dtoH = new HuespedDTO(
                            ((Number) tbHuespedes.getValueAt(rowH, 0)).longValue(),
                            tbHuespedes.getValueAt(rowH, 1).toString(),
                            tbHuespedes.getValueAt(rowH, 2).toString(),
                            tbHuespedes.getValueAt(rowH, 3).toString(),
                            tbHuespedes.getValueAt(rowH, 4).toString(),
                            tbHuespedes.getValueAt(rowH, 5).toString(),
                            Long.parseLong(tbHuespedes.getValueAt(rowH, 6).toString())
                    );
                    CallBack<HuespedDTO> c = hc;
                    c.listener(dtoH, (byte)3);
                }else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar uno de los "
                            + "registros para poder eliminar", "Información",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
    }

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void placeHolderMouseClicked(MouseEvent me){
        placeHolder.setVisible(false);
        txtBuscar.grabFocus();
    }
    
    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
    
    private void tabbedListener(JTabbedPane panel){
        int i = panel.getSelectedIndex();
        if (i == 0) {
            pestagna = 0;
            rc.fillComponente(tbReservas);
        }else{
            pestagna = 1;
            hc.fillComponente(tbHuespedes);
        }
        panel.addChangeListener((e)->{
            int in = panel.getSelectedIndex();
            if (in == 0) {
                pestagna = 0;
                rc.fillComponente(tbReservas);
            }else{
                pestagna = 1;
                hc.fillComponente(tbHuespedes);
            }
        });
    }
}
