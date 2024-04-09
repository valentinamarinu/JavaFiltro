package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Empresa;
import entity.Contratacion;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        try {
            String sql = "INSERT INTO contratacion (estado, salario, id_vacante, id_coder) VALUES (?,?,?,?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objContratacion.getEstado());
            objPrepared.setDouble(2, objContratacion.getSalario());
            objPrepared.setInt(3, objContratacion.getId_vacante());
            objPrepared.setInt(4, objContratacion.getId_coder());

            objPrepared.execute();

            ResultSet objResult =objPrepared.getGeneratedKeys();

            while (objResult.next()) {
                objContratacion.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null,"Contratación agregada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objContratacion;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listContratacion = new ArrayList<>();

        try {
            String sql = "SELECT * FROM contratacion \n" +
                    "INNER JOIN vacante ON vacante.id = contratacion.id_vacante \n" +
                    "INNER JOIN coder ON coder.id = contratacion.id_coder;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Contratacion objContratacion = new Contratacion();

                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setFecha_aplicacion(objResult.getString("contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("contratacion.salario"));
                objContratacion.setId_vacante(objResult.getInt("contratacion.id_vacante"));
                objContratacion.setId_coder(objResult.getInt("contratacion.id_coder"));

                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));

                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("coder.id"));
                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellidos(objResult.getString("coder.apellidos"));
                objCoder.setDocumento(objResult.getString("coder.documento"));
                objCoder.setCohorte(objResult.getInt("coder.cohorte"));
                objCoder.setCv(objResult.getString("coder.cv"));
                objCoder.setClan(objResult.getString("coder.clan"));

                objContratacion.setObjVacante(objVacante);

                objContratacion.setObjCoder(objCoder);

                listContratacion.add(objContratacion);
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listContratacion;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE contratacion SET estado = ?, salario = ?, id_vacante = ?, id_coder = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objContratacion.getEstado());
            objPrepared.setDouble(2,objContratacion.getSalario());
            objPrepared.setInt(3,objContratacion.getId_coder());
            objPrepared.setInt(4,objContratacion.getId_vacante());
            objPrepared.setInt(5,objContratacion.getId());


            int totalAffectedRows = objPrepared.executeUpdate();

            if (totalAffectedRows > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"La contratación fue actualizada correctamente.");
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
        Contratacion objContratacion = (Contratacion) obj;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM contratacion WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1,objContratacion.getId());

            int totalAffectedRows = objPrepared.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"La contratación fue eliminada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
            JOptionPane.showMessageDialog(null,"ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public List<Contratacion> findByEstado (String estado) {
        Connection objConnection = ConfigDB.openConnection();
        List<Contratacion> listEstado = new ArrayList<>();

        try {
            String sql = "SELECT * FROM contratacion \n" +
                    "INNER JOIN vacante ON vacante.id = contratacion.id_vacante \n" +
                    "INNER JOIN coder ON coder.id = contratacion.id_coder WHERE estado = 'activo';";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, estado);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Contratacion objContratacion = new Contratacion();

                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setFecha_aplicacion(objResult.getString("contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("contratacion.salario"));
                objContratacion.setId_vacante(objResult.getInt("contratacion.id_vacante"));
                objContratacion.setId_coder(objResult.getInt("contratacion.id_coder"));

                Vacante objVacante = new Vacante();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));

                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("coder.id"));
                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellidos(objResult.getString("coder.apellidos"));
                objCoder.setDocumento(objResult.getString("coder.documento"));
                objCoder.setCohorte(objResult.getInt("coder.cohorte"));
                objCoder.setCv(objResult.getString("coder.cv"));
                objCoder.setClan(objResult.getString("coder.clan"));

                objContratacion.setObjVacante(objVacante);

                objContratacion.setObjCoder(objCoder);

                listEstado.add(objContratacion);
            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEstado;
    }

    public Contratacion findById (int id) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = new Contratacion();

        try {
            String sql = "SELECT * FROM contratacion \n" +
                    "INNER JOIN vacante ON vacante.id = contratacion.id_vacante \n" +
                    "INNER JOIN coder ON coder.id = contratacion.id_coder WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, id);

            ResultSet objResult = objPrepared.executeQuery();

            if (objResult.next()) {

                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setFecha_aplicacion(objResult.getString("contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("contratacion.salario"));
                objContratacion.setId_vacante(objResult.getInt("contratacion.id_vacante"));
                objContratacion.setId_coder(objResult.getInt("contratacion.id_coder"));

            }
        } catch (SQLException e) {
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objContratacion;
    }
}
