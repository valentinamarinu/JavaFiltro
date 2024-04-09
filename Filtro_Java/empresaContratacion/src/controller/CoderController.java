package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.List;

public class CoderController {

    public static void getAll(){
        String list = getAllString();
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAllString() {
        CoderModel objCoderModel = new CoderModel();
        String listString = "LISTA DE REGISTROS: \n";

        for (Object temp: objCoderModel.findAll()) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }
        return listString;
    }

    public static CoderModel instanceModel() {
        return new CoderModel();
    }

    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Inserte el nombre del Coder: ");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos del Coder: ");
        String documento = JOptionPane.showInputDialog("Ingresa el documento del Coder: ");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cohorte a la que pertenece el Coder: "));
        String cv = JOptionPane.showInputDialog("Ingresa la información de la hoja de vida del Coder: ");
        String clan = JOptionPane.showInputDialog("Ingresa el clan al que pertenece el Coder: ");

        instanceModel().insert(new Coder(nombre, apellido, documento, cohorte, cv, clan));
    }

    public static void delete() {
        CoderModel objCoderModel = new CoderModel();
        String listCoders = getAllString();

        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\n Ingrese el ID del Coder que desea eliminar: "));

        Coder objCoder = objCoderModel.findById(isDelete);

        if (objCoder == null) {
            System.out.println("Coder no encontrado.");
        } else {
            objCoderModel.delete(objCoder);
        }
    }

    public static void update() {
        CoderModel objCoderModel = new CoderModel();
        String listCoders = getAllString();

        int isUpdated = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\n Ingrese el ID del Coder que desea actualizar: "));

        Coder objCoder = objCoderModel.findById(isUpdated);

        if (objCoder == null) {
            System.out.println("Coder no encontrado.");
        } else {
            String nombre = JOptionPane.showInputDialog("Inserte el nuevo nombre del Coder: ");
            String apellido = JOptionPane.showInputDialog("Ingresa los nuevos apellidos del Coder: ");
            String documento = JOptionPane.showInputDialog("Ingresa el nuevo documento del Coder: ");
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la nueva cohorte a la que pertenece el Coder: "));
            String cv = JOptionPane.showInputDialog("Ingresa la nueva información de la hoja de vida del Coder: ");
            String clan = JOptionPane.showInputDialog("Ingresa el nuevo clan al que pertenece el Coder: ");

            objCoder.setNombre(nombre);
            objCoder.setApellidos(apellido);
            objCoder.setDocumento(documento);
            objCoder.setCohorte(cohorte);
            objCoder.setCv(cv);
            objCoder.setClan(clan);

            objCoderModel.update(objCoder);
        }
    }

    public static void getCohorte() {
        String cohorte = JOptionPane.showInputDialog("Inserte la cohorte por la que quiere filtrar: ");
        CoderModel objCoderModel = new CoderModel();
        String listString = "LISTA DE REGISTROS POR COHORTE: \n";

        for (Object temp: objCoderModel.findByTecnologia(cohorte)) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listString);
    }


    public static void getClan() {
        String clan = JOptionPane.showInputDialog("Inserte el clan por la que quiere filtrar: ");
        CoderModel objCoderModel = new CoderModel();
        String listString = "LISTA DE REGISTROS POR ClAN: \n";

        for (Object temp: objCoderModel.findByTecnologia(clan)) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listString);
    }

    public static void getTecnologia() {
        String tecnologia = JOptionPane.showInputDialog("Inserte la tecnología por la que quiere filtrar: ");
        CoderModel objCoderModel = new CoderModel();
        String listString = "LISTA DE REGISTROS POR TECNOLOGÍA: \n";

        for (Object temp: objCoderModel.findByTecnologia(tecnologia)) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listString);
    }
}
