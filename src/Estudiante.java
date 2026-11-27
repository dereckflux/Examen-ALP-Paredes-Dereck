/**
 * Representa a un estudiante con sus datos personales y tres notas.
 * Escala de notas: 0 a 10. Se aprueba con promedio >= 7.
 */
public class Estudiante {

    public static final double NOTA_MINIMA = 0.0;
    public static final double NOTA_MAXIMA = 10.0;
    public static final double NOTA_APROBACION = 7.0;

    private String codigo;
    private String nombre;
    private String apellido;
    private double nota1;
    private double nota2;
    private double nota3;

    public Estudiante(String codigo, String nombre, String apellido,
                      double nota1, double nota2, double nota3) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        setNotas(nota1, nota2, nota3);
    }

    public static boolean notaValida(double nota) {
        return nota >= NOTA_MINIMA && nota <= NOTA_MAXIMA;
    }

    public void setNotas(double n1, double n2, double n3) {
        if (!notaValida(n1) || !notaValida(n2) || !notaValida(n3)) {
            throw new IllegalArgumentException("Las notas deben estar entre "
                    + NOTA_MINIMA + " y " + NOTA_MAXIMA);
        }
        this.nota1 = n1;
        this.nota2 = n2;
        this.nota3 = n3;
    }

    public double getPromedio() {
        return (nota1 + nota2 + nota3) / 3.0;
    }

    public boolean estaAprobado() {
        return getPromedio() >= NOTA_APROBACION;
    }

    public String getEstado() {
        return estaAprobado() ? "APROBADO" : "REPROBADO";
    }

    /** Formato de una línea del archivo: codigo;nombre;apellido;nota1;nota2;nota3 */
    public String toLineaArchivo() {
        return codigo + ";" + nombre + ";" + apellido + ";" + nota1 + ";" + nota2 + ";" + nota3;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public double getNota1() { return nota1; }
    public double getNota2() { return nota2; }
    public double getNota3() { return nota3; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    @Override
    public String toString() {
        return String.format("%-6s %-12s %-12s %5.2f %5.2f %5.2f | Prom: %5.2f  %s",
                codigo, nombre, apellido, nota1, nota2, nota3, getPromedio(), getEstado());
    }
}
