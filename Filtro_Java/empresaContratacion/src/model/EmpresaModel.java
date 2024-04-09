package model;

import database.ConfigDB;
import entity.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel {
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listEmpresa = new ArrayList<>();

        try {
            String sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                listEmpresa.add(objEmpresa);
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEmpresa;
    }
}
