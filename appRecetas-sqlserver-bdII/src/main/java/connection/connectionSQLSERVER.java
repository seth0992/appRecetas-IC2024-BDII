/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.*;

/**
 *
 * @author seth
 */
public class connectionSQLSERVER {
    
    //String que espeficia el driver de conexión
    private static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    //String que especifica cual es la base de datos a la que nos conectamos
    private static String JDBC_DB = "dbGestorRecetas";
    
    //String que especifica el nombre de usuario 
    private static String JDBC_USER = "user1";
    
    //String que especifica la contraceña del usuario
    private static String JDBC_PASS = "1234";
    
    //Variables que almacena el driver 
    private static Driver driver = null;
    
    //String que especifica la ruta de conexion -> stringConnection
    private static String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName="+JDBC_DB+";encrypt=false";
    
    public static synchronized Connection getConnection() throws SQLException{
        
        if(driver == null){
            try{
            
                //Cargar el driver jdbc
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                
                //Utilizar el driver para crear una instancia de la clase controlador
                driver = (Driver)jdbcDriverClass.getDeclaredConstructor().newInstance();
                
                //Se utiliza para registrar el controlador JDBC que se a creado previmente
                DriverManager.registerDriver(driver);                
                
            }catch(Exception ex){
                System.out.println("Fallo el proceso de carga del driver");
                ex.printStackTrace();
            }        
        }        
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);        
    }    
    
    //Cierre de Connectios
    public static void close(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    // Cierras los preparedStatmente abiertos, si lo están
    public static void close(PreparedStatement stmt){
        try{
            if(stmt != null){
                stmt.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
   // Cierras los resultset abiertos, si lo están
    public static void close(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    // Cierras los callableStatements abiertos, si lo están
    public static void close(CallableStatement cstmt){
        try{
            if(cstmt != null){
                cstmt.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
