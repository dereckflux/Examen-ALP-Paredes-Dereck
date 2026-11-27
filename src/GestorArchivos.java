import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Guarda y lee los registros de estudiantes en un archivo de texto.
 * Formato obligatorio por linea: E001;Ana Torres;15;18;12
 */
public class GestorArchivos {

    private static final String SEPARADOR = ";";
    private static final int CAMPOS_POR_LINEA = 5;

    private String rutaArchivo;

    public GestorArchivos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Reescribe el archivo completo con la lista actual (sin modo append),
     * por lo que nunca se duplican registros.
     * Escritura con FileWriter y BufferedWriter.
     */
    public boolean guardar(ArrayList<Estudiante> estudiantes) {
        File archivo = new File(rutaArchivo);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, false))) {
            for (Estudiante e : estudiantes) {
                escritor.write(e.toLineaArchivo());
                escritor.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lee el archivo y reconstruye los objetos Estudiante.
     * Lectura con FileReader y BufferedReader. Si el archivo no existe
     * devuelve una lista vacia; las lineas invalidas se ignoran.
     */
    public ArrayList<Estudiante> leer() {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }
                try {
                    estudiantes.add(convertirLinea(linea));
                } catch (IllegalArgumentException e) {
                    System.out.println("Aviso: linea " + numeroLinea + " ignorada (" + e.getMessage() + ").");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Aviso: no existe el archivo " + rutaArchivo + ". Se inicia sin registros.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return estudiantes;
    }

    /** Convierte una linea del archivo en un objeto Estudiante. */
    private Estudiante convertirLinea(String linea) {
        String[] partes = linea.split(SEPARADOR);
        if (partes.length != CAMPOS_POR_LINEA) {
            throw new IllegalArgumentException("se esperaban " + CAMPOS_POR_LINEA + " campos");
        }
        String codigo = partes[0].trim();
        String nombre = partes[1].trim();
        if (codigo.isEmpty() || nombre.isEmpty()) {
            throw new IllegalArgumentException("codigo o nombre vacio");
        }
        int[] notas = new int[Estudiante.NUMERO_NOTAS];
        try {
            for (int i = 0; i < notas.length; i++) {
                notas[i] = Integer.parseInt(partes[i + 2].trim());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("calificacion no numerica");
        }
        return new Estudiante(codigo, nombre, notas);
    }
}
