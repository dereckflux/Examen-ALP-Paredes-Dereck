/**
 * Representa a un estudiante: codigo, nombre y sus tres calificaciones.
 * El promedio, la nota maxima, la minima y el estado se calculan
 * siempre a partir de las notas (no se almacenan).
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

    /**
     * Crea un estudiante. Copia el arreglo de notas para proteger el encapsulamiento.
     * @throws IllegalArgumentException si no hay 3 notas o alguna esta fuera de rango
     */
    public Estudiante(String codigo, String nombre, int[] notas) {
        if (notas == null || notas.length != NUMERO_NOTAS) {
            throw new IllegalArgumentException("se requieren " + NUMERO_NOTAS + " notas");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = new int[NUMERO_NOTAS];
        for (int i = 0; i < NUMERO_NOTAS; i++) {
            if (!esNotaValida(notas[i])) {
                throw new IllegalArgumentException("nota fuera de rango: " + notas[i]);
            }
            this.notas[i] = notas[i];
        }
    }

    /** Verifica que una nota este entre 0 y 20. */
    public static boolean esNotaValida(int nota) {
        return nota >= NOTA_MINIMA && nota <= NOTA_MAXIMA;
    }

    /** Promedio real de las tres notas (sin division entera). */
    public double calcularPromedio() {
        int suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        return suma / (double) notas.length;
    }

    public int obtenerNotaMaxima() {
        int maxima = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > maxima) {
                maxima = notas[i];
            }
        }
        return maxima;
    }

    public int obtenerNotaMinima() {
        int minima = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < minima) {
                minima = notas[i];
            }
        }
        return minima;
    }

    public boolean estaAprobado() {
        return calcularPromedio() >= LIMITE_APROBACION;
    }

    public String obtenerEstado() {
        if (estaAprobado()) {
            return "APROBADO";
        } else {
            return "REPROBADO";
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    /** Devuelve una copia de las notas. */
    public int[] getNotas() {
        int[] copia = new int[notas.length];
        for (int i = 0; i < notas.length; i++) {
            copia[i] = notas[i];
        }
        return copia;
    }

    public int getNota(int posicion) {
        return notas[posicion];
    }

    /** Linea con el formato obligatorio: E001;Ana Torres;15;18;12 */
    public String toLineaArchivo() {
        return codigo + ";" + nombre + ";" + notas[0] + ";" + notas[1] + ";" + notas[2];
    }
}
