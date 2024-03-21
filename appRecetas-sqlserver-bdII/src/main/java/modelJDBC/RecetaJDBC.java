/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelJDBC;

import connection.connectionSQLSERVER;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author seth
 */
public class RecetaJDBC {

    private final String SQL_INSERT_SP = "{CALL spAgregarReceta(?,?,?,?,?,?)}";
    private final String SQL_UPDATE_SP = "{CALL spEditarCategoria(?,?)}";
    private final String SQL_DELETE_SP = "{CALL spEliminarCategoria(?)}";
    private final String SQL_SELECT_SP = "{CALL spObtenerCategoriasPorNombre(?)}";
    private final String SQL_INSERT_SP_INGREDIENTES = "{CALL spAgregarIngredientesAReceta(?,?,?)}";
    private final String SQL_INSERT_SP_CATEGORIAS = "{CALL spAgregarCategoriasAReceta(?,?)}";

    /**
     * Método para registrar una nueva receta en la base de datos.
     *
     * @param nombre El nombre de la receta.
     * @param descripcion La descripción de la receta.
     * @param tiempoPreparacion El tiempo de preparación estimado de la receta
     * en minutos.
     * @param dificultad El nivel de dificultad de la receta.
     * @param imagen Los datos de la imagen de la receta en formato de byte
     * array.
     * @param categorias Un arreglo bidimensional de objetos que contiene las
     * categorías asociadas a la receta.
     * @param ingredientes Un arreglo bidimensional de objetos que contiene los
     * ingredientes asociados a la receta.
     * @return El número de filas afectadas por la operación de inserción.
     */
    public int registrarReceta(String nombre, String descripcion, int tiempoPreparacion, int dificultad, byte[] imagen, Object[][] categorias, Object[][] ingredientes) {
        // Objeto de conexión
        Connection conn = null;
        // prepareCall -> para realizar el llamado del procedimiento almacenado
        CallableStatement cstmt = null;
        int idReceta = 0;
        int filaAfectadas = 0;

        try {
            conn = connectionSQLSERVER.getConnection(); // Se obtiene la conexion desde la clase Conexion SQL Server
            cstmt = conn.prepareCall(SQL_INSERT_SP); // Se prepara la llamada al procedimiento 

            // Se Sustituye los valores a enviar en el procedimiento almacenado
            cstmt.setString(1, nombre);
            cstmt.setString(2, descripcion);
            cstmt.setInt(3, tiempoPreparacion);
            cstmt.setInt(4, dificultad);
            cstmt.setBytes(5, imagen);

            // Definir el parámetro de salida para el ID de la receta
            cstmt.registerOutParameter(6, Types.INTEGER);

            // Se ejecuta la consulta
            System.out.println("Ejecutando la Registro de Categoria");
            cstmt.execute();
            // Obtener el valor del parámetro de salida
            idReceta = cstmt.getInt(6);

            filaAfectadas = cstmt.getUpdateCount();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connectionSQLSERVER.close(cstmt);
            connectionSQLSERVER.close(conn);
        }

        if (idReceta != 0) {
            registrarCategoriaReceta(idReceta, categorias);

            registrarIngredientesReceta(idReceta, ingredientes);
        }

        return idReceta;
    }

    public int registrarCategoriaReceta(int idReceta, Object[][] categorias) {
        Connection conn = null;
        CallableStatement cstmt = null;

        int filasAfectadas = 0;

        try {
            conn = connectionSQLSERVER.getConnection(); // Obtener la conexión una vez antes del bucle
            cstmt = conn.prepareCall(SQL_INSERT_SP_CATEGORIAS); // Preparar la llamada al procedimiento

            for (Object[] categoria : categorias) {
                // Sustituir los valores a enviar en el procedimiento almacenado
                cstmt.setInt(1, idReceta);
                cstmt.setInt(2, Integer.parseInt(categoria[0].toString()));

                // Ejecutar la consulta
                System.out.println("Ejecutando la Registro de Categoria a la receta");
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
                System.out.println("Ejecutando la Registro de Categoria a la receta");
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
