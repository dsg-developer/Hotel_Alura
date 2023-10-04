package com.ds.hotel_alura;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    
    public Connection getConnection(){
        String dbCasa = "hotel_alura_reservation";
        String dbOficina = "hotel_alura";
        String user = "root";
        String pass = "MySQLS3rv3r";
        try {
            cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+dbCasa,  
                    user, pass
            );
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }
    
    private Connection cn = null;
}
