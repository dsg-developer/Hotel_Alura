package com.ds.hotel_alura.controllers;

import com.ds.hotel_alura.models.huesped.HuespedDTO;
import com.ds.hotel_alura.models.CallBack;
import com.ds.hotel_alura.models.huesped.Huespedes;
import com.ds.hotel_alura.models.huesped.HuespedDAO;
import com.ds.hotel_alura.models.reservas.ReservaDTO;
import com.ds.hotel_alura.views.Exito;
import com.ds.hotel_alura.views.RegistroHuesped;
import javax.swing.JDialog;

public class HuespedController implements CallBack<HuespedDTO>{
    public void open(ReservaDTO dto){
        RegistroHuesped registro = new RegistroHuesped();
        
        registro.setReservasDTO(dto);
        registro.setCallBack(this);
        registro.setVisible(true);
    }

    @Override
    public void listener(HuespedDTO dto) {
        Huespedes entidad = new Huespedes(dto);
        HuespedDAO dao = new HuespedDAO();
        dao.save(entidad);
        try {
            Exito dialog = new Exito();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
