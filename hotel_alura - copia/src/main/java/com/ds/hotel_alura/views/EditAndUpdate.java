package com.ds.hotel_alura.views;

import com.ds.hotel_alura.controllers.HuespedController;
import com.ds.hotel_alura.controllers.ReservasController;
import com.ds.hotel_alura.models.CallBack;
import com.ds.hotel_alura.models.huesped.HuespedDTO;
import com.ds.hotel_alura.models.reservas.ReservaDTO;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditAndUpdate extends JFrame{

    public EditAndUpdate(ReservaDTO dtoR, HuespedDTO dtoH, byte pestagna,
    HuespedController hc, ReservasController rc) {
        this.dtoR = dtoR;
        this.dtoH = dtoH;
        this.pestagna = pestagna;
        this.hc = hc;
        this.rc = rc;
        initComponent();
    }
    
    @SuppressWarnings("Unchecked")
    void initComponent(){
        panel = new JPanel();
        panel.setLayout(null);
        
        campos = new ArrayList<>();
        
        createFields();
        
        ReservasController rc = new ReservasController();
        
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(121, espacioH+12, 98, 36);
        btnEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener((e)->{
            if(pestagna == 0) actualizarReserva();
            else actualizarHuesped(); 
        });
        panel.add(btnEditar);
        
        add(panel, BorderLayout.CENTER);
        this.setSize(258, 386);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Edición");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(
                EditAndUpdate.class.getResource("/imagenes/lupa2.png"))
        );
    }
    
    private void actualizarHuesped(){
        int size = campos.get(6).getText().length();
        long idReserva = Long.parseLong(campos.get(6).getText().
                substring(2, size));
        HuespedDTO h = new HuespedDTO(
                Long.parseLong(campos.get(0).getText()),
                campos.get(1).getText(),
                campos.get(2).getText(),
                campos.get(3).getText(),
                campos.get(4).getText(),
                campos.get(5).getText(),
                idReserva
        );
        CallBack<HuespedDTO> call = hc;
        call.listener(h, (byte)1);
        hc.fillTableFind(null);
        dispose();
    }
    
    private void actualizarReserva(){
        ReservaDTO r = new ReservaDTO(
                Long.parseLong(campos.get(0).getText()),
                campos.get(1).getText(),
                campos.get(2).getText(),
                Integer.parseInt(campos.get(3).getText()),
                campos.get(4).getText()
        );
        CallBack<ReservaDTO> call = rc;
        call.listener(r, (byte)1);
        rc.fillTable();
        dispose();
    }
    
    private void createFields (){
        Field[] camp;
        String[] celdas;
        if(dtoH == null){
            camp = dtoR.getClass().getDeclaredFields();
            RecordComponent[] components = dtoR.getClass().getRecordComponents();
            celdas = Arrays.stream(components)
                .map(component -> {
                    try {
                        return component.getAccessor().invoke(dtoR).toString();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(String[]::new);
        }else{
            camp = dtoH.getClass().getDeclaredFields();
            RecordComponent[] components = dtoH.getClass().getRecordComponents();
            celdas = Arrays.stream(components)
                .map(component -> {
                    try {
                        return component.getAccessor().invoke(dtoH).toString();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(String[]::new);
        }
        
        for(int x = 0; x < camp.length; x++){
            JTextField campo;
            if (x == 6) campo  = new JTextField("00"+celdas[x]);
            else campo  = new JTextField(celdas[x]);
            campo.setBounds(20, espacioH, 200, 38);
            campo.setFont(new Font("Roboto", Font.PLAIN, 18));
            espacioH += 38;
            if(x == 0 || x == 6)
                campo.setEditable(false);
            
            campos.add(campo);
            panel.add(campo);
        }
    }
          
    private ReservaDTO dtoR;
    private HuespedDTO dtoH;
    private HuespedController hc;
    private ReservasController rc;
    private JPanel panel;
    private List<JTextField> campos;
    private short espacioH = 20;
    private byte pestagna = -1;
}
