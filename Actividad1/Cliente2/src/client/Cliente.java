package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        System.out.println("APLICACIÓN DE CLIENTE");
        System.out.println("----------------------------------");

        Scanner scanner = new Scanner(System.in);

        try {
            Socket socket = new Socket("localhost", 8080);

            // Obtener flujos de entrada y salida
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader entrada = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter salida = new PrintWriter(outputStream, true);

            boolean salir = false;

            while (!salir) {
                // Mostrar menú al usuario
                System.out.println("Seleccione una opción:");
                System.out.println("1. Consultar libro por ISBN");
                System.out.println("2. Consultar libro por título");
                System.out.println("3. Consultar libros por autor");
                System.out.println("4. Agregar libro");
                System.out.println("5. Salir de la aplicación");

                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1" -> {
                        // Consultar libro por ISBN
                        System.out.print("Introduce el ISBN del libro: ");
                        String isbn = scanner.nextLine();

                        // Enviar opción y datos al servidor
                        salida.println("consultarISBN");
                        salida.println(isbn);

                        // Leer respuesta del servidor
                        String respuesta = entrada.readLine();
                        System.out.println("Respuesta del servidor: " + respuesta);
                    }
                    case "2" -> {
                        // Consultar libro por título
                        System.out.print("Introduce el título del libro: ");
                        String titulo = scanner.nextLine();

                        // Enviar opción y datos al servidor
                        salida.println("consultarTitulo");
                        salida.println(titulo);

                        // Leer respuesta del servidor
                        String respuesta = entrada.readLine();
                        System.out.println("Respuesta del servidor: " + respuesta);
                    }
                    case "3" -> {
                        // Consultar libros por autor
                        System.out.print("Introduce el autor: ");
                        String autor = scanner.nextLine();

                        // Enviar opción y datos al servidor
                        salida.println("consultarAutor");
                        salida.println(autor);

                        // Leer respuesta del servidor
                        String respuesta = entrada.readLine();
                        System.out.println("Respuesta del servidor: ");
                        System.out.println(respuesta);
                    }
                    case "4" -> {
                        // Agregar libro
                        System.out.print("Introduce el ISBN del nuevo libro: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Introduce el título del nuevo libro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Introduce el autor del nuevo libro: ");
                        String autor = scanner.nextLine();
                        System.out.print("Introduce el precio del nuevo libro: ");
                        String precioStr = scanner.nextLine();

                        // Enviar opción al servidor
                        salida.println("agregarLibro");

                        // Enviar cada dato del libro por separado
                        salida.println(isbn);
                        salida.println(titulo);
                        salida.println(autor);
                        salida.println(precioStr);

                        // Leer respuesta del servidor
                        String respuesta = entrada.readLine();
                        System.out.println("Respuesta del servidor: " + respuesta);
                    }
                    case "5" -> {
                        // Salir
                        salida.println("salir");
                        salir = true;
                        String respuesta = entrada.readLine();
                        System.out.println("Respuesta del servidor: " + respuesta);
                    }
                    default -> {
                        System.out.println("Opción no válida. Por favor, elige una opción del menú.");
                    }
                }
            }

            // Cerrar recursos
            entrada.close();
            salida.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }

    }
}