package com.ds.hotel_alura.controllers;

import com.ds.hotel_alura.models.huesped.HuespedDTO;
import com.ds.hotel_alura.models.CallBack;
import com.ds.hotel_alura.models.huesped.Huespedes;
import com.ds.hotel_alura.models.huesped.HuespedDAO;
import com.ds.hotel_alura.models.reservas.ReservaDTO;
import com.ds.hotel_alura.views.Exito;
import com.ds.hotel_alura.views.RegistroHuesped;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HuespedController implements CallBack<HuespedDTO>{

    public HuespedController() {
        this.dao = new HuespedDAO();
    }
    
    public void open(ReservaDTO dto){
        RegistroHuesped registro = new RegistroHuesped();
        
        registro.setReservasDTO(dto);
        registro.setCallBack(this);
        registro.setVisible(true);
    }
    
    public void fillComponente(Component object){
        if(object instanceof JTable){
            fillTable((JTable)object);
        }
        
    }
    
    private void fillTable(JTable tabla){
        jtable = tabla;
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        getColumn(dtm, tabla);
        List<Huespedes> huespedes = dao.findAll(new Huespedes());
        List<HuespedDTO> dtos = new ArrayList<>();
        
        huespedes.forEach(e->{
            dtos.add(new HuespedDTO(e));
        });
        
        dtos.forEach(e->{
            dtm.addRow(new Object[]{
                e.id(), e.nombre(), e.apellido(), e.fechaNacimiento(),
                e.nacionalidad(), e.telefono(), "00"+e.id_reserva()
            });
        });
        tabla.setModel(dtm);
    }
    
    public void fillTableFind(List<Huespedes> huespedes){
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        getColumn(dtm, jtable);
        if(huespedes == null) huespedes = dao.findAll(new Huespedes());
        List<HuespedDTO> dtos = new ArrayList<>();
        
        huespedes.forEach(e->{
            dtos.add(new HuespedDTO(e));
        });
        
        dtos.forEach(e->{
            dtm.addRow(new Object[]{
                e.id(), e.nombre(), e.apellido(), e.fechaNacimiento(),
                e.nacionalidad(), e.telefono(), "00"+e.id_reserva()
            });
        });
        jtable.setModel(dtm);
    }
    
    private void getColumn(DefaultTableModel dtm, JTable tabla){
        for (int x = 0; x < tabla.getColumnCount(); x++) 
            dtm.addColumn(tabla.getColumnName(x));
    }

    public void actualizar(HuespedDTO dto){
        Huespedes entidad = new Huespedes(dto);
        dao.update(entidad);
    }
    
    @Override
    public void listener(HuespedDTO dto, byte query) {
        switch(query){
            case 0 ->{
                Huespedes entidad = new Huespedes(dto);
                dao.save(entidad);
                try {
                    Exito dialog = new Exito("Datos guardados satisfactoriamente");
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }case 1 ->{
                Huespedes entidad = new Huespedes(dto);
                dao.update(entidad);
                try {
                    Exito dialog = new Exito("Datos actualizados satisfactoriamente");
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }case 2->{
                Huespedes entidad = new Huespedes(dto);
                String querry = "select * from "+entidad.getClass().getSimpleName()+
                        " where id_reserva = '"+entidad.getId_reserva()+"'";
                List<Huespedes> hu = dao.CustomeFindQuery(entidad, querry);
                fillTableFind(hu);
            }case 3 ->{
                int respuesta = JOptionPane.showConfirmDialog(null, """
                                                                    \u00bfEstas seguro que deseas elimiar este registro?
                                                                    Nota: Si continua con esta acci\u00f3n el registro se eliminara permanentemente.""", "Información", 
                        JOptionPane.INFORMATION_MESSAGE);
                if(respuesta == 0){
                    Huespedes entidad = new Huespedes(dto);
                    dao.delete(entidad);
                    fillTableFind(null);
                }
            }
        }
    }
    
    private HuespedDAO dao;
    private JTable jtable;
}
