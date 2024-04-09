package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;

        try {
            String sql = "INSERT INTO coder (nombre, apellido, documento, cohorte, cv, clan) VALUES (?,?,?,?,?,?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objCoder.getNombre());
            objPrepared.setString(2, objCoder.getApellidos());
            objPrepared.setString(3, objCoder.getDocumento());
            objPrepared.setInt(4, objCoder.getCohorte());
            objPrepared.setString(5, objCoder.getCv());
            objPrepared.setString(6, objCoder.getClan());

            objPrepared.execute();

            ResultSet objResult =objPrepared.getGeneratedKeys();

            while (objResult.next()) {
                objCoder.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null,"Coder agregado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listCoders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coder;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoders.add(objCoder);
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoders;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE coder SET nombre = ?, apellidos = ?, documento = ?, cohorte = ?, cv = ?, clan = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objCoder.getNombre());
            objPrepared.setString(2,objCoder.getApellidos());
            objPrepared.setString(3,objCoder.getDocumento());
            objPrepared.setInt(4,objCoder.getCohorte());
            objPrepared.setString(5,objCoder.getCv());
            objPrepared.setString(6,objCoder.getClan());
            objPrepared.setInt(7,objCoder.getId());

            int totalAffectedRows = objPrepared.executeUpdate();

            if (totalAffectedRows > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El coder fue actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM coder WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1,objCoder.getId());

            int totalAffectedRows = objPrepared.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"El coder fue eliminado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public List<Coder> findByClan(String clan) {
        Connection objConnection = ConfigDB.openConnection();
        List<Coder> listClan = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coder WHERE clan = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, clan);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listClan.add(objCoder);
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listClan;
    }

    public List<Coder> findByCohorte (int cohorte) {
        Connection objConnection = ConfigDB.openConnection();
        List<Coder> listCohorte = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coder WHERE cohorte = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, cohorte);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCohorte.add(objCoder);
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCohorte;
    }

    public  List<Coder> findByTecnologia (String tecnologia){
        Connection objConnection = ConfigDB.openConnection();
        List<Coder> listCoder = new ArrayList<>();

        try{
            String sql = "SELECT * FROM coder WHERE cv LIKE ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,"%"+tecnologia+"%");

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);
            }

        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoder;
    }

    public Coder findById (int id) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = new Coder();

        try {
            String sql = "SELECT * FROM coder WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, id);

            ResultSet objResult = objPrepared.executeQuery();

            if (objResult.next()) {

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCoder;
    }

}
