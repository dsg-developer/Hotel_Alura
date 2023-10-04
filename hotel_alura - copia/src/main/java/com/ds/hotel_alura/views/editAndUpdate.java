package com.ds.hotel_alura.views;

import com.ds.hotel_alura.Hotel_alura;
import com.ds.hotel_alura.models.reservas.ReservaDTO;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class editAndUpdate extends JFrame{

    public editAndUpdate(ReservaDTO dto) {
        this.dto = dto;
        initComponent();
    }
    
    @SuppressWarnings("Unchecked")
    void initComponent(){
        panel = new JPanel();
        panel.setLayout(null);
        createFields();
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(126, espacioH+38, 98, 36);
        btnEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnEditar);
        
        add(panel, BorderLayout.CENTER);
        this.setSize(258, 352);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Edición");
        this.setIconImage(
            Toolkit.getDefaultToolkit().getImage(
                editAndUpdate.class.getResource("/imagenes/lupa2.png")
            )
        );
    }
    
    private void createFields(){
        Field[] camp = dto.getClass().getDeclaredFields();
        RecordComponent[] components = dto.getClass().getRecordComponents();
        String[] celdas = Arrays.stream(components)
            .map(component -> {
                try {
                    return component.getAccessor().invoke(dto).toString();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .toArray(String[]::new);
        for(int x = 0; x < camp.length; x++){
            JTextField campo = new JTextField(celdas[x]);
            campo.setBounds(20, espacioH, 200, 38);
            campo.setFont(new Font("Roboto", Font.PLAIN, 18));
            espacioH += 38;
            if(x == 0 || x == 6)
                campo.setEditable(false);
            
            panel.add(campo);
        }
    }
          
    private ReservaDTO dto;
    private JPanel panel;
    private short espacioH = 20;
}
