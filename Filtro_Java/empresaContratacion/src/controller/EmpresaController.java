package controller;

import entity.Empresa;
import model.EmpresaModel;

import javax.swing.*;
import java.util.List;

public class EmpresaController {
    public static void getAll(){
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {
        String listString = "LISTA DE REGISTROS: \n";

        for (Object temp: list) {
            Empresa objEmpresa = (Empresa)temp;
            listString += objEmpresa.toString() + "\n";
        }
        return listString;
    }

    public static EmpresaModel instanceModel() {
        return new EmpresaModel();
    }
}
