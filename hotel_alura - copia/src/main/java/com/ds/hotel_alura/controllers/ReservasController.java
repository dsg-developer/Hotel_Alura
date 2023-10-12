package com.ds.hotel_alura.controllers;

import com.ds.hotel_alura.models.CallBack;
import com.ds.hotel_alura.models.reservas.ReservaDAO;
import com.ds.hotel_alura.models.reservas.ReservaDTO;
import com.ds.hotel_alura.models.reservas.Reservas;
import com.ds.hotel_alura.views.Exito;
import com.ds.hotel_alura.views.ReservasView;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReservasController implements CallBack<ReservaDTO> {

    public ReservasController() {
        this.dao = new ReservaDAO();
    }

    public void open() {
        final ReservasView reserva = new ReservasView();
        reserva.setCallBack(this);
        reserva.setVisible(true);
    }

    public void fillComponente(Component object) {
        if (object instanceof JTable) {
            fillTable((JTable) object);
        }

    }

    private void fillTable(JTable table) {
        jtable = table;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        getColumn(dtm, table);
        List<Reservas> reservas = dao.findAll(new Reservas());
        List<ReservaDTO> dtos = new ArrayList<>();
        reservas.forEach(e -> {
            dtos.add(new ReservaDTO(e));
        });

        dtos.forEach(e -> {
            dtm.addRow(new Object[]{
                e.id(), e.fechaEntrada(), e.fechaSalida(),
                e.precio(), e.metodoDePago()
            });
        });
        table.setModel(dtm);
    }

    public void fillTable() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        getColumn(dtm, jtable);
        List<Reservas> reservas = dao.findAll(new Reservas());
        List<ReservaDTO> dtos = new ArrayList<>();
        reservas.forEach(e -> {
            dtos.add(new ReservaDTO(e));
        });

        dtos.forEach(e -> {
            dtm.addRow(new Object[]{
                e.id(), e.fechaEntrada(), e.fechaSalida(),
                e.precio(), e.metodoDePago()
            });
        });
        jtable.setModel(dtm);
    }

    private void getColumn(DefaultTableModel dtm, JTable tabla) {
        for (int x = 0; x < tabla.getColumnCount(); x++) {
            dtm.addColumn(tabla.getColumnName(x));
        }
    }

    @Override
    public void listener(ReservaDTO dto, byte query) {
        switch (query) {
            case 0 -> {
                Reservas entidad = new Reservas(dto);
                entidad.setId(dao.save(entidad));
                ReservaDTO newDto = new ReservaDTO(entidad);
                new HuespedController().open(newDto);
            }
            case 1 -> {
                Reservas entidad = new Reservas(dto);
                dao.update(entidad);
                try {
                    Exito dialog = new Exito("Datos actualizados satisfactoriamente");
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                Reservas entidad = new Reservas(dto);
                List<Reservas> reservas = dao.findById(entidad);
                DefaultTableModel dtm = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                getColumn(dtm, jtable);
                List<ReservaDTO> Rdtos = new ArrayList<>();
                reservas.forEach(e -> {
                    Rdtos.add(new ReservaDTO(e));
                });

                Rdtos.forEach(e -> {
                    dtm.addRow(new Object[]{
                        e.id(), e.fechaEntrada(), e.fechaSalida(),
                        e.precio(), e.metodoDePago()
                    });
                });
                jtable.setModel(dtm);
            }case 3->{
                int respuesta = JOptionPane.showConfirmDialog(null, """
                                                                    \u00bfEstas seguro que deseas elimiar este registro?
                                                                    Nota: Si continua con esta acci\u00f3n el registro se eliminara permanentemente.""", "Información", 
                        JOptionPane.INFORMATION_MESSAGE);
                if(respuesta == 0){
                    Reservas entidad = new Reservas(dto);
                    dao.delete(entidad);
                    fillTable();
                }
            }
        }
    }

    private ReservaDAO dao;
    private JTable jtable;
}
