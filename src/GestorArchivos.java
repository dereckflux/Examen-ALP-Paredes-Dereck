import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lectura y escritura del archivo de texto de estudiantes.
 * Formato por línea: codigo;nombre;apellido;nota1;nota2;nota3
 * Las líneas vacías o que empiezan con '#' se ignoran.
 */
public class GestorArchivos {

    private final String ruta;

    public GestorArchivos(String ruta) {
        this.ruta = ruta;
    }

    public List<Estudiante> cargar() {
        List<Estudiante> lista = new ArrayList<>();
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("[Aviso] No existe " + ruta + ". Se inicia con lista vacía.");
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numero = 0;
            while ((linea = br.readLine()) != null) {
                numero++;
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                try {
                    lista.add(parsear(linea));
                } catch (IllegalArgumentException ex) {
                    System.out.println("[Aviso] Línea " + numero + " ignorada: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("[Error] No se pudo leer el archivo: " + ex.getMessage());
        }
        return lista;
    }

    public boolean guardar(List<Estudiante> lista) {
        File archivo = new File(ruta);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write("# codigo;nombre;apellido;nota1;nota2;nota3");
            bw.newLine();
            for (Estudiante e : lista) {
                bw.write(e.toLineaArchivo());
                bw.newLine();
            }
            return true;
        } catch (IOException ex) {
            System.out.println("[Error] No se pudo guardar el archivo: " + ex.getMessage());
            return false;
        }
    }

    private Estudiante parsear(String linea) {
        String[] p = linea.split(";");
        if (p.length != 6) {
            throw new IllegalArgumentException("se esperaban 6 campos y hay " + p.length);
        }
        try {
            return new Estudiante(p[0].trim(), p[1].trim(), p[2].trim(),
                    Double.parseDouble(p[3].trim()),
                    Double.parseDouble(p[4].trim()),
                    Double.parseDouble(p[5].trim()));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("nota no numérica");
        }
    }
}
