package server;
import model.Libro;

import java.io.*;
import java.net.Socket;

public class HiloEscuchador implements Runnable {
    private Socket socketCliente;

    public HiloEscuchador(Socket socket) {
        this.socketCliente = socket;
    }

    @Override
    public void run() {
        try {

            System.out.println("Cliente conectado: " + socketCliente.getInetAddress());

            // Obtener los flujos de entrada y salida
            InputStream inputStream = socketCliente.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader entrada = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socketCliente.getOutputStream();
            PrintWriter salida = new PrintWriter(outputStream, true);

            boolean salir = false;

            while (!salir) {

                // Leer la opción seleccionada por el cliente
                String opcion = entrada.readLine();

                if (opcion == null) {

                    break; // Cliente desconectado

                }

                switch (opcion) {

                    case "consultarISBN" -> {

                        String isbn = entrada.readLine(); // Leer ISBN enviado por el cliente
                        salida.println(Libro.consultarPorISBN(isbn)); // Enviar resultado

                    }
                    case "consultarTitulo" -> {

                        String titulo = entrada.readLine(); // Leer título enviado por el cliente
                        salida.println(Libro.consultarPorTitulo(titulo)); // Enviar resultado

                    }
                    case "consultarAutor" -> {

                        String autor = entrada.readLine(); // Leer autor enviado por el cliente
                        salida.println(Libro.consultarPorAutor(autor)); // Enviar resultado

                    }
                    case "agregarLibro" -> {

                        // Leer cada dato enviado por el cliente
                        String isbn = entrada.readLine();
                        String titulo = entrada.readLine();
                        String autor = entrada.readLine();
                        String precioStr = entrada.readLine();

                        if (isbn != null && titulo != null && autor != null && precioStr != null) {

                            try {

                                double precio = Double.parseDouble(precioStr);
                                Libro nuevoLibro = new Libro(isbn, titulo, autor, precio);

                                // Agregar el libro de manera segura
                                synchronized (Libro.class) {

                                    Libro.agregarLibro(nuevoLibro);

                                }

                                salida.println("Libro agregado exitosamente.");

                            } catch (NumberFormatException e) {

                                salida.println("Precio inválido. No se pudo agregar el libro.");

                            }

                        } else {

                            salida.println("Datos del libro incompletos. No se pudo agregar el libro.");

                        }

                    }

                    case "salir" -> {

                        salida.println("Desconectando. ¡Hasta luego!");
                        salir = true;

                    }

                    default -> {

                        // Opción inválida
                        salida.println("Opción no válida. Por favor, elige una opción del menú.");

                    }

                }

            }

        } catch (IOException e) {

            System.out.println("Error al manejar cliente: " + e.getMessage());

        } finally {

            // Cerrar la conexión con el cliente
            try {

                if (socketCliente != null) {

                    socketCliente.close();
                    System.out.println("Conexión con el cliente cerrada.");

                }

            } catch (IOException e) {

                System.out.println("Error al cerrar la conexión del cliente: " + e.getMessage());

            }

        }

    }

}
