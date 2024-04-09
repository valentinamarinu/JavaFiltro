package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;

    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/empresa_contratacion";
            String user = "root";
            String password = "";

            // Establecer la conexión
            objConnection = DriverManager.getConnection(url,user,password);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR > Driver no instalado " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("ERROR > Al conectar la base de datos " + e.getMessage());
        }

        return objConnection;
    }

    public static void closeConnection() {
        try {
            if (objConnection != null) {
                objConnection.close();
                System.out.println("Conexión finalizada con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
    }
}
