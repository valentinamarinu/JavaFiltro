package controller;

import entity.Coder;
import entity.Vacante;
import model.CoderModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class VacanteController {
    public static void getAll(){
        String list = getAllString();
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAllString() {
        VacanteModel objVacanteModel = new VacanteModel();
        String listString = "LISTA DE REGISTROS: \n";

        for (Object temp: objVacanteModel.findAll()) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }
        return listString;
    }

    public static CoderModel instanceModel() {
        return new CoderModel();
    }

    public static void insert() {
        String titulo = JOptionPane.showInputDialog("Inserte el título de la vacante: ");
        String descripcion = JOptionPane.showInputDialog("Ingresa la descripción de la vacante: ");
        String duracion = JOptionPane.showInputDialog("Ingresa la duración de la vacante: ");
        String estado = JOptionPane.showInputDialog("Ingresa el estado de la vacante(activa/inactiva): ");
        String tecnologia = JOptionPane.showInputDialog("Ingresa la tecnología de la vacante: ");
        int id_empresa  = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID de la empresa a la que pertenece la vacante: "));

        instanceModel().insert(new Vacante(titulo, descripcion, duracion, estado, tecnologia, id_empresa));
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
        VacanteModel objVacanteModel = new VacanteModel();
        String listVacante = getAllString();

        int isUpdated = Integer.parseInt(JOptionPane.showInputDialog(listVacante + "\n Ingrese el ID del Coder que desea actualizar: "));

        Vacante objVacante = objVacanteModel.findById(isUpdated);

        if (objVacante == null) {
            System.out.println("Vacante no encontrado.");
        } else {
            String titulo = JOptionPane.showInputDialog("Inserte el nuevo título de la vacante: ");
            String descripcion = JOptionPane.showInputDialog("Ingresa la nueva descripción de la vacante: ");
            String duracion = JOptionPane.showInputDialog("Ingresa la nueva duración de la vacante: ");
            String estado = JOptionPane.showInputDialog("Ingresa el nueva estado de la vacante(activa/inactiva): ");
            String tecnologia = JOptionPane.showInputDialog("Ingresa la nueva tecnología de la vacante: ");
            int id_empresa  = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el nuevo ID de la empresa a la que pertenece la vacante: "));

            objVacante.setTitulo(titulo);
            objVacante.setDescripcion(descripcion);
            objVacante.setDuracion(duracion);
            objVacante.setEstado(estado);
            objVacante.setTecnologia(tecnologia);
            objVacante.setId_empresa(id_empresa);

            objVacanteModel.update(objVacante);
        }
    }

    public static String getAllDisponibles() {
        VacanteModel objVacanteModel = new VacanteModel();
        String listString = "LISTA DE REGISTROS: \n";

        for (Object temp: objVacanteModel.findByEstado("activo")) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }
        return listString;
    }

    public static void getTitulo() {
        String titulo = JOptionPane.showInputDialog("Inserte el título por la que quiere filtrar: ");
        VacanteModel objVacanteModel = new VacanteModel();
        String listString = "LISTA DE REGISTROS POR TÍTULO: \n";

        for (Object temp: objVacanteModel.findByTitulo(titulo)) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listString);
    }

    public static void getTecnologia() {
        String tecnologia = JOptionPane.showInputDialog("Inserte la tecnología por la que quiere filtrar: ");
        VacanteModel objVacanteModel = new VacanteModel();
        String listString = "LISTA DE REGISTROS POR TECNOLOGÍA: \n";

        for (Object temp: objVacanteModel.findByTecnologia(tecnologia)) {
            Coder objCoder = (Coder)temp;
            listString += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listString);
    }

}
