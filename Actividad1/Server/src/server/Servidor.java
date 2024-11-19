package server;
import model.Libro;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args){

        // Inicializar libros
        Libro.inicializarLibros();

        System.out.println("APLICACIÓN DE SERVIDOR");
        System.out.println("----------------------------------");

        ServerSocket serverSocket = null;

        try {

            // Intentar crear el ServerSocket
            serverSocket = new ServerSocket(8080);
            System.out.println("Servidor escuchando en el puerto 8080...");

        } catch (IOException e) {

            // Manejar el error de creación del ServerSocket
            System.out.println("Error al iniciar el servidor en el puerto 8080: " + e.getMessage());
            return; // Terminar el programa si no se puede crear el ServerSocket

        }

        //while para manejar las conexiones de los clientes
        while (true) {

            // Declarar el socket del cliente
            Socket clientSocket = null;

            try {

                // Aceptar conexión con un cliente
                clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                // Obtener el flujo de entrada del socket del cliente
                InputStream inputStream = clientSocket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader in = new BufferedReader(inputStreamReader);

                // Obtener el flujo de salida del socket del cliente
                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream, true);


                // Leer la opción seleccionada por el cliente
                String opcion = in.readLine();
                boolean salir = true;

                switch (opcion) {
                    case "consultarISBN" -> {

                        String isbn = in.readLine(); // Leer ISBN enviado por el cliente
                        String resultado = Libro.consultarPorISBN(isbn);
                        out.println(resultado); // Enviar resultado

                    }
                    case "consultarTitulo" -> {

                        String titulo = in.readLine(); // Leer título enviado por el cliente
                        String resultado = Libro.consultarPorTitulo(titulo);
                        out.println(resultado); // Enviar resultado

                    }
                    case "consultarAutor" -> {

                        String autor = in.readLine(); // Leer autor enviado por el cliente
                        String resultado = Libro.consultarPorAutor(autor);
                        out.println(resultado); // Enviar resultado

                    }
                    case "salir" -> {

                        out.println("Desconectando. ¡Hasta luego!");
                        salir = false;

                    }
                    default -> {
                        // Opción inválida
                        out.println("Opción no válida. Por favor, elige una opción del menú.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al manejar cliente: " + e.getMessage());
            } finally {
                // Garantizar el cierre del cliente
                if (clientSocket != null) {
                    try {
                        clientSocket.close();
                        System.out.println("Conexión con el cliente cerrada.");
                    } catch (IOException e) {
                        System.out.println("Error al cerrar la conexión del cliente: " + e.getMessage());
                    }
                }
            }
        }
    }
}
