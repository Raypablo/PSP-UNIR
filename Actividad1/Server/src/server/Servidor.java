package server;

import model.Libro;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        //Inicializar libros
        Libro.inicializarLibros();

        //Mostramos mensajes informativos en la consola de Servidor
        System.out.println("APLICACIÓN DE SERVIDOR");
        System.out.println("----------------------------------");

        ServerSocket serverSocket = null;

        try {

            //Creamo el ServerSocket en el puerto 8080
            serverSocket = new ServerSocket(8080);
            System.out.println("Servidor escuchando en el puerto 8080...");

        } catch (IOException e) {

            // Manejar el error de creación del ServerSocket
            System.out.println("Error al iniciar el servidor en el puerto 8080: " + e.getMessage());
            return; // Terminar el programa si no se puede crear el ServerSocket

        }

        // bucle para manejar las conexiones de los clientes
        while (true) {

            try {

                // Aceptar conexión con un cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                // Crear un nuevo hilo para manejar el cliente
                HiloEscuchador hilo = new HiloEscuchador(clientSocket); //Creamos la instancia hilo
                Thread thread = new Thread(hilo); //Asignamos la instancia a un hilo
                thread.start(); //Iniciamos el hilo

            } catch (IOException e) {

                System.out.println("Error al aceptar conexión de cliente: " + e.getMessage());

            }

        }
    }
}
