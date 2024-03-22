/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelJDBC;

import connection.connectionSQLSERVER;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author seth
 */
public class IngredientesPorRecetaJDBC {
    
    private final String SQL_INSERT_SP_INGREDIENTES = "{CALL spAgregarIngredientesAReceta(?,?,?)}";
    private final String SQL_DELETE_SP_INGREDIENTES = "{CALL spEliminarIngredienteDeReceta(?,?)}";
    
    public int registrarIngredientesReceta(int idReceta, Object[][] ingredientes) {
        Connection conn = null;
        CallableStatement cstmt = null;

        int filasAfectadas = 0;

        try {
            conn = connectionSQLSERVER.getConnection(); // Obtener la conexión una vez antes del bucle
            cstmt = conn.prepareCall(SQL_INSERT_SP_INGREDIENTES); // Preparar la llamada al procedimiento

            for (Object[] categoria : ingredientes) {
                // Sustituir los valores a enviar en el procedimiento almacenado
                cstmt.setInt(1, idReceta);
                cstmt.setInt(2, Integer.parseInt(categoria[0].toString()));
                cstmt.setDouble(3, Double.parseDouble(categoria[2].toString()));

                // Ejecutar la consulta
                System.out.println("Ejecutando EL Registro de ingredeintes a la receta");
                cstmt.execute();
                filasAfectadas += cstmt.getUpdateCount();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connectionSQLSERVER.close(cstmt);
            connectionSQLSERVER.close(conn); // Cerrar la conexión después de salir del bucle
        }

        return filasAfectadas;
    }
    
       //Método para eliminar Categoria a un ingrediente ya creada
    public int eliminarCategoria(int idIngrediente, int idReceta) {

        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;

        int filaAfectadas = 0;

        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_DELETE_SP_INGREDIENTES); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setInt(1, idReceta);
            cstmt.setInt(2, idIngrediente);
            //Se ejecuta la consulta
            System.out.println("Ejecutando el borrado de ingredientes a receta");
            cstmt.execute();
            filaAfectadas = cstmt.getUpdateCount();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connectionSQLSERVER.close(cstmt);
            connectionSQLSERVER.close(conn);
        }

        return filaAfectadas;
    }
    

}
