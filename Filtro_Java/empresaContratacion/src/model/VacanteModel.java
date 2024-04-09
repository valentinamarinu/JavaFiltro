package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;

        try {
            String sql = "INSERT INTO vacante (titulo, descripcion, duracion, estado, tecnologia, id_empresa) VALUES (?,?,?,?,?,?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objVacante.getTitulo());
            objPrepared.setString(2, objVacante.getDescripcion());
            objPrepared.setString(3, objVacante.getDuracion());
            objPrepared.setString(4, objVacante.getEstado());
            objPrepared.setString(5, objVacante.getTecnologia());
            objPrepared.setInt(6, objVacante.getId_empresa());

            objPrepared.execute();

            ResultSet objResult =objPrepared.getGeneratedKeys();

            while (objResult.next()) {
                objVacante.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null,"Vacante agregada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVacante;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listVacante = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante \n" +
                            "INNER JOIN empresa ON empresa.id = vacante.id_empresa;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));

                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacante.add(objVacante);
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE vacante SET titulo = ?, descripcion = ?, duracion = ?, estado = ?, tecnologia = ? id_empresa = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objVacante.getTitulo());
            objPrepared.setString(2,objVacante.getDescripcion());
            objPrepared.setString(3,objVacante.getDuracion());
            objPrepared.setString(4,objVacante.getEstado());
            objPrepared.setString(5,objVacante.getTecnologia());
            objPrepared.setInt(6,objVacante.getId_empresa());
            objPrepared.setInt(7,objVacante.getId());

            int totalAffectedRows = objPrepared.executeUpdate();

            if (totalAffectedRows > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"La vacante fue actualizada correctamente.");
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
        Vacante objVacante = (Vacante) obj;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM vacante WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1,objVacante.getId());

            int totalAffectedRows = objPrepared.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"La vacante fue eliminada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public List<Object> findByTitulo(String titulo) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listTitulo = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante \n" +
                            "INNER JOIN empresa ON empresa.id = vacante.id_empresa WHERE titulo = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, titulo);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));

                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listTitulo.add(objVacante);
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listTitulo;
    }

    public  List<Vacante> findByTecnologia (String tecnologia){
        Connection objConnection = ConfigDB.openConnection();
        List<Vacante> listVacante = new ArrayList<>();

        try{
            String sql = "SELECT * FROM vacante \n" +
                            "INNER JOIN empresa ON empresa.id = vacante.id_empresa WHERE cv LIKE ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,"%"+tecnologia+"%");

            ResultSet objResult = objPrepared.executeQuery();

            while(objResult.next()){
                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));

                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacante.add(objVacante);
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }

    public Vacante findById (int id) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = new Vacante();

        try {
            String sql = "SELECT * FROM vacante \n" +
                    "INNER JOIN empresa ON empresa.id = vacante.id_empresa WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, id);

            ResultSet objResult = objPrepared.executeQuery();

            if (objResult.next()) {

                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
                objVacante.setId_empresa(objResult.getInt("id_empresa"));

            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVacante;
    }

    public List<Vacante> findByEstado (String estado) {
        Connection objConnection = ConfigDB.openConnection();
        List<Vacante> listEstado = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacante \n" +
                    "INNER JOIN empresa ON empresa.id = vacante.id_empresa WHERE estado = 'activo';";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, estado);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));

                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listEstado.add(objVacante);
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEstado;
    }

    public void changeEstado(int id) {

    }
}
