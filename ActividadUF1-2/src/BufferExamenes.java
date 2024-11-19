import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {

    private Queue<String> colaExamenes;

    public BufferExamenes() {

        colaExamenes = new LinkedList<String>();

    }

    public synchronized void fabricarNuevoExamen(String codigo) {

        colaExamenes.add(codigo); // Añade el código del examen a la cola.
        System.out.println("Producido examen " + codigo); // Mensaje informativo.
        notifyAll(); // Notifica a los hilos que están esperando consumir un examen.

    }

    public synchronized String consumirExamen() {

        // Espera mientras no haya exámenes disponibles.
        while (colaExamenes.isEmpty()) {
            try {

                wait(); // El hilo espera a que se produzca un examen.

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt(); // Maneja la interrupción.

            }

        }

        // Extrae y retorna el examen de la cola.
        return colaExamenes.poll();

    }

}