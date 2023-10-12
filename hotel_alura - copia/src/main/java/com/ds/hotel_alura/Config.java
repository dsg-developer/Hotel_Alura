package com.ds.hotel_alura;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Config {
    
    public Connection getConnection(){
        String dbCasa = "hotel_alura_reservation";
        String dbOficina = "hotel_alura";
        String user = "root";
        String pass = "MySQLS3rv3r";
        try {
            cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+dbOficina,  
                    user, ""
            );
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo realizar la conexión con la db"
                    + "\nfavor de revisar el servidor con el que se intenta conectar", "Información de error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return cn;
    }
    
    private Connection cn = null;
}
