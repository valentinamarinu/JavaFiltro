import controller.CoderController;
import controller.ContratacionController;
import controller.EmpresaController;
import controller.VacanteController;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        // Validación de conexión -> ConfigDB.openConnection();

        int option = 0, option2 = 0;

        do {
            // Mostrar menú de opciones
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    Ingrese una opción:
                    1. Empresas registradas
                    2. Administrar vacantes
                    3. Administrar contrataciones
                    4. Administrar coders
                    5. Salir
                    """));

            switch (option) {
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                Ingrese una opción:
                                1. Mostrar empresas
                                2. Salir
                                """));

                        switch (option2) {
                            case 1:
                                EmpresaController.getAll();
                                break;
                        }
                    } while (option2 != 2);
                    break;

                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                Ingrese una opción:
                                1. Listar vacantes disponibles
                                2. Mostrar vacantes por título
                                3. Mostrar vacantes por tecnología
                                4. Crear vacante
                                5. Eliminar vacante
                                6. Actualizar vacante
                                7. Salir
                                """));

                        switch (option2) {
                            case 1:
                                VacanteController.getAllDisponibles();
                                break;
                            case 2:
                                VacanteController.insert();
                                break;
                            case 3:
                                VacanteController.getTitulo();
                                break;
                            case 4:
                                VacanteController.getTecnologia();
                                break;
                            case 5:
                                VacanteController.delete();
                                break;
                            case 6:
                                VacanteController.update();
                                break;
                        }
                    } while (option2 != 7);
                    break;

                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                Ingrese una opción:
                                1. Listar coders
                                2. Mostrar coders por cohorte
                                3. Mostrar coders por clan
                                4. Mostrar coders por tecnologia
                                5. Crear vacante
                                6. Eliminar vacante
                                7. Actualizar vacante
                                8. Salir
                                """));

                        switch (option2) {
                            case 1:
                                CoderController.getAll();
                                break;
                            case 2:
                                CoderController.insert();
                                break;
                            case 3:
                                CoderController.getCohorte();
                                break;
                            case 4:
                                CoderController.getClan();
                                break;
                            case 5:
                                CoderController.getTecnologia();
                                break;
                            case 6:
                                CoderController.delete();
                                break;
                            case 7:
                                CoderController.update();
                                break;
                        }
                    } while (option2 != 8);
                    break;

                case 4:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                Ingrese una opción:
                                1. Listar contrataciones disponibles
                                2. Crear contratación
                                3. Eliminar contratación
                                4. Actualizar contratación
                                5. Salir
                                """));

                        switch (option2) {
                            case 1:
                                ContratacionController.getAllDisponibles();
                                break;
                            case 2:
                                ContratacionController.insert();
                                break;
                            case 3:
                                ContratacionController.delete();
                                break;
                            case 4:
                                ContratacionController.update();
                                break;
                        }
                    } while (option2 != 5);
                    break;
            }
        } while (option != 5);
    }
}
