package cine;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crea un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        // Crea un objeto PeliculaDB para interactuar con la base de datos
        PeliculaDB peliculaDB = new PeliculaDB();

        // Bucle principal que muestra el menú hasta que el usuario elige salir
        while (true) {
            // Muestra el menú de opciones
            System.out.println("Sistema de Gestión de Películas");
            System.out.println("1 - Ver Películas");
            System.out.println("2 - Añadir película");
            System.out.println("3 - Eliminar película");
            System.out.println("4 - Modificar película");
            System.out.println("5 - Salir");
            System.out.print("Opción: ");

            // Lee la opción seleccionada por el usuario
            String opcion = scanner.nextLine();

            // Evalúa la opción seleccionada
            switch (opcion) {
                case "1":
                    // Llama al método para mostrar todas las películas
                    peliculaDB.mostrarPeliculas();
                    break;

                case "2":
                    // Solicita los datos de la nueva película
                    System.out.print("Código de la película (ej. PEL006): ");
                    String codigo = scanner.nextLine();
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Director: ");
                    String director = scanner.nextLine();
                    System.out.print("Duración (minutos): ");
                    int duracion;
                    try {
                        // Convierte la entrada a entero y valida
                        duracion = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        // Muestra un error si la duración no es un número
                        System.out.println("Error: Duración debe ser un número");
                        break;
                    }
                    System.out.print("Año de estreno: ");
                    int anio;
                    try {
                        // Convierte la entrada a entero y valida
                        anio = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        // Muestra un error si el año no es un número
                        System.out.println("Error: Año debe ser un número");
                        break;
                    }
                    System.out.print("ID género (1-Acción, 2-Drama, 3-Comedia, 4-Ciencia Ficción): ");
                    int genero;
                    try {
                        // Convierte la entrada a entero y valida
                        genero = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        // Muestra un error si el género no es un número
                        System.out.println("Error: ID género debe ser un número");
                        break;
                    }
                    // Llama al método para añadir la película
                    peliculaDB.anadirPelicula(codigo, titulo, director, duracion, anio, genero);
                    break;

                case "3":
                    // Solicita el código de la película a eliminar
                    System.out.print("Código de la película a eliminar: ");
                    String codigoEliminar = scanner.nextLine();
                    // Llama al método para eliminar la película
                    peliculaDB.eliminarPelicula(codigoEliminar);
                    break;

                case "4":
                    // Solicita el código y los nuevos datos de la película a modificar
                    System.out.print("Código de la película a modificar: ");
                    String codigoModificar = scanner.nextLine();
                    System.out.print("Nuevo título: ");
                    String nuevoTitulo = scanner.nextLine();
                    System.out.print("Nuevo director: ");
                    String nuevoDirector = scanner.nextLine();
                    // Llama al método para modificar la película
                    peliculaDB.modificarPelicula(codigoModificar, nuevoTitulo, nuevoDirector);
                    break;

                case "5":
                    // Muestra un mensaje de despedida y cierra el programa
                    System.out.println("¡Adiós!");
                    // Cierra el Scanner
                    scanner.close();
                    // Termina el programa
                    return;

                default:
                    // Muestra un mensaje si la opción no es válida
                    System.out.println("Opción no válida");
            }
        }
    }
}
