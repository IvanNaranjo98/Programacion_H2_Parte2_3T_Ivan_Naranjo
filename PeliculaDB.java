package cine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PeliculaDB {
    public void mostrarPeliculas() {
        // Obtiene la conexión a la base de datos
        Connection conn = DatabaseConnection.getConnection();
        // Si la conexión falla, sale del método
        if (conn == null) {
            return;
        }

        try {
            // Crea un Statement para ejecutar una consulta SQL
            Statement stmt = conn.createStatement();
            // Consulta que une las tablas peliculas y generos para obtener código, título y nombre del género
            ResultSet rs = stmt.executeQuery("SELECT p.codigo_pelicula, p.titulo, g.nombre " + "FROM peliculas p JOIN generos g ON p.id_genero = g.id_genero");

            // Itera sobre los resultados de la consulta
            while (rs.next()) {
                // Muestra el código, título y género de cada película en formato legible
                System.out.println(rs.getString("codigo_pelicula") +" "+ rs.getString("titulo") +" "+ rs.getString("nombre"));
            }

            // Cierra los recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Muestra un mensaje de error si falla la consulta
            System.out.println("Error al mostrar películas: " + e.getMessage());
        }
    }

    public boolean anadirPelicula(String codigoPelicula, String titulo, String director, int duracion, int anioEstreno, int idGenero) {
        // Obtiene la conexión a la base de datos
        Connection conn = DatabaseConnection.getConnection();
        // Si la conexión falla, retorna false
        if (conn == null) {
            return false;
        }

        try {
            // Consulta para verificar si ya existe una película con el mismo código
            String checkSql = "SELECT codigo_pelicula FROM peliculas WHERE codigo_pelicula = ?";
            // Prepara la consulta
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            // Asigna el código de la película al parámetro
            checkStmt.setString(1, codigoPelicula);
            // Ejecuta la consulta
            ResultSet rs = checkStmt.executeQuery();
            
            // Si existe una película con ese código, muestra un error y retorna false
            if (rs.next()) {
                System.out.println("Error: Ya existe una película con el código " + codigoPelicula);
                rs.close();
                checkStmt.close();
                conn.close();
                return false;
            }
            // Cierra los recursos de la verificación
            rs.close();
            checkStmt.close();

            // Consulta para insertar una nueva película
            String sql = "INSERT INTO peliculas (codigo_pelicula, titulo, director, duracion, anio_estreno, id_genero) " + "VALUES (?, ?, ?, ?, ?, ?)";
            // Prepara la consulta
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Asigna los valores a los parámetros
            pstmt.setString(1, codigoPelicula);
            pstmt.setString(2, titulo);
            pstmt.setString(3, director);
            pstmt.setInt(4, duracion);
            pstmt.setInt(5, anioEstreno);
            pstmt.setInt(6, idGenero);

            // Ejecuta la inserción y obtiene el número de filas afectadas
            int rowsAffected = pstmt.executeUpdate();
            // Cierra el PreparedStatement y la conexión
            pstmt.close();
            conn.close();
            
            // Si se insertó al menos una fila, la operación fue exitosa
            if (rowsAffected > 0) {
                System.out.println("Película añadida correctamente");
                return true;
            }
            // Si no se insertó ninguna fila, retorna false
            return false;

        } catch (SQLException e) {
            // Muestra un mensaje de error si falla la operación
            System.out.println("Error al añadir película: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPelicula(String codigoPelicula) {
        // Obtiene la conexión a la base de datos
        Connection conn = DatabaseConnection.getConnection();
        // Si la conexión falla, retorna false
        if (conn == null) {
            return false;
        }

        try {
            // Consulta para verificar si existe la película
            String checkSql = "SELECT codigo_pelicula FROM peliculas WHERE codigo_pelicula = ?";
            // Prepara la consulta parametrizada
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            // Asigna el código de la película al parámetro
            checkStmt.setString(1, codigoPelicula);
            // Ejecuta la consulta
            ResultSet rs = checkStmt.executeQuery();
            
            // Si no existe la película, muestra un error y retorna false
            if (!rs.next()) {
                System.out.println("Error: No existe una película con el código " + codigoPelicula);
                rs.close();
                checkStmt.close();
                conn.close();
                return false;
            }
            // Cierra los recursos de la verificación
            rs.close();
            checkStmt.close();

            // Consulta para eliminar la película
            String sql = "DELETE FROM peliculas WHERE codigo_pelicula = ?";
            // Prepara la consulta parametrizada
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Asigna el código de la película al parámetro
            pstmt.setString(1, codigoPelicula);

            // Ejecuta la eliminación y obtiene el número de filas afectadas
            int rowsAffected = pstmt.executeUpdate();
            // Cierra el PreparedStatement y la conexión
            pstmt.close();
            conn.close();
            
            // Si se eliminó al menos una fila, la operación fue exitosa
            if (rowsAffected > 0) {
                System.out.println("Película eliminada correctamente");
                return true;
            }
            // Si no se eliminó ninguna fila, retorna false
            return false;

        } catch (SQLException e) {
            // Muestra un mensaje de error si falla la operación
            System.out.println("Error al eliminar película: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarPelicula(String codigoPelicula, String nuevoTitulo, String nuevoDirector) {
        // Obtiene la conexión a la base de datos
        Connection conn = DatabaseConnection.getConnection();
        // Si la conexión falla, retorna false
        if (conn == null) {
            return false;
        }

        try {
            // Consulta para verificar si existe la película
            String checkSql = "SELECT codigo_pelicula FROM peliculas WHERE codigo_pelicula = ?";
            // Prepara la consulta parametrizada
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            // Asigna el código de la película al parámetro
            checkStmt.setString(1, codigoPelicula);
            // Ejecuta la consulta
            ResultSet rs = checkStmt.executeQuery();
            
            // Si no existe la película, muestra un error y retorna false
            if (!rs.next()) {
                System.out.println("Error: No existe una película con el código " + codigoPelicula);
                rs.close();
                checkStmt.close();
                conn.close();
                return false;
            }
            // Cierra los recursos de la verificación
            rs.close();
            checkStmt.close();

            // Consulta para actualizar el título y director de la película
            String sql = "UPDATE peliculas SET titulo = ?, director = ? WHERE codigo_pelicula = ?";
            // Prepara la consulta parametrizada
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Asigna los nuevos valores a los parámetros
            pstmt.setString(1, nuevoTitulo);
            pstmt.setString(2, nuevoDirector);
            pstmt.setString(3, codigoPelicula);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = pstmt.executeUpdate();
            // Cierra el PreparedStatement y la conexión
            pstmt.close();
            conn.close();
            
            // Si se actualizó al menos una fila, la operación fue exitosa
            if (rowsAffected > 0) {
                System.out.println("Película modificada correctamente");
                return true;
            }
            // Si no se actualizó ninguna fila, retorna false
            return false;

        } catch (SQLException e) {
            // Muestra un mensaje de error si falla la operación
            System.out.println("Error al modificar película: " + e.getMessage());
            return false;
        }
    }
}
