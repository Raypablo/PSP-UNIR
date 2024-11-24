import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.TreeMap;

public class HiloEscuchador implements Runnable {

    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;
    //Variable del TreeMap
    private TreeMap<String, Producto> productos;

    //Constructor que recibe el socket del cliente y la referencia al TreeMap de productos
    public HiloEscuchador(Socket cliente, TreeMap<String, Producto> productos) {

        numCliente++;

        // Creamos un hilo con un nombre único basado en el número de cliente
        hilo = new Thread(this, "Cliente" + numCliente);

        this.enchufeAlCliente = cliente; //Asociamos el socket del cliente
        this.productos = productos; //Asociamos el TreeMap de productos

        hilo.start();//Iniciamos el hilo

    }

    @Override
    public void run() {

        System.out.println("Estableciendo comunicación con " + hilo.getName());

        try {

            InputStream entrada = enchufeAlCliente.getInputStream(); // Para leer datos enviados por el cliente
            OutputStream salida = enchufeAlCliente.getOutputStream(); // Para enviar datos al cliente
            String texto = "";

            while (!texto.trim().equals("FIN")) {

                byte[] mensaje = new byte[100];
                // Leemos la cantidad de bytes reales
                int bytesLeidos = entrada.read(mensaje);
                // Procesamos solo los bytes leidos y eliminanos los espacios
                texto = new String(mensaje, 0, bytesLeidos).trim();
                // Como el TreeMap tiene los codigo en mayusculas, lo convertimos si o si a mayus para mas comodidad
                texto = texto.toUpperCase();

                if (texto.trim().equals("FIN")) {

                    salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
                    System.out.println(hilo.getName() + " ha cerrado la comunicación");

                } else {

                    Producto producto = productos.get(texto);

                    if (producto != null) {

                        // Enviamos los detalles del producto al cliente
                        String respuesta = String.format("Artículo: %s, Stock: %d, Precio: %.2f €",
                                producto.getNombre(),
                                producto.getStock(),
                                producto.getPrecio());

                        salida.write(respuesta.getBytes());

                    } else {

                        // Enviamos un mensaje de artículo no encontrado
                        salida.write("Artículo no encontrado".getBytes());
                    }
                }
            }

            entrada.close();
            salida.close();
            enchufeAlCliente.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }
    }
}