import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema AulaLogic - menu principal y flujo del programa.
 */
public class Main {

    private static final String RUTA_ARCHIVO = "datos/estudiantes.txt";
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        GestionAcademica gestion = new GestionAcademica();
        GestorArchivos gestorArchivos = new GestorArchivos(RUTA_ARCHIVO);

        // Recuperacion automatica al iniciar
        int recuperados = gestion.agregarRecuperados(gestorArchivos.leer());
        System.out.println("Registros recuperados de " + RUTA_ARCHIVO + ": " + recuperados);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    registrarEstudiante(gestion);
                    break;
                case 2:
                    listarEstudiantes(gestion);
                    break;
                case 3:
                    buscarEstudiante(gestion);
                    break;
                case 4:
                    generarReporte(gestion);
                    break;
                case 5:
                    guardarRegistros(gestion, gestorArchivos);
                    break;
                case 6:
                    guardarRegistros(gestion, gestorArchivos);
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero del 1 al 6.");
            }
        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=====================================");
        System.out.println("        SISTEMA AULALOGIC");
        System.out.println("=====================================");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Generar reporte academico");
        System.out.println("5. Guardar registros");
        System.out.println("6. Salir");
        System.out.println("=====================================");
        System.out.print("Seleccione una opcion: ");
    }

    // ------------------------------------------------------------------
    // Opciones del menu
    // ------------------------------------------------------------------

    private static void registrarEstudiante(GestionAcademica gestion) {
        System.out.println("--- Registro de estudiante ---");
        String codigo = leerTextoValido("Codigo: ");
        if (gestion.existeCodigo(codigo)) {
            System.out.println("Error: el codigo " + codigo + " ya esta registrado.");
            return;
        }
        String nombre = leerTextoValido("Nombre completo: ");

        int[] notas = new int[Estudiante.NUMERO_NOTAS];
        for (int i = 0; i < notas.length; i++) {
            notas[i] = leerNota(i + 1);
        }

        Estudiante nuevo = new Estudiante(codigo, nombre, notas);
        gestion.registrar(nuevo);
        System.out.println("Estudiante registrado correctamente.");
        mostrarDetalle(nuevo);
    }

    private static void listarEstudiantes(GestionAcademica gestion) {
        if (gestion.estaVacia()) {
            System.out.println("No existen estudiantes registrados.");
            return;
        }
        imprimirTabla(gestion.getEstudiantes());
    }

    private static void buscarEstudiante(GestionAcademica gestion) {
        String codigo = leerTextoValido("Ingrese el codigo a buscar: ");
        Estudiante encontrado = gestion.buscarPorCodigo(codigo);
        if (encontrado == null) {
            System.out.println("No existe un estudiante con el codigo " + codigo + ".");
        } else {
            mostrarDetalle(encontrado);
        }
    }

    private static void generarReporte(GestionAcademica gestion) {
        System.out.println("========= REPORTE ACADEMICO =========");
        if (gestion.estaVacia()) {
            System.out.println("No existen estudiantes registrados. No se puede generar el reporte.");
            return;
        }
        System.out.println("Limite de aprobacion (Variante D): " + Estudiante.LIMITE_APROBACION);
        System.out.println("Total de estudiantes:  " + gestion.getCantidad());
        System.out.println("Aprobados:             " + gestion.contarAprobados());
        System.out.println("Reprobados:            " + gestion.contarReprobados());
        System.out.printf("Promedio general:      %.2f%n", gestion.calcularPromedioGeneral());
        System.out.printf("Porcentaje aprobados:  %.2f %%%n", gestion.calcularPorcentajeAprobados());

        Estudiante mayor = gestion.obtenerMayorPromedio();
        Estudiante menor = gestion.obtenerMenorPromedio();
        System.out.printf("Promedio mas alto:     %s - %s (%.2f)%n",
                mayor.getCodigo(), mayor.getNombre(), mayor.calcularPromedio());
        System.out.printf("Promedio mas bajo:     %s - %s (%.2f)%n",
                menor.getCodigo(), menor.getNombre(), menor.calcularPromedio());

        System.out.println("--- Estudiantes bajo el promedio general ---");
        ArrayList<Estudiante> bajos = gestion.obtenerBajoPromedioGeneral();
        if (bajos.isEmpty()) {
            System.out.println("Ninguno.");
        } else {
            for (Estudiante e : bajos) {
                System.out.printf("%s - %s (%.2f)%n", e.getCodigo(), e.getNombre(), e.calcularPromedio());
            }
        }

        System.out.println("--- Listado ordenado de mayor a menor promedio ---");
        imprimirTabla(gestion.ordenarPorPromedio());
    }

    private static void guardarRegistros(GestionAcademica gestion, GestorArchivos gestorArchivos) {
        if (gestorArchivos.guardar(gestion.getEstudiantes())) {
            System.out.println("Registros guardados en " + RUTA_ARCHIVO + " (" + gestion.getCantidad() + ").");
        } else {
            System.out.println("No se pudieron guardar los registros.");
        }
    }

    // ------------------------------------------------------------------
    // Presentacion
    // ------------------------------------------------------------------

    private static void mostrarDetalle(Estudiante e) {
        System.out.println("Codigo:        " + e.getCodigo());
        System.out.println("Nombre:        " + e.getNombre());
        System.out.println("Calificaciones: " + e.getNota(0) + " - " + e.getNota(1) + " - " + e.getNota(2));
        System.out.printf("Promedio:      %.2f%n", e.calcularPromedio());
        System.out.println("Nota maxima:   " + e.obtenerNotaMaxima());
        System.out.println("Nota minima:   " + e.obtenerNotaMinima());
        System.out.println("Estado:        " + e.obtenerEstado());
    }

    private static void imprimirTabla(ArrayList<Estudiante> lista) {
        System.out.printf("%-6s %-20s %4s %4s %4s %8s %4s %4s  %s%n",
                "COD", "NOMBRE", "N1", "N2", "N3", "PROM", "MAX", "MIN", "ESTADO");
        for (Estudiante e : lista) {
            System.out.printf("%-6s %-20s %4d %4d %4d %8.2f %4d %4d  %s%n",
                    e.getCodigo(), e.getNombre(), e.getNota(0), e.getNota(1), e.getNota(2),
                    e.calcularPromedio(), e.obtenerNotaMaxima(), e.obtenerNotaMinima(),
                    e.obtenerEstado());
        }
    }

    // ------------------------------------------------------------------
    // Lectura y validacion de datos
    // ------------------------------------------------------------------

    private static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        if (!teclado.hasNextLine()) {
            System.out.println();
            System.out.println("Fin de la entrada de datos.");
            System.exit(0);
        }
        return teclado.nextLine().trim();
    }

    /** Lee un texto que no este vacio ni contenga punto y coma. */
    private static String leerTextoValido(String mensaje) {
        String texto;
        boolean valido;
        do {
            texto = leerLinea(mensaje);
            valido = true;
            if (texto.isEmpty()) {
                System.out.println("El dato no puede estar vacio.");
                valido = false;
            } else if (texto.contains(";")) {
                System.out.println("El dato no puede contener punto y coma (;).");
                valido = false;
            }
        } while (!valido);
        return texto;
    }

    /** Devuelve la opcion ingresada, o 0 si no es un numero entero. */
    private static int leerOpcion() {
        String texto = leerLinea("");
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /** Solicita una calificacion hasta que sea un entero entre 0 y 20. */
    private static int leerNota(int numero) {
        while (true) {
            String texto = leerLinea("Calificacion " + numero + " (0-20): ");
            try {
                int nota = Integer.parseInt(texto);
                if (Estudiante.esNotaValida(nota)) {
                    return nota;
                }
                System.out.println("Calificacion fuera de rango. Debe estar entre "
                        + Estudiante.NOTA_MINIMA + " y " + Estudiante.NOTA_MAXIMA + ".");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero.");
            }
        }
    }
}
