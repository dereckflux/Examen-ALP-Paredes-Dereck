import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada: menú de consola del sistema de gestión académica.
 * Ejecutar desde la carpeta raíz del proyecto para que encuentre datos/estudiantes.txt
 */
public class Main {

    private static final String RUTA_DATOS = "datos/estudiantes.txt";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestorArchivos archivos = new GestorArchivos(RUTA_DATOS);
        GestionAcademica gestion = new GestionAcademica(archivos.cargar());
        System.out.println("Se cargaron " + gestion.getEstudiantes().size() + " estudiantes.");

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> listar(gestion.getEstudiantes());
                case 2 -> registrar(gestion);
                case 3 -> buscar(gestion);
                case 4 -> actualizar(gestion);
                case 5 -> eliminar(gestion);
                case 6 -> estadisticas(gestion);
                case 7 -> System.out.println(archivos.guardar(gestion.getEstudiantes())
                        ? "Datos guardados en " + RUTA_DATOS : "No se pudo guardar.");
                case 0 -> {
                    archivos.guardar(gestion.getEstudiantes());
                    System.out.println("Datos guardados. ¡Hasta luego!");
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== GESTIÓN ACADÉMICA =====");
        System.out.println("1. Listar estudiantes");
        System.out.println("2. Registrar estudiante");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Actualizar notas");
        System.out.println("5. Eliminar estudiante");
        System.out.println("6. Estadísticas");
        System.out.println("7. Guardar en archivo");
        System.out.println("0. Salir (guarda automáticamente)");
    }

    private static void listar(List<Estudiante> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.printf("%-6s %-12s %-12s %5s %5s %5s%n", "COD", "NOMBRE", "APELLIDO", "N1", "N2", "N3");
        for (Estudiante e : lista) {
            System.out.println(e);
        }
    }

    private static void registrar(GestionAcademica g) {
        String codigo = leerTexto("Código: ");
        if (g.buscarPorCodigo(codigo) != null) {
            System.out.println("Ya existe un estudiante con ese código.");
            return;
        }
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        double n1 = leerNota("Nota 1: ");
        double n2 = leerNota("Nota 2: ");
        double n3 = leerNota("Nota 3: ");
        g.agregar(new Estudiante(codigo, nombre, apellido, n1, n2, n3));
        System.out.println("Estudiante registrado.");
    }

    private static void buscar(GestionAcademica g) {
        String texto = leerTexto("Código, nombre o apellido: ");
        Estudiante porCodigo = g.buscarPorCodigo(texto);
        if (porCodigo != null) {
            System.out.println(porCodigo);
            return;
        }
        List<Estudiante> encontrados = g.buscarPorNombre(texto);
        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
        } else {
            listar(encontrados);
        }
    }

    private static void actualizar(GestionAcademica g) {
        String codigo = leerTexto("Código del estudiante: ");
        if (g.buscarPorCodigo(codigo) == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }
        double n1 = leerNota("Nueva nota 1: ");
        double n2 = leerNota("Nueva nota 2: ");
        double n3 = leerNota("Nueva nota 3: ");
        g.actualizarNotas(codigo, n1, n2, n3);
        System.out.println("Notas actualizadas: " + g.buscarPorCodigo(codigo));
    }

    private static void eliminar(GestionAcademica g) {
        String codigo = leerTexto("Código del estudiante: ");
        System.out.println(g.eliminar(codigo) ? "Estudiante eliminado." : "Estudiante no encontrado.");
    }

    private static void estadisticas(GestionAcademica g) {
        if (g.getEstudiantes().isEmpty()) {
            System.out.println("No hay datos para calcular estadísticas.");
            return;
        }
        System.out.printf("Total de estudiantes: %d%n", g.getEstudiantes().size());
        System.out.printf("Promedio general:     %.2f%n", g.promedioGeneral());
        System.out.printf("Aprobados:            %d%n", g.contarAprobados());
        System.out.printf("Reprobados:           %d%n", g.contarReprobados());
        System.out.println("Mejor estudiante:     " + g.mejorEstudiante());
    }

    // ---------- Lectura validada de datos ----------

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!sc.hasNextLine()) {
                System.exit(0);
            }
            String s = sc.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println("El valor no puede estar vacío.");
            } else if (s.contains(";")) {
                System.out.println("El valor no puede contener ';'.");
            } else {
                return s;
            }
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            String s = leerTexto(mensaje);
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero.");
            }
        }
    }

    private static double leerNota(String mensaje) {
        while (true) {
            String s = leerTexto(mensaje).replace(',', '.');
            try {
                double n = Double.parseDouble(s);
                if (Estudiante.notaValida(n)) {
                    return n;
                }
                System.out.println("La nota debe estar entre 0 y 10.");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}
