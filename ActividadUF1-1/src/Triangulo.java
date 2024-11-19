import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Triangulo {

    public static void main(String[] args){

        if (args.length == 0){

            System.out.println("Se requiere un argumento");
            return;

        }

        // Obtener la fecha y hora de inicio
        LocalDateTime inicio = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Fecha de inicio del proceso: " + inicio.format(formatter));
        System.out.println();

        int filas = Integer.parseInt(args[0]);

        for (int i=filas; i>=1; i--){

            for (int n=1; n<=i; n++){


                System.out.print(n);

            }

            System.out.println();

        }

        // Obtener la fecha y hora de finalización
        LocalDateTime fin = LocalDateTime.now();
        System.out.println();
        System.out.println("Fecha de fin del proceso: " + fin.format(formatter));

    }
}