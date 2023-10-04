package com.ds.hotel_alura.controllers;

import com.ds.hotel_alura.models.CallBack;
import com.ds.hotel_alura.models.reservas.ReservaDTO;
import com.ds.hotel_alura.views.Login;

public class LoginController implements CallBack<ReservaDTO>{
    public Login start(){
        return login = new Login();
    }
    
    private Login login;

    @Override
    public void listener(ReservaDTO DTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
