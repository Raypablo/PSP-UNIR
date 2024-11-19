import java.time.LocalDateTime;

public class ProductorExamenes implements Runnable {

    private BufferExamenes buffer;
    private static int numeroExamen = 0;
    private Thread hilo;

    public ProductorExamenes(BufferExamenes buffer) {

        numeroExamen++; // Incrementa el contador de exámenes.

        this.hilo = new Thread(this, "E" + numeroExamen); // Construye el hilo. El nombre será la letra E seguida del valor de la variable numeroExamen.
        this.buffer = buffer; // Establece el valor de la propiedad buffer

        hilo.start(); // Inicia el hilo.

    }

    @Override

    public void run() {

        int aa = LocalDateTime.now().getYear();

        // Generación del código de examen.
        String codigo = this.hilo.getName() + "-" +aa;
        buffer.fabricarNuevoExamen(codigo); // Añade el examen al buffer.

    }

}