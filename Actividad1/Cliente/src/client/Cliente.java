package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try (

                Socket socket = new Socket("127.0.0.1", 8080); //creamos el socket en localhost (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)

        ) {
            System.out.println("Conectado al servidor.");

            boolean salir = true;

            while (salir) {
                // Mostrar menú al usuario
                System.out.println("""
                        MENÚ
                        1. Consultar ISBN
                        2. Consultar Título
                        3. Consultar Autor
                        4. Salir
                        Elige una opción:
                        """);

                // Leer la opción del usuario
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1" -> {
                        out.println("consultarISBN"); // Enviar comando al servidor
                        System.out.print("Introduce el ISBN: ");
                        String isbn = scanner.nextLine();
                        out.println(isbn); // Enviar ISBN al servidor
                        String resultado = in.readLine(); // Leer resultado
                        System.out.println("Resultado: " + resultado);
                    }
                    case "2" -> {
                        out.println("consultarTitulo"); // Enviar comando al servidor
                        System.out.print("Introduce el título: ");
                        String titulo = scanner.nextLine();
                        out.println(titulo); // Enviar título al servidor
                        String resultado = in.readLine(); // Leer resultado
                        System.out.println("Resultado: " + resultado);
                    }
                    case "3" -> {
                        out.println("consultarAutor"); // Enviar comando al servidor
                        System.out.print("Introduce el autor: ");
                        String autor = scanner.nextLine();
                        out.println(autor); // Enviar autor al servidor
                        String resultado = in.readLine(); // Leer resultado
                        System.out.println("Resultado:\n" + resultado);
                    }
                    case "4" -> {
                        out.println("salir"); // Enviar comando al servidor
                        System.out.println("Desconectando...");
                         salir = false;// Finalizar cliente
                    }
                    default -> System.out.println("Opción no válida. Por favor, intenta nuevamente.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

