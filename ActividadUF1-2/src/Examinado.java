import java.util.Random;

public class Examinado implements Runnable {

    private Thread hilo;
    BufferExamenes buffer;

    public Thread getHilo() {

        return hilo;

    }

    public Examinado(String alumno, BufferExamenes generador) {

        // Construye el hilo. El nombre será el nombre del alumno.
        this.hilo = new Thread(this, alumno); // Crea el hilo con el nombre del alumno.
        this.buffer = generador; // Establece el valor de la propiedad buffer

        hilo.start(); // Inicia el hilo.

    }

    @Override

    public synchronized void run() {

        String codigoExamen = this.buffer.consumirExamen();

        if (codigoExamen != null) {

            Random random = new Random();
            String[] respuestas = {"A", "B", "C", "D", "-"}; // Posibles respuestas.

            // Simula 10 preguntas en el examen.
            for (int i = 1; i <= 10; i++) {

                String respuesta = respuestas[random.nextInt(respuestas.length)]; // Selecciona una respuesta aleatoria.
                System.out.println(codigoExamen + ";" + hilo.getName() + "; Pregunta " + i + ";" + respuesta);

            }

        }else {

            System.out.println("Agotado tiempo de espera y no hay más exámenes");

        }

    }

}
