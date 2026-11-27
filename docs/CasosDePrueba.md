# Matriz de casos de prueba – Sistema AulaLogic

**Estudiante:** Dereck Paredes · **Variante:** D (promedio mínimo de aprobación = 14)  
**Datos iniciales** (`datos/estudiantes.txt`):

| Código | Nombre | N1 | N2 | N3 | Promedio | Estado |
|--------|--------|----|----|----|----------|--------|
| E001 | Carla Mena | 16 | 18 | 17 | 17,00 | APROBADO |
| E002 | Jorge Villacis | 10 | 12 | 11 | 11,00 | REPROBADO |
| E003 | Paula Sanchez | 13 | 14 | 15 | **14,00** (= límite) | APROBADO |
| E004 | Diego Naranjo | 20 | 19 | 18 | 19,00 | APROBADO |
| E005 | Sofia Lopez | 9 | 13 | 14 | 12,00 | REPROBADO |

## Casos obligatorios

| Caso | Situación | Entrada | Resultado esperado | Resultado obtenido | Estado | Evidencia |
|------|-----------|---------|--------------------|--------------------|--------|-----------|
| CP01 | Registro correcto de un estudiante | Opción 1 → `E006`, `Luis Vera`, 15, 16, 17 | Se registra; promedio 16,00, máx. 17, mín. 15, APROBADO | "Estudiante registrado correctamente." Promedio 16,00, máx. 17, mín. 15, APROBADO | ✅ Aprobado | `CP01-registro-correcto.png` |
| CP02 | Código duplicado | Opción 1 → `E001` | Mensaje de error y regreso al menú sin registrar | "Error: el codigo E001 ya esta registrado." Se guardan 5 registros (no se duplica) | ✅ Aprobado | `CP02-codigo-duplicado.png` |
| CP03 | Calificación fuera de rango | Opción 1 → `E007`, `Maria Lopez`, nota 1: 25, -1, abc, 12; nota 2: 21, 10; nota 3: 8 | Se rechazan 25, -1, abc y 21 y se vuelven a pedir; se registra con 12, 10, 8 | "Calificacion fuera de rango. Debe estar entre 0 y 20." (25, -1, 21) y "Debe ingresar un numero entero." (abc). Registrado: promedio 10,00, REPROBADO | ✅ Aprobado | `CP03-nota-fuera-de-rango.png` |
| CP04 | Promedio igual al límite (14) | Opción 1 → `E008`, `Pedro Ortiz`, 12, 14, 16; opción 3 → `E003` (13, 14, 15) | Promedio 14,00 → APROBADO (se usa `>=`) | E008: promedio 14,00, APROBADO. E003: promedio 14,00, APROBADO | ✅ Aprobado | `CP04-promedio-igual-al-limite.png` |
| CP05 | Búsqueda de estudiante existente | Opción 3 → `E004` | Muestra código, nombre, notas, promedio, máx., mín. y estado | E004, Diego Naranjo, 20-19-18, promedio 19,00, máx. 20, mín. 18, APROBADO | ✅ Aprobado | `CP05-busqueda-existente.png` |
| CP06 | Búsqueda de estudiante inexistente | Opción 3 → `E999` | Mensaje informativo | "No existe un estudiante con el codigo E999." | ✅ Aprobado | `CP06-busqueda-inexistente.png` |
| CP07 | Reporte sin estudiantes | Sin archivo `datos/estudiantes.txt`; opción 4 | Aviso de archivo inexistente; reporte sin división entre cero | "Aviso: no existe el archivo…" y "No existen estudiantes registrados. No se puede generar el reporte." | ✅ Aprobado | `CP07-reporte-sin-estudiantes.png` |
| CP08 | Guardado y recuperación al reiniciar | Ejecución 1: registrar `E006` (15, 16, 17) y salir (6). Ejecución 2: opciones 2 y 3 → `E006` | El archivo queda con 6 líneas; al reiniciar se recuperan 6 registros y E006 se encuentra con su promedio recalculado | Ejecución 1: "Registros guardados … (6)". Ejecución 2: "Registros recuperados …: 6"; E006 promedio 16,00, APROBADO. Archivo sin duplicados | ✅ Aprobado | `CP08a-guardado.png`, `CP08b-recuperacion.png` |

## Pruebas adicionales

| Caso | Situación | Entrada | Resultado obtenido | Estado |
|------|-----------|---------|--------------------|--------|
| PA01 | Opción de menú no numérica / fuera de rango | `abc`, `9` | "Opcion incorrecta. Seleccione un numero del 1 al 6." y el menú se repite | ✅ Aprobado |
| PA02 | Código o nombre vacío o con `;` | `""`, `E;01`, `Ana;Gil` | "El dato no puede estar vacio." / "El dato no puede contener punto y coma (;)." | ✅ Aprobado |
| PA03 | Reporte académico con los datos iniciales | Opción 4 | Total 5, aprobados 3, reprobados 2, promedio general 14,60, 60,00 % aprobados, más alto E004 (19,00), más bajo E002 (11,00), bajo el promedio: E002, E003, E005; orden: E004, E001, E003, E005, E002 | ✅ Aprobado |

> El reporte de PA03 coincide con el cálculo manual de `docs/Analisis.md` (73 / 5 = 14,60; 3 / 5 = 60 %).  
> Las capturas de pantalla de cada caso obligatorio están en `evidencias/` (tomadas durante la ejecución del programa en la terminal de PowerShell). `evidencias/Ejecucion.png` muestra el listado, el reporte académico (PA03) y la salida del programa.  
> Antes de cada caso se restauró `datos/estudiantes.txt` a los 5 registros iniciales, excepto en CP08b, que usa el archivo guardado en CP08a.
