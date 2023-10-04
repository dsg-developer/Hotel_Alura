package com.ds.hotel_alura.controllers;

import com.ds.hotel_alura.models.CallBack;
import com.ds.hotel_alura.models.reservas.ReservaDAO;
import com.ds.hotel_alura.models.reservas.ReservaDTO;
import com.ds.hotel_alura.models.reservas.Reservas;
import com.ds.hotel_alura.views.ReservasView;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReservasController implements CallBack<ReservaDTO>{

    public ReservasController() {
        this.dao = new ReservaDAO();
    }

    public void open(){
        final ReservasView reserva = new ReservasView();
        reserva.setCallBack(this);
        reserva.setVisible(true);
    }
    
    public void fillComponente(Component object){
        if(object instanceof JTable) {
            fillTable((JTable)object);
        }
    }
    
    public void eliminar(long id){
        int value = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea elimiar el registro seleccionado?","info",JOptionPane.YES_NO_CANCEL_OPTION);
        if(value == 0) {
            dao.delete(id);
        }
    }
    
    
    private void fillTable(JTable object){
        DefaultTableModel dtm = (DefaultTableModel) object.getModel();
        List<Reservas> reservas = dao.findAll(new Reservas());
        List<ReservaDTO> dtos = new ArrayList<>();
        reservas.forEach(e->{
            dtos.add(new ReservaDTO(e));
        });
        
        dtos.forEach(e->{
            dtm.addRow(new Object[]{
                e.id(), e.fechaEntrada(), e.fechaSalida(),
                e.precio(), e.metodoDePago()
            });
        });
    }
    
    @Override
    public void listener(ReservaDTO dto) {
        Reservas entidad = new Reservas(dto);
        entidad.setId(dao.save(entidad));
        ReservaDTO newDto = new ReservaDTO(entidad);
        new HuespedController().open(newDto);
    }
    
    private ReservaDAO dao;
}
