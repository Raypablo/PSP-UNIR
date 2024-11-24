import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 2000);

             OutputStream salida = socket.getOutputStream();
             InputStream entrada = socket.getInputStream();

             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor. Escribe un código de artículo o 'FIN' para salir.");
            String mensaje = "";

            while (!mensaje.equalsIgnoreCase("FIN")) {

                System.out.print("Código de artículo: ");
                mensaje = scanner.nextLine();
                salida.write(mensaje.getBytes());
                //Inserto flush para que el mensaje se envíe inmediatamente
                salida.flush();

                byte[] respuesta = new byte[100];
                entrada.read(respuesta);

                System.out.println("Respuesta del servidor: " + new String(respuesta).trim());
            }

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());

        }
    }
}
