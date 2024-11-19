import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LanzadorTriangulo {

    public static void main(String[] args) {

        try {

            // Obtener el classpath actual
            String classpath = System.getProperty("java.class.path");

            // Crear ProcessBuilder para Triangulo con parámetro 5
            ProcessBuilder triangulo5 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "5");
            triangulo5.redirectOutput(new File("triangulo5.txt"));

            // Crear ProcessBuilder para Triangulo con parámetro 7
            ProcessBuilder triangulo7 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "7");
            triangulo7.redirectOutput(new File("triangulo7.txt"));

            // Crear ProcessBuilder para Triangulo con parámetro 9
            ProcessBuilder triangulo9 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "9");
            triangulo9.redirectOutput(new File("triangulo9.txt"));

            // Iniciar los procesos y esperar a que los procesos terminen
            triangulo5.start().waitFor();
            triangulo7.start().waitFor();
            triangulo9.start().waitFor();

            System.out.println("Todos los procesos han finalizado.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
