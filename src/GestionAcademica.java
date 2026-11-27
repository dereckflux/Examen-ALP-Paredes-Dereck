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

    /**
     * Agrega los estudiantes recuperados del archivo sin duplicar codigos.
     * @return cantidad de estudiantes agregados
     */
    public int agregarRecuperados(ArrayList<Estudiante> recuperados) {
        int agregados = 0;
        for (Estudiante e : recuperados) {
            if (registrar(e)) {
                agregados++;
            } else {
                System.out.println("Aviso: codigo repetido en el archivo, se omite " + e.getCodigo());
            }
        }
        return agregados;
    }

    // ------------------------------------------------------------------
    // Indicadores academicos
    // ------------------------------------------------------------------

    public int contarAprobados() {
        int contador = 0;
        for (Estudiante e : estudiantes) {
            if (e.estaAprobado()) {
                contador++;
            }
        }
        return contador;
    }

    public int contarReprobados() {
        return estudiantes.size() - contarAprobados();
    }

    /** Promedio general del curso. Devuelve 0 si no hay estudiantes (evita dividir entre cero). */
    public double calcularPromedioGeneral() {
        if (estudiantes.isEmpty()) {
            return 0.0;
        }
        double acumulador = 0.0;
        for (Estudiante e : estudiantes) {
            acumulador += e.calcularPromedio();
        }
        return acumulador / estudiantes.size();
    }

    /** Porcentaje de aprobados. Devuelve 0 si no hay estudiantes. */
    public double calcularPorcentajeAprobados() {
        if (estudiantes.isEmpty()) {
            return 0.0;
        }
        return contarAprobados() * 100.0 / estudiantes.size();
    }

    /** Estudiante con el promedio mas alto; en empate, el primero registrado. */
    public Estudiante obtenerMayorPromedio() {
        if (estudiantes.isEmpty()) {
            return null;
        }
        Estudiante mayor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).calcularPromedio() > mayor.calcularPromedio()) {
                mayor = estudiantes.get(i);
            }
        }
        return mayor;
    }

    /** Estudiante con el promedio mas bajo; en empate, el primero registrado. */
    public Estudiante obtenerMenorPromedio() {
        if (estudiantes.isEmpty()) {
            return null;
        }
        Estudiante menor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).calcularPromedio() < menor.calcularPromedio()) {
                menor = estudiantes.get(i);
            }
        }
        return menor;
    }

    /** Estudiantes cuyo promedio es inferior al promedio general del curso. */
    public ArrayList<Estudiante> obtenerBajoPromedioGeneral() {
        ArrayList<Estudiante> resultado = new ArrayList<>();
        double promedioGeneral = calcularPromedioGeneral();
        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() < promedioGeneral) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    /**
     * Devuelve una copia de la lista ordenada de mayor a menor promedio
     * (metodo de la burbuja). La lista original conserva el orden de registro.
     */
    public ArrayList<Estudiante> ordenarPorPromedio() {
        ArrayList<Estudiante> copia = new ArrayList<>(estudiantes);
        int n = copia.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (copia.get(j).calcularPromedio() < copia.get(j + 1).calcularPromedio()) {
                    Estudiante temporal = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temporal);
                }
            }
        }
        return copia;
    }
}
