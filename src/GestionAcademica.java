import java.util.ArrayList;
import java.util.List;

/**
 * Lógica de negocio: administra la lista de estudiantes en memoria.
 */
public class GestionAcademica {

    private final List<Estudiante> estudiantes;

    public GestionAcademica(List<Estudiante> iniciales) {
        this.estudiantes = new ArrayList<>(iniciales);
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /** Agrega un estudiante si su código no existe. Devuelve true si se agregó. */
    public boolean agregar(Estudiante e) {
        if (buscarPorCodigo(e.getCodigo()) != null) {
            return false;
        }
        estudiantes.add(e);
        return true;
    }

    public Estudiante buscarPorCodigo(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        return null;
    }

    /** Busca por coincidencia parcial en nombre o apellido (sin distinguir mayúsculas). */
    public List<Estudiante> buscarPorNombre(String texto) {
        List<Estudiante> resultado = new ArrayList<>();
        String t = texto.toLowerCase();
        for (Estudiante e : estudiantes) {
            if (e.getNombre().toLowerCase().contains(t) || e.getApellido().toLowerCase().contains(t)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public boolean actualizarNotas(String codigo, double n1, double n2, double n3) {
        Estudiante e = buscarPorCodigo(codigo);
        if (e == null) {
            return false;
        }
        e.setNotas(n1, n2, n3);
        return true;
    }

    public boolean eliminar(String codigo) {
        Estudiante e = buscarPorCodigo(codigo);
        return e != null && estudiantes.remove(e);
    }

    public double promedioGeneral() {
        if (estudiantes.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Estudiante e : estudiantes) {
            suma += e.getPromedio();
        }
        return suma / estudiantes.size();
    }

    public Estudiante mejorEstudiante() {
        Estudiante mejor = null;
        for (Estudiante e : estudiantes) {
            if (mejor == null || e.getPromedio() > mejor.getPromedio()) {
                mejor = e;
            }
        }
        return mejor;
    }

    public int contarAprobados() {
        int c = 0;
        for (Estudiante e : estudiantes) {
            if (e.estaAprobado()) {
                c++;
            }
        }
        return c;
    }

    public int contarReprobados() {
        return estudiantes.size() - contarAprobados();
    }
}
