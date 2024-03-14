/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelJDBC;

import connection.connectionSQLSERVER;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author seth
 */
public class CategoriaJDBC {
    private final String SQL_INSERT_SP = "{CALL spAgregarCategoria(?)}";
    private final String SQL_UPDATE_SP = "{CALL spEditarCategoria(?,?)}";
    private final String SQL_DELETE_SP = "{CALL spEliminarCategoria(?)}";
    private final String SQL_SELECT_SP = "{CALL spObtenerCategoriasPorNombre(?)}";

    //Método para registrar la categoría
    public int registrarCategoria(String nombreCategoria) {

        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;

        int filaAfectadas = 0;

        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_INSERT_SP); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setString(1, nombreCategoria);

            //Se ejecuta la consulta
            System.out.println("Ejecutando la Registro de Categoria");
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
    public int modificarCategoria(int idCategoria, String nombreCategoria) {

        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;

        int filaAfectadas = 0;

        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_UPDATE_SP); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setInt(1, idCategoria);
            cstmt.setString(2, nombreCategoria);

            //Se ejecuta la consulta
            System.out.println("Ejecutando la Registro de Categoria");
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
    public int eliminarCategoria(int idCategoria) {

        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;

        int filaAfectadas = 0;

        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_DELETE_SP); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setInt(1, idCategoria);

            //Se ejecuta la consulta
            System.out.println("Ejecutando la Registro de Categoria");
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
    public DefaultTableModel consultarCategorias(String nombreCat) {
        //Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;
        ResultSet rs = null;

        //Creación del modelo de la tabla
       DefaultTableModel modeloTabla = new DefaultTableModel();
       modeloTabla.addColumn("ID");
       modeloTabla.addColumn("Nombre");
       
        
        try {

            conn = connectionSQLSERVER.getConnection(); //Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_SELECT_SP, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //Se prepara la llamada al procedimiento 

            //Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setString(1, nombreCat);

            //Se ejecuta la consulta
            System.out.println("Ejecutando consulta de Categoria");
            boolean resultado = cstmt.execute();

            // Comprobar si hay un conjunto de resultados
            if (resultado) {
                // Devolver el conjunto de resultados
                rs = cstmt.getResultSet();
                while (rs.next()) {
                    // Acceder a los datos de cada fila
                    int id = rs.getInt("idCategoria");
                    String nombre = rs.getString("nombre");                  
                    modeloTabla.addRow(new Object[]{id, nombre});
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
