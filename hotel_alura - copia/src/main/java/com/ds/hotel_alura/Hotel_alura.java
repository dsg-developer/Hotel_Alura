/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ds.hotel_alura;

//import java.util.stream.Collectors;

import com.ds.hotel_alura.views.MenuPrincipal;
import java.awt.EventQueue;

//import org.flywaydb.core.Flyway;
//import org.flywaydb.core.api.Location;

/**
 *
 * @author Dionisio de los Santos Guzmán
 */
public class Hotel_alura {

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Hotel_alura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hotel_alura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hotel_alura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hotel_alura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MenuPrincipal();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
//    @Override
//    public void run(String... args) throws Exception {
//        String locations = Arrays.stream(flyway.getConfiguration().getLocations())
//                .map(Location::toString)
//                .collect(Collectors.joining(", "));
//        System.err.println("Flyway migration locations: " + locations);
//    }
//    
//    private Flyway flyway;
}
