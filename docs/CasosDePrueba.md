# Casos de prueba

Datos iniciales: los 5 estudiantes de `datos/estudiantes.txt`.

| # | Caso | Entrada | Resultado esperado | Resultado obtenido |
|---|------|---------|--------------------|--------------------|
| 1 | Cargar archivo | Iniciar programa | "Se cargaron 5 estudiantes." | ✅ Correcto |
| 2 | Listar | Opción 1 | Tabla con 5 estudiantes, promedio y estado | ✅ Correcto |
| 3 | Promedio y estado | E001: 8.5, 9.0, 7.5 | Prom: 8.33 – APROBADO | ✅ Correcto |
| 4 | Límite de aprobación | E004: 7, 7, 7 | Prom: 7.00 – APROBADO | ✅ Correcto |
| 5 | Reprobado | E005: 4.5, 6.0, 5.0 | Prom: 5.17 – REPROBADO | ✅ Correcto |
| 6 | Registrar válido | Opción 2: E006, Pedro, Vera, 8, 7.5, 9 | "Estudiante registrado." | ✅ Correcto |
| 7 | Código duplicado | Opción 2: E001 | "Ya existe un estudiante con ese código." | ✅ Correcto |
| 8 | Nota fuera de rango | Nota 1: 11 | "La nota debe estar entre 0 y 10." y vuelve a pedir | ✅ Correcto |
| 9 | Nota no numérica | Nota 1: abc | "Ingrese un número válido." | ✅ Correcto |
| 10 | Nota con coma | Nota 1: 8,5 | Se acepta como 8.5 | ✅ Correcto |
| 11 | Buscar por código | Opción 3: E003 | Muestra a Maria Lopez | ✅ Correcto |
| 12 | Buscar por nombre parcial | Opción 3: lu | Muestra a Luis Gomez | ✅ Correcto |
| 13 | Buscar inexistente | Opción 3: zzz | "No se encontraron coincidencias." | ✅ Correcto |
| 14 | Actualizar notas | Opción 4: E002, 8, 8, 8 | Prom: 8.00 – APROBADO | ✅ Correcto |
| 15 | Eliminar | Opción 5: E005 | "Estudiante eliminado." | ✅ Correcto |
| 16 | Eliminar inexistente | Opción 5: X99 | "Estudiante no encontrado." | ✅ Correcto |
| 17 | Estadísticas | Opción 6 (datos iniciales) | Total 5, promedio general 7.23, aprobados 3, reprobados 2, mejor: Maria Lopez (9.50) | ✅ Correcto |
| 18 | Opción inválida | Opción 9 / texto | "Opción no válida." / "Ingrese un número entero." | ✅ Correcto |
| 19 | Campo vacío | Nombre: (Enter) | "El valor no puede estar vacío." | ✅ Correcto |
| 20 | Persistencia | Registrar, salir (0) y volver a abrir | El nuevo estudiante aparece en el listado | ✅ Correcto |
| 21 | Archivo inexistente | Borrar `datos/estudiantes.txt` e iniciar | Aviso y lista vacía; se crea al guardar | ✅ Correcto |
| 22 | Línea mal formada | Agregar `E9;Juan;8` al archivo | Aviso "Línea N ignorada" y carga el resto | ✅ Correcto |

La evidencia de ejecución se encuentra en `evidencias/Ejecucion.png`.
