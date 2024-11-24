import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        // Conectamos con el servidor en localhost (127.0.0.1) y puerto 2000
        try (Socket socket = new Socket("127.0.0.1", 2000);

             OutputStream salida = socket.getOutputStream(); // Configuramos el flujo de salida para enviar datos al servidor
             InputStream entrada = socket.getInputStream(); // Configuramos el flujo de entrada para recibir datos del servidor

             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor. Escribe un código de artículo o 'FIN' para salir.");
            String mensaje = "";

            while (!mensaje.equalsIgnoreCase("FIN")) {

                System.out.print("Código de artículo: ");
                mensaje = scanner.nextLine();
                salida.write(mensaje.getBytes()); // Convertimos el mensaje a bytes y lo enviamos
                salida.flush(); //Inserto flush para que el mensaje se envíe inmediatamente

                // Crear un buffer para recibir la respuesta del servidor
                byte[] respuesta = new byte[100];
                entrada.read(respuesta); // Leemos la respuesta del servidor en el buffer

                System.out.println("Respuesta del servidor: " + new String(respuesta).trim());
            }

        } catch (IOException e) {

            // Manejar errores de entrada/salida
            System.out.println("Error: " + e.getMessage());

        }
    }
}
