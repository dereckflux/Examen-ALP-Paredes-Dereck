# Análisis del problema

## 1. Descripción
Se requiere un programa que permita a un docente registrar estudiantes, administrar sus notas y obtener estadísticas, conservando la información entre ejecuciones mediante un archivo de texto.

## 2. Entradas
| Dato | Tipo | Validación |
|------|------|------------|
| Opción del menú | Entero | 0 – 7 |
| Código | Texto | No vacío, único, sin `;` |
| Nombre / Apellido | Texto | No vacío, sin `;` |
| Nota 1, 2, 3 | Decimal | 0.0 – 10.0 (acepta `,` o `.`) |
| `datos/estudiantes.txt` | Archivo | 6 campos por línea separados por `;` |

## 3. Procesos
- **Promedio** = (nota1 + nota2 + nota3) / 3
- **Estado** = APROBADO si promedio ≥ 7, si no REPROBADO
- **Promedio general** = suma de promedios / número de estudiantes
- **Mejor estudiante** = el de mayor promedio
- Búsqueda lineal por código (exacta) o por nombre/apellido (parcial, sin distinguir mayúsculas).

## 4. Salidas
- Listado tabulado de estudiantes con promedio y estado.
- Mensajes de confirmación o error de cada operación.
- Estadísticas generales.
- Archivo `datos/estudiantes.txt` actualizado.

## 5. Diseño (clases)
| Clase | Responsabilidad |
|-------|-----------------|
| `Estudiante` | Modelo de datos; calcula promedio y estado; valida notas. |
| `GestionAcademica` | Mantiene la lista (`ArrayList`) y realiza agregar, buscar, actualizar, eliminar y estadísticas. |
| `GestorArchivos` | Lee y escribe el archivo de texto; ignora líneas inválidas. |
| `Main` | Menú interactivo y lectura validada desde teclado. |

```
Main ──usa──> GestionAcademica ──contiene──> Estudiante*
  └──usa──> GestorArchivos ──crea/lee──> Estudiante*
```

## 6. Algoritmo principal (pseudocódigo)
```
INICIO
  lista <- GestorArchivos.cargar("datos/estudiantes.txt")
  REPETIR
    mostrar menú
    leer opcion
    SEGÚN opcion
      1: listar
      2: registrar (validar código único y notas)
      3: buscar
      4: actualizar notas
      5: eliminar
      6: mostrar estadísticas
      7: guardar archivo
      0: guardar archivo y salir
  HASTA opcion = 0
FIN
```

## 7. Restricciones y supuestos
- Escala de notas de 0 a 10; nota mínima de aprobación 7.
- Los datos no pueden contener `;` porque es el separador del archivo.
- Si el archivo no existe, se inicia con lista vacía y se crea al guardar.
