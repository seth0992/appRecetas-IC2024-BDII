/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apprecetas.sqlserver.pkg2024;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author seth
 * ISO 8859-1 -> para tildes
 */
public class AppRecetasSQLSERVER2024 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        testConnection();
    }
 
    
     public static void testConnection(){
     Connection conn = null;

        try {
            // Obtén la conexión utilizando el método getConnection de la clase conexionSQLServer
            conn = connection.connectionSQLServer.getConnection();

            // Si no se lanzó ninguna excepción, la conexión fue exitosa
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar la conexión al finalizar
            connection.connectionSQLServer.close(conn);
        }
    }
}
