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

    CategoriaPorRecetaJDBC categoriaPorRecetaJDBC = new CategoriaPorRecetaJDBC();
    IngredientesPorRecetaJDBC ingredientesPorRecetaJDBC = new IngredientesPorRecetaJDBC();

    private final String SQL_INSERT_SP = "{CALL spAgregarReceta(?,?,?,?,?,?)}";

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
            categoriaPorRecetaJDBC.registrarCategoriaReceta(idReceta, categorias);

            ingredientesPorRecetaJDBC.registrarIngredientesReceta(idReceta, ingredientes);
        }

        return idReceta;
    }
}
