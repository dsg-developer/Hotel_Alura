package com.ds.hotel_alura.models;

import com.ds.hotel_alura.Config;
import com.ds.hotel_alura.models.huesped.Huespedes;
import com.ds.hotel_alura.models.reservas.Reservas;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryDAO<T, U> {

    public RepositoryDAO() {
        config = new Config();
        this.cn = config.getConnection();
    }

    public long save(T entidad) throws IllegalArgumentException {
        long respuesta = 0;
        final Field[] campos = entidad.getClass().getDeclaredFields();
        @SuppressWarnings("unchecked")
        String sql = createQuery((Class<T>) entidad.getClass(), campos, "insert into ");
        try (
                PreparedStatement pst = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            for (int i = 0; i < campos.length; i++) {
                campos[i].setAccessible(true);
                pst.setObject(i + 1, campos[i].get(entidad));
            }
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                respuesta = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RepositoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    private String createQuery(Class<T> clase, Field[] campos, String Action) {
        String query = null;
        switch (Action) {
            case "insert into " -> {
                query = insert(clase, campos, Action);
            }

            case "update " -> {
                query = update(clase, campos, Action);
            }
        }
        return query;
    }

    private String insert(Class<T> clase, Field[] campos, String Action) {
        StringBuilder query = new StringBuilder();
        query.append(Action);
        query.append(" ");
        query.append(clase.getSimpleName());
        query.append("(");
        for (int x = 0; x < campos.length; x++) {
            query.append(campos[x].getName());
            if (x < campos.length - 1) {
                query.append(", ");
            }
        }
        query.append(") VALUES (");

        for (int x = 0; x < campos.length; x++) {
            query.append("?");
            if (x < campos.length - 1) {
                query.append(", ");
            }
        }

        query.append(")");

        return query.toString();
    }
    
    private String update(Class<T> clase, Field[] campos, String Action) {
        StringBuilder query = new StringBuilder();
        query.append(Action);
        query.append(" ");
        query.append(clase.getSimpleName());
        query.append(" set ");
        for (int x = 0; x < campos.length; x++) {
            query.append(campos[x].getName());
            query.append("=?");
            if (x < campos.length - 1) {
                query.append(", ");
            }
        }
        query.append(" where id = ?");

        return query.toString();
    }

    public List<T> findById(T entidad) {
        List<T> respuesta = new ArrayList<>();
        String sql = "select * from "+entidad.getClass().getSimpleName()+" "
                + "where id = ?";
        Field[] camp = entidad.getClass().getDeclaredFields();
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            camp[0].setAccessible(true);
            pst.setObject(1, camp[0].get(entidad));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object generic = entidad;
                if (generic instanceof Reservas) {
                    Reservas entity = new Reservas(
                            rs.getLong("id"),
                            rs.getString("fecha_entrada"),
                            rs.getString("fecha_salida"),
                            rs.getInt("precio"),
                            rs.getString("forma_pago")
                    );
                    respuesta.add((T) entity);
                } else {
                    Huespedes entity = new Huespedes(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("fecha_nacimiento"),
                            rs.getString("nacionalidad"),
                            rs.getString("telefono"),
                            rs.getLong("id_reserva")
                    );
                    respuesta.add((T) entity);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(RepositoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public List<T> findByName(String name) {
        List<T> entidad = null;
        try {

        } catch (Exception e) {
        }
        return entidad;
    }

    public List<T> findAll(T entidad) {
        List<T> respuesta = new ArrayList<>();
        try {
            String sql = "select * from " + entidad.getClass().getSimpleName();
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object generic = entidad;
                if (generic instanceof Reservas) {
                    Reservas entity = new Reservas(
                            rs.getLong("id"),
                            rs.getString("fecha_entrada"),
                            rs.getString("fecha_salida"),
                            rs.getInt("precio"),
                            rs.getString("forma_pago")
                    );
                    respuesta.add((T) entity);
                } else {
                    Huespedes entity = new Huespedes(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("fecha_nacimiento"),
                            rs.getString("nacionalidad"),
                            rs.getString("telefono"),
                            rs.getLong("id_reserva")
                    );
                    respuesta.add((T) entity);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public void update(T entidad){
        try {
            Field[] campos = entidad.getClass().getDeclaredFields();
            String sql = createQuery((Class<T>) entidad.getClass(), campos,
                     "update ");
            PreparedStatement pst = cn.prepareStatement(sql);
            for(int x = 0; x < campos.length; x++){
                campos[x].setAccessible(true);
                pst.setObject(x+1, campos[x].get(entidad));
                if(x == campos.length-1)
                    pst.setObject(x+2, campos[0].get(entidad));
            }
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(RepositoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecycle(U id) {
        try {

        } catch (Exception e) {
        }
    }

    public void delete(T entidad) {
        try {
            Field[] campos = entidad.getClass().getDeclaredFields();
            String sql = "delete from "+entidad.getClass().getSimpleName()+
                    " where id = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            campos[0].setAccessible(true);
            pst.setObject(1, campos[0].get(entidad));
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(RepositoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<T> CustomeFindQuery(T entidad, String query) {
        List<T> respuesta = new ArrayList<>();
        try {
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object generic = entidad;
                if (generic instanceof Reservas) {
                    Reservas entity = new Reservas(
                        rs.getLong("id"),
                        rs.getString("fecha_entrada"),
                        rs.getString("fecha_salida"),
                        rs.getInt("precio"),
                        rs.getString("forma_pago")
                    );
                    respuesta.add((T) entity);
                } else {
                    Huespedes entity = new Huespedes(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("nacionalidad"),
                        rs.getString("telefono"),
                        rs.getLong("id_reserva")
                    );
                    respuesta.add((T) entity);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(RepositoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public void CustomeQuery(String query, String... Args) {
        try {

        } catch (Exception e) {
        }
    }

    private Connection cn;
    private Config config;
}
