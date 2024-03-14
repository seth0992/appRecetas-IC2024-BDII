/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.uaca.apprecetas.sqlserver.bdii;

import java.sql.*;
/**
 *
 * @author seth
 */
public class AppRecetasSqlserverBdII {

    public static void main(String[] args) {
        testConnection();
    }
    
    public static void testConnection(){
        Connection conn = null;
        try{
            //Obtener la conexión 
            conn = connection.connectionSQLSERVER.getConnection();
            
            System.out.println("Conexión Exitosa a la base de datos");
            
        }catch(SQLException ex){
            System.out.println("Error al establecer la conexion a la base de datos");
            ex.printStackTrace();
                    
        }finally{
            connection.connectionSQLSERVER.close(conn);
        }
    }
}
