/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelJDBC;

import connection.connectionSQLSERVER;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author seth09
 */
public class IngredienteJDBC {
    private final String SQL_INSERT_SP = "{CALL spAgregarCategoria(?)}";
    private final String SQL_UPDATE_SP = "{CALL spEditarCategoria(?,?)}";
    private final String SQL_DELETE_SP = "{CALL spEliminarCategoria(?)}";
    private final String SQL_SELECT_SP = "{CALL spObtenerCategoriasPorNombre(?)}";

    //Método para registrar la categoría
    public int registrarIngredientes(String nombreIngrediente, String unidadMedida) {

        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;

        int filaAfectadas = 0;

        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_INSERT_SP); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setString(1, nombreIngrediente);
            cstmt.setString(2, unidadMedida);

            //Se ejecuta la consulta
            System.out.println("Ejecutando la Registro del Ingrediente");
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

    //Método para modificar Categoria
    public int modificarIngrediente(int idIngrediente, String nombreIngrediente, String unidadMedida) {

        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;

        int filaAfectadas = 0;

        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_UPDATE_SP); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setInt(1, idIngrediente);
                     cstmt.setString(2, nombreIngrediente);
            cstmt.setString(3, unidadMedida);

            //Se ejecuta la consulta
            System.out.println("Ejecutando la modificación del Ingrediente");
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

    //Método para eliminar Categoria
    public int eliminarIngrediente(int idIngrediente) {

        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;

        int filaAfectadas = 0;

        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_DELETE_SP); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setInt(1, idIngrediente);

            //Se ejecuta la consulta
            System.out.println("Ejecutando la eliminación del ingrediente");
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

    //Método para obtener las categorías
    public DefaultTableModel consultarIngrediente(String nombreIngrediente) {
        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;
        ResultSet rs = null;

        //Creación del modelo de la tabla
       DefaultTableModel modeloTabla = new DefaultTableModel();
       modeloTabla.addColumn("ID");
       modeloTabla.addColumn("Nombre");
       modeloTabla.addColumn("Unidad Medida");
       
        
        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_SELECT_SP, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setString(1, nombreIngrediente);

            //Se ejecuta la consulta
            System.out.println("Ejecutando consulta de Categoria");
            boolean resultado = cstmt.execute();

            // Comprobar si hay un conjunto de resultados
            if (resultado) {
                // Devolver el conjunto de resultados
                rs = cstmt.getResultSet();
                while (rs.next()) {
                    // Acceder a los datos de cada fila
                    int id = rs.getInt("IdIngrediente");
                    String nombre = rs.getString("Nombre");   
                    String unidadMedida = rs.getString("UnidadMedida");
                    modeloTabla.addRow(new Object[]{id, nombre, unidadMedida});
                }
            } 

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connectionSQLSERVER.close(cstmt);
            connectionSQLSERVER.close(conn);
            connectionSQLSERVER.close(rs);
        }

        return modeloTabla;
    }
}
