package com.ds.hotel_alura.models;

import com.ds.hotel_alura.Config;
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
        String sql = createQuery((Class<T>) entidad.getClass(), campos, "insert into");
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
                System.err.println(rs.getInt(1));
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
            case "insert into" -> {
                query = insert(clase, campos, Action);
            }

            case "" -> {
                insert(clase, campos, Action);
            }
        }
        return query;
    }

    private String insert(Class<T> clase, Field[] campos, String Action) {
        StringBuilder query = new StringBuilder();
        query.append(Action + " ");
        query.append(clase.getSimpleName());
        System.err.println(clase.getSimpleName().toLowerCase());
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

    public T findById(U id) {
        T entidad = null;
        try {

        } catch (Exception e) {
        }
        return entidad;
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
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public void update(U id) {
        try {

        } catch (Exception e) {
        }
    }

    public void deleteRecycle(U id) {
        try {

        } catch (Exception e) {
        }
    }

    public void delete(U id) {
        try {

        } catch (Exception e) {
        }
    }

    public List<T> CustomeFindQuery(String query) {
        List<T> respuesta = null;
        try {

        } catch (Exception e) {
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
