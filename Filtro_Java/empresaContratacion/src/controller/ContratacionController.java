package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Vacante;
import model.CoderModel;
import model.ContratacionModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class ContratacionController {
    public static void getAll(){
        String list = getAllString();
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAllString() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listString = "LISTA DE REGISTROS: \n";

        for (Object temp: objContratacionModel.findAll()) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }
        return listString;
    }

    public static CoderModel instanceModel() {
        return new CoderModel();
    }

    public static void insert() {
        String estado = JOptionPane.showInputDialog("Inserte el estado de la contratacion: ");
        Double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el salario de la contratacion: "));
        int id_vacante  = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID de la vacante a la que pertenece la contratación: "));
        int id_coder  = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID del Coder a la que pertenece la contratación: "));

        instanceModel().insert(new Contratacion(estado, salario, id_vacante, id_coder));
    }

    public static void delete() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContratacion = getAllString();

        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listContratacion + "\n Ingrese el ID de la contratación que deseas eliminar: "));

        Contratacion objContratacion = objContratacionModel.findById(isDelete);


        if (objContratacion == null) {
            System.out.println("Coder no encontrado.");
        } else {
            objContratacionModel.delete(objContratacion);
        }
    }

    public static void update() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContratacion = getAllString();

        int isUpdated = Integer.parseInt(JOptionPane.showInputDialog(listContratacion + "\n Ingrese el ID de la contratación que desea actualizar: "));

        Contratacion objContratacion = objContratacionModel.findById(isUpdated);

        if (listContratacion == null) {
            System.out.println("Vacante no encontrado.");
        } else {
            String estado = JOptionPane.showInputDialog("Inserte el estado de la contratacion: ");
            Double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el salario de la contratacion: "));
            int id_vacante  = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID de la vacante a la que pertenece la contratación: "));
            int id_coder  = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID del Coder a la que pertenece la contratación: "));

            objContratacion.setEstado(estado);
            objContratacion.setSalario(salario);
            objContratacion.setId_vacante(id_vacante);
            objContratacion.setId_coder(id_coder);

            objContratacionModel.update(objContratacion);
        }
    }

    public static String getAllDisponibles() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listString = "LISTA DE REGISTROS: \n";

        for (Object temp: objContratacionModel.findByEstado("activo")) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }
        return listString;
    }
}
