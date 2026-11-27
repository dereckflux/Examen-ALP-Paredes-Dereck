import java.util.ArrayList;

/**
 * Administra la lista de estudiantes y procesa la informacion.
 */
public class GestionAcademica {

    private ArrayList<Estudiante> estudiantes;

    public GestionAcademica() {
        estudiantes = new ArrayList<>();
    }

    /** Busqueda lineal por codigo. Devuelve null si no existe. */
    public Estudiante buscarPorCodigo(String codigo) {
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante e = estudiantes.get(i);
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        return null;
    }

    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    /** Registra un estudiante si su codigo no esta repetido. */
    public boolean registrar(Estudiante estudiante) {
        if (existeCodigo(estudiante.getCodigo())) {
            return false;
        }
        estudiantes.add(estudiante);
        return true;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public int getCantidad() {
        return estudiantes.size();
    }

    public boolean estaVacia() {
        return estudiantes.isEmpty();
    }
}
