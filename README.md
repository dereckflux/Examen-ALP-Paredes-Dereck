# Examen-ALP-Paredes-Dereck – Sistema AulaLogic

Sistema de seguimiento del rendimiento académico en Java (consola).

## Identificación del estudiante
| Campo | Detalle |
|-------|---------|
| Estudiante | Dereck Paredes |
| Universidad | Universidad Técnica de Ambato |
| Facultad | Ingeniería en Sistemas, Electrónica e Industrial |
| Carrera | Ingeniería de Software |
| Asignatura | Algoritmos y Lógica de Programación |
| Nivel / modalidad | Primero / Individual |
| Evaluación | Examen práctico integrador |
| Variante asignada | **D – promedio mínimo de aprobación: 14** |

## Descripción del problema
La Universidad Técnica de Ambato necesita un sistema que permita registrar, consultar, procesar y almacenar las calificaciones de los estudiantes de una asignatura. El sistema funciona por consola, conserva los registros en `datos/estudiantes.txt` después de finalizar la ejecución y genera indicadores del rendimiento académico del curso.

Cada estudiante tiene un código, un nombre completo y tres calificaciones enteras de 0 a 20. Con la **variante D**, un estudiante está **APROBADO** si su promedio es **mayor o igual a 14**; en caso contrario está **REPROBADO**.

El análisis completo (entrada, proceso, salida, diseño de clases, pseudocódigo y prueba de escritorio) está en [`docs/Analisis.md`](docs/Analisis.md).

## Estructura del repositorio
```
Examen-ALP-Paredes-Dereck/
|-- README.md
|-- src/
|   |-- Main.java              # Menú, captura y validación de datos, flujo principal
|   |-- Estudiante.java        # codigo, nombre, int[] notas; promedio, máx., mín., estado
|   |-- GestionAcademica.java  # ArrayList<Estudiante>; registro, búsqueda, indicadores, orden
|   `-- GestorArchivos.java    # FileWriter/BufferedWriter y FileReader/BufferedReader
|-- datos/
|   `-- estudiantes.txt        # Datos de prueba propios (incluye un promedio = 14)
|-- docs/
|   |-- Analisis.md
|   `-- CasosDePrueba.md       # Matriz de casos de prueba CP01–CP08
`-- evidencias/
    |-- Ejecucion.png          # Listado y reporte académico
    `-- CP01 … CP08 *.png      # Una captura por caso de prueba
```

## Requisitos
- JDK 11 o superior (probado con JDK 21).

## Compilación y ejecución
Abrir una terminal en la **carpeta raíz del repositorio** (para que el programa encuentre `datos/estudiantes.txt`):

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

En Windows PowerShell funciona el mismo comando. Si se usa un IDE (NetBeans, IntelliJ, VS Code), el directorio de trabajo debe ser la raíz del proyecto.

## Menú principal
```
=====================================
        SISTEMA AULALOGIC
=====================================
1. Registrar estudiante
2. Listar estudiantes
3. Buscar estudiante
4. Generar reporte academico
5. Guardar registros
6. Salir
=====================================
Seleccione una opcion:
```

## Funcionalidades implementadas
- **RF01 – Registro:** código y nombre no vacíos y sin `;`; código no repetido; tres calificaciones enteras entre 0 y 20, que se vuelven a pedir si son incorrectas. Las notas se guardan en un arreglo `int[]` y los estudiantes en un `ArrayList<Estudiante>`.
- **RF02 – Procesamiento:** promedio real (sin división entera), nota máxima, nota mínima y estado APROBADO/REPROBADO según el límite 14.
- **RF03 – Consulta:** búsqueda lineal por código que muestra código, nombre, notas, promedio, nota máxima, nota mínima y estado, o un mensaje si no existe.
- **RF04 – Reporte académico:** total, aprobados, reprobados, promedio general, porcentaje de aprobados, promedio más alto y más bajo (en empate, el primero registrado), estudiantes bajo el promedio general y listado ordenado de mayor a menor promedio (método de la burbuja). Si la lista está vacía se avisa y no se divide entre cero.
- **RF05 – Persistencia:** escritura con `FileWriter` + `BufferedWriter`, lectura con `FileReader` + `BufferedReader`, recuperación automática al iniciar, conversión de notas con `Integer.parseInt`, control con `try-catch`, aviso si el archivo no existe. El archivo se reescribe completo, sin duplicar registros, y los promedios y estados se recalculan desde las notas.
- **Menú:** se repite con `do-while` + `switch` hasta la opción 6; las opciones incorrectas no terminan el programa; antes de salir se guardan los registros.

## Formato del archivo `datos/estudiantes.txt`
```
E001;Carla Mena;16;18;17
E002;Jorge Villacis;10;12;11
E003;Paula Sanchez;13;14;15
```
`codigo;nombre;nota1;nota2;nota3`. Las líneas mal formadas se ignoran con un aviso.

## Casos de prueba
Los ocho casos obligatorios (CP01–CP08) y tres adicionales pasaron. La matriz completa (entrada, resultado esperado, resultado obtenido y estado) está en [`docs/CasosDePrueba.md`](docs/CasosDePrueba.md) y las evidencias en [`evidencias/`](evidencias/).

## Limitaciones
- La búsqueda es solo por código (no por nombre), como pide el enunciado.
- No existen opciones para modificar o eliminar estudiantes; para corregir un registro se debe editar `datos/estudiantes.txt`.
- Los decimales se muestran según la configuración regional del equipo (por ejemplo `14,60` en español).
- Se recomienda no usar tildes ni ñ en los nombres al escribir desde la consola de Windows, porque la codificación de la consola puede alterarlas.

## Control de versiones
El desarrollo se registró en commits progresivos:
1. Análisis y estructura inicial.
2. Implementación de clases y registro.
3. Procesamiento y persistencia.
4. Pruebas y documentación final.
