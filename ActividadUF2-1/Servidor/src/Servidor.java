import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;

public class Servidor {

    public static void main(String[] args) {

        // Mensaje inicial en la consola Servidor para informar que esta arrancando
        System.out.println("APLICACIÓN DE SERVIDOR MULTITAREA");
        System.out.println("----------------------------------");

        try {

            ServerSocket servidor = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("127.0.0.1",2000); //Configuramos la dirección IP y puerto donde escuchará el servidor
            servidor.bind(direccion); //Asocia el ServerSocket con esta dirección y puerto

            System.out.println("Servidor listo para aceptar solicitudes");
            System.out.println("Dirección IP: " + direccion.getAddress());

            // Creamos un TreeMap para almacenar los productos.
            TreeMap<String, Producto> productos = new TreeMap<String, Producto>();

            // Inicializar el TreeMap con productos predefinidos
            productos.put("PL",new Producto("Peras limoneras", 14, 5f));
            productos.put("PC",new Producto("Peras conferencia", 12, 7f));
            productos.put("PN",new Producto("Plátano canario", 5, 2.5f));
            productos.put("BN",new Producto("Bananas", 7, 1.3f));
            productos.put("TP",new Producto("Tomates tipo pera", 8, 1.7f));
            productos.put("TR",new Producto("Tomates Raf", 7, 5.3f));
            productos.put("UN",new Producto("Uvas negras", 8, 3.2f));
            productos.put("UB",new Producto("Uvas blancas", 5, 2.7f));
            productos.put("PT",new Producto("Picotas", 8, 4.3f));
            productos.put("CR",new Producto("Ciruelas rojas", 10, 2.8f));
            productos.put("MR",new Producto("Melocotones rojos", 3, 2.5f));
            productos.put("MA",new Producto("Melocotones amarillos", 4, 3.2f));


            while (true) {

                Socket enchufeAlCliente = servidor.accept(); // Esperar una conexión entrante de un cliente
                System.out.println("Comunicación entrante");

                //Crear un nuevo hilo para manejar esta conexión y pasamos los productos para que el hilo pueda interactuar con ellos
                new HiloEscuchador(enchufeAlCliente, productos);

            }

        } catch (IOException e) {

            // Captura y muestra errores relacionados con la entrada/salida
            System.out.println(e.getMessage());

        }
    }
}