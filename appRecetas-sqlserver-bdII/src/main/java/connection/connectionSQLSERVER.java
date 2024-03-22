/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.*;

/**
 * Esta clase proporciona métodos para establecer una conexión con una base de datos SQL Server
 * y para cerrar los recursos de conexión de manera segura.
 * Requiere el driver JDBC de SQL Server para funcionar correctamente.
 * 
 * @author seth
 */
public class connectionSQLSERVER {
    
    // String que especifica el driver de conexión
    private static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    // String que especifica el nombre de la base de datos a la que se realizará la conexión
    private static String JDBC_DB = "dbGestorRecetas";
    
    // String que especifica el nombre de usuario con el que se conectará a la BD
    private static String JDBC_USER = "user1";
    
    // String que especifica la contraseña del usuario con el que se conectará a la BD
    private static String JDBC_PASS = "1234";
    
    // Variable que almacena el driver creado
    private static Driver driver = null;
    
    // String que especifica la ruta de conexión a la base de datos 
    private static String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName="+JDBC_DB+";encrypt=false";

    /**
     * Método para obtener una conexión a la base de datos.
     * 
     * @return La conexión establecida con la base de datos.
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
    public static synchronized Connection getConnection() throws SQLException {

        if (driver == null) {
            try {
                // Se carga el driver JDBC
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);

                // Se utiliza para crear una instancia de la clase del controlador JDBC cargada previamente
                driver = (Driver) jdbcDriverClass.getDeclaredConstructor().newInstance();

                // Se utiliza para registrar el controlador JDBC que has creado previamente con la instancia
                DriverManager.registerDriver(driver);

            } catch (Exception ex) {
                System.out.println("Fallo al cargar el driver");
                ex.printStackTrace();
            }
        }
        // Establecer una conexión a la base de datos utilizando los detalles de conexión proporcionados
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    /**
     * Método para cerrar un ResultSet abierto.
     * 
     * @param rs El ResultSet que se va a cerrar.
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para cerrar un PreparedStatement abierto.
     * 
     * @param stmt El PreparedStatement que se va a cerrar.
     */
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para cerrar una conexión abierta.
     * 
     * @param conn La conexión que se va a cerrar.
     */
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para cerrar un CallableStatement abierto.
     * 
     * @param cstmt El CallableStatement que se va a cerrar.
     */
    public static void close(CallableStatement cstmt) {
        try {
            if (cstmt != null) {
                cstmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}