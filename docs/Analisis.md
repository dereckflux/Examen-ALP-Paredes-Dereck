# Análisis del problema – Sistema AulaLogic

**Estudiante:** Dereck Paredes  
**Asignatura:** Algoritmos y Lógica de Programación  
**Variante asignada:** D – promedio mínimo de aprobación **14**

## 1. Descripción del problema
La Universidad Técnica de Ambato necesita un programa de consola que permita registrar, consultar, procesar y almacenar las calificaciones de los estudiantes de una asignatura. La información se guarda en `datos/estudiantes.txt` para conservarla entre ejecuciones, y el sistema genera indicadores del rendimiento académico del curso.

## 2. Entrada – Proceso – Salida

### 2.1 Entradas
| Dato | Tipo | Validación |
|------|------|------------|
| Opción del menú | `int` | Entre 1 y 6; si no es número o está fuera de rango se muestra un mensaje y se repite el menú |
| Código del estudiante | `String` | No vacío, sin `;`, no repetido |
| Nombre completo | `String` | No vacío, sin `;` |
| Calificación 1, 2 y 3 | `int` | Entre 0 y 20; si es incorrecta se solicita nuevamente |
| Archivo `datos/estudiantes.txt` | Texto | `codigo;nombre;nota1;nota2;nota3` |

### 2.2 Constantes
| Constante | Valor | Uso |
|-----------|-------|-----|
| `NUMERO_NOTAS` | 3 | Tamaño del arreglo de notas |
| `NOTA_MINIMA` / `NOTA_MAXIMA` | 0 / 20 | Rango válido de calificaciones |
| `LIMITE_APROBACION` | 14.0 | Variante D |
| `RUTA_ARCHIVO` | `datos/estudiantes.txt` | Persistencia |

### 2.3 Procesos
| Proceso | Fórmula / algoritmo |
|---------|---------------------|
| Promedio | `(nota1 + nota2 + nota3) / 3.0` (división real, no entera) |
| Nota máxima / mínima | Recorrido del arreglo con `for` comparando con el mayor/menor actual |
| Estado | `promedio >= 14` → APROBADO, si no → REPROBADO |
| Búsqueda | Búsqueda lineal por código en el `ArrayList` |
| Aprobados / reprobados | Contadores dentro de un recorrido |
| Promedio general | Acumulador de promedios / cantidad (si la lista está vacía devuelve 0, sin dividir entre cero) |
| % de aprobados | `aprobados * 100.0 / total` (solo si total > 0) |
| Mayor / menor promedio | Recorrido con comparación estricta (`>` / `<`): en empate queda el primero registrado |
| Bajo el promedio general | Recorrido que selecciona `promedio < promedioGeneral` |
| Ordenamiento | Método de la burbuja, de mayor a menor promedio, sobre una copia de la lista |

### 2.4 Salidas
- Menú principal y mensajes de confirmación o error.
- Listado de estudiantes con notas, promedio y estado.
- Ficha de un estudiante: código, nombre, notas, promedio, nota máxima, nota mínima y estado.
- Reporte académico: total, aprobados, reprobados, promedio general, % de aprobados, mayor y menor promedio, estudiantes bajo el promedio general y listado ordenado.
- Archivo `datos/estudiantes.txt` actualizado (se reescribe completo, sin duplicados).

## 3. Diseño de clases

```
+---------------------------+        +------------------------------------+
| Main                      |------->| GestionAcademica                   |
|---------------------------|        |------------------------------------|
| menú, lectura y validación|        | - estudiantes: ArrayList<Estudiante>|
| de datos, flujo principal |        | registrar, buscar, existeCodigo,   |
+---------------------------+        | indicadores, ordenarPorPromedio    |
            |                        +------------------------------------+
            |                                        | 1..*
            v                                        v
+---------------------------+        +------------------------------------+
| GestorArchivos            |------->| Estudiante                         |
|---------------------------|  crea  |------------------------------------|
| - rutaArchivo: String     |        | - codigo: String                   |
| guardar(lista), leer()    |        | - nombre: String                   |
+---------------------------+        | - notas: int[]                     |
                                     | calcularPromedio(),                |
                                     | obtenerNotaMaxima(),               |
                                     | obtenerNotaMinima(), obtenerEstado()|
                                     +------------------------------------+
```

| Clase | Responsabilidad |
|-------|-----------------|
| `Estudiante` | Datos de un estudiante (encapsulados) y cálculos sobre sus notas. |
| `GestionAcademica` | Administra el `ArrayList`, valida códigos repetidos, busca, calcula indicadores y ordena. |
| `GestorArchivos` | Escribe con `FileWriter` + `BufferedWriter` y lee con `FileReader` + `BufferedReader`, con `try-catch`. |
| `Main` | Menú `do-while` + `switch`, captura y validación de datos, coordina las demás clases. |

## 4. Algoritmo principal (pseudocódigo)
```
INICIO
  gestion <- nueva GestionAcademica
  archivo <- nuevo GestorArchivos("datos/estudiantes.txt")
  gestion.agregarRecuperados(archivo.leer())      // recuperación automática
  HACER
     mostrar menú
     opcion <- leer entero (si no es número, opcion <- 0)
     SEGÚN opcion
        1: registrar estudiante (validar código, nombre y 3 notas)
        2: listar estudiantes
        3: buscar por código
        4: generar reporte académico (si la lista está vacía, avisar)
        5: guardar registros
        6: guardar registros y salir
        otro: "Opción incorrecta"
  MIENTRAS opcion <> 6
FIN
```

### Registro de un estudiante
```
leer codigo (repetir mientras esté vacío o tenga ';')
SI gestion.existeCodigo(codigo) ENTONCES mostrar "código repetido" y volver al menú
leer nombre (repetir mientras esté vacío o tenga ';')
PARA i <- 0 HASTA 2
   HACER leer nota MIENTRAS nota no sea entero o (nota < 0 o nota > 20)
   notas[i] <- nota
crear Estudiante(codigo, nombre, notas) y agregar al ArrayList
```

## 5. Prueba de escritorio

Estudiante `E003;Paula Sanchez;13;14;15`

| Paso | i | notas[i] | suma | max | min |
|------|---|----------|------|-----|-----|
| Inicio | – | – | 0 | 13 | 13 |
| 1 | 0 | 13 | 13 | 13 | 13 |
| 2 | 1 | 14 | 27 | 14 | 13 |
| 3 | 2 | 15 | 42 | 15 | 13 |

Promedio = 42 / 3.0 = **14.00** → 14.00 >= 14 → **APROBADO** (caso límite CP04).

Promedio general con los datos de prueba: (17 + 11 + 14 + 19 + 12) / 5 = 73 / 5 = **14.60**; aprobados 3 de 5 = **60 %**.

## 6. Persistencia
- Formato obligatorio por línea: `E001;Ana Torres;15;18;12`.
- Al iniciar se leen las líneas, se convierten las notas con `Integer.parseInt` y se crean los objetos; el promedio y el estado se **recalculan** a partir de las notas (no se guardan en el archivo).
- Si el archivo no existe se muestra un aviso y se inicia con la lista vacía (`FileNotFoundException`).
- Las líneas mal formadas o con notas inválidas se ignoran con un aviso; los códigos repetidos en el archivo no se duplican.
- Al guardar se reescribe el archivo completo (`FileWriter` sin modo *append*), por lo que no se duplican registros.
