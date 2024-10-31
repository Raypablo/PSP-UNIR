import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        ProcessBuilder proceso = new ProcessBuilder("C:/Windows/System32/calc.exe");

        try{

            Process p = proceso.start();
            p.waitFor(); //Espera hasta que el usuario cierre el proceso
            System.out.println("proceso lanzado");

        } catch (IOException | InterruptedException e) {

            System.out.println(e.getMessage());

        }
    }
}
