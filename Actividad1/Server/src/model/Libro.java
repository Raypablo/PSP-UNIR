package model;
import java.util.ArrayList;
import java.util.List;

public class Libro {

    String isbn;
    String titulo;
    String autor;
    double precio;

    public Libro(String isbn, String titulo, String autor, double precio) {

        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;

    }

    // Inicializamos algunos libro para que el cliente pueda consultar

    private static List<Libro> libros = new ArrayList<>();
    public static void inicializarLibros() {

        libros.add(new Libro("111", "Libro A", "Autor 1", 10.99));
        libros.add(new Libro("222", "Libro B", "Autor 2", 12.99));
        libros.add(new Libro("333", "Libro C", "Autor 1", 9.99));
        libros.add(new Libro("444", "Libro D", "Autor 3", 14.99));
        libros.add(new Libro("555", "Libro E", "Autor 2", 19.99));

    }

    public static String consultarPorISBN(String isbn) {

        for (Libro libro : libros) {

            if (libro.isbn.equals(isbn)) {

                return libro.toString();

            }

        }

        return "Libro no encontrado.";

    }

    public static String consultarPorTitulo(String titulo) {
        for (Libro libro : libros) {

            if (libro.titulo.equalsIgnoreCase(titulo)) {

                return libro.toString();

            }

        }

        return "Libro no encontrado.";

    }

    public static String consultarPorAutor(String autor) {

        StringBuilder resultados = new StringBuilder();

        for (Libro libro : libros) {

            if (libro.autor.equalsIgnoreCase(autor)) {

                resultados.append(libro.toString()).append("\n");

            }

        }

        if (!resultados.isEmpty()) {

            return resultados.toString();

        }else{

            return "Libro no encontrado.";

        }

    }

    public static void agregarLibro(Libro libro) {

        synchronized (Libro.class) {

            libros.add(libro);

        }

    }

    public String toString() {

        return "ISBN: " + isbn + ", Título: " + titulo + ", Autor: " + autor + ", Precio: " + precio;

    }

}
