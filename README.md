# Examen ALP – Sistema de Gestión Académica

**Autor:** Dereck Paredes

Aplicación de consola en Java que administra estudiantes y sus notas, con persistencia en un archivo de texto (`datos/estudiantes.txt`).

## Funcionalidades
1. Listar estudiantes con sus notas, promedio y estado (APROBADO / REPROBADO).
2. Registrar un estudiante (código único, 3 notas entre 0 y 10).
3. Buscar por código, nombre o apellido.
4. Actualizar notas.
5. Eliminar estudiante.
6. Estadísticas: promedio general, aprobados, reprobados y mejor estudiante.
7. Guardar en archivo (también se guarda automáticamente al salir).

## Estructura
```
Examen-ALP-Paredes-Dereck/
|-- README.md
|-- src/
|   |-- Main.java              # Menú de consola y lectura validada
|   |-- Estudiante.java        # Modelo: datos, promedio y estado
|   |-- GestionAcademica.java  # Lógica: CRUD y estadísticas
|   `-- GestorArchivos.java    # Lectura/escritura de estudiantes.txt
|-- datos/
|   `-- estudiantes.txt
|-- docs/
|   |-- Analisis.md
|   `-- CasosDePrueba.md
`-- evidencias/
    `-- Ejecucion.png
```

## Requisitos
- JDK 17 o superior (usa `switch` con flechas).

## Compilar y ejecutar
Desde la carpeta raíz del proyecto:

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

> Se debe ejecutar desde la raíz para que el programa encuentre `datos/estudiantes.txt`.

## Formato del archivo
Una línea por estudiante, separada por `;`:
```
codigo;nombre;apellido;nota1;nota2;nota3
```
Las líneas vacías o que empiezan con `#` se ignoran; las líneas mal formadas se omiten con un aviso.
