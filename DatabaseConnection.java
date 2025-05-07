package cine;
import java.sql.Connection; // Importamos una clase para conectar con la base de datos.
import java.sql.DriverManager; // Esta clase nos ayuda a hacer la conexión.
import java.sql.SQLException; // Esto es para manejar errores si algo falla al conectar.

public class DatabaseConnection { // Creo una clase que se llama DatabaseConnection.
    // Estas son las "instrucciones" para conectarnos a la base de datos.
    private static final String URL = "jdbc:mysql://localhost:3306/cine_ivan_naranjo"; // La dirección de la base de datos
    private static final String USER = "root"; // El usuario para entrar a la base de datos.
    private static final String PASSWORD = "curso"; // La contraseña para entrar.

    public static Connection getConnection() { // Este método nos da la conexión a la base de datos.
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD); // Usamos la dirección, usuario y contraseña para conectar.
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos"); // Mostramos un mensaje de error.
            e.printStackTrace(); // Mostramos más detalles del error.
            return null; // Devolvemos "nada" porque no se pudo conectar.
        }
    }
}
