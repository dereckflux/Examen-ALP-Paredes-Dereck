/**
 * Sistema AulaLogic - punto de entrada.
 * (Estructura inicial: solo muestra el menu.)
 */
public class Main {

    public static void main(String[] args) {
        mostrarMenu();
    }

    private static void mostrarMenu() {
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
}
