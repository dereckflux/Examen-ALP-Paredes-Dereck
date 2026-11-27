/**
 * Representa a un estudiante: codigo, nombre y sus tres calificaciones.
 * (Estructura inicial: los metodos se implementan en el siguiente commit.)
 */
public class Estudiante {

    public static final int NUMERO_NOTAS = 3;
    public static final int NOTA_MINIMA = 0;
    public static final int NOTA_MAXIMA = 20;
    // Variante D: promedio minimo de aprobacion
    public static final double LIMITE_APROBACION = 14.0;

    private String codigo;
    private String nombre;
    private int[] notas;

    public Estudiante(String codigo, String nombre, int[] notas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = notas;
    }

    public double calcularPromedio() {
        return 0.0;
    }

    public int obtenerNotaMaxima() {
        return 0;
    }

    public int obtenerNotaMinima() {
        return 0;
    }

    public String obtenerEstado() {
        return "";
    }
}
