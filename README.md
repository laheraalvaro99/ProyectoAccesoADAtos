# 🎓 ProyectoAccesoADAtos  
**Proyecto de Dani para la clase de Acceso a Datos**  

---

## 📖 Descripción del Proyecto

El proyecto **Academia** consiste en una plataforma para la gestión y evaluación académica de alumnos.  
Cada **alumno** podrá iniciar sesión y consultar sus calificaciones en las asignaturas que tenga asignadas, mientras que cada **profesor** podrá **crear, modificar y eliminar notas** de los alumnos en sus respectivas materias.  

Todas las modificaciones quedan registradas automáticamente en una **tabla histórica**, lo que permite mantener una trazabilidad completa de los cambios realizados en las calificaciones.

---

## 🧩 Modelo Entidad–Relación

A continuación se muestra un esquema simplificado del modelo de base de datos que describe las relaciones entre las tablas principales:

```
+------------------+
|      users       |
+------------------+
| id (PK)          |
| nombre           |
| apellido         |
| email            |
| password         |
| rol ('a'/'p')    |
+------------------+
        |1
        |        (un profesor imparte varias)
        |N
+----------------------+
|     asignaturas      |
+----------------------+
| id (PK)              |
| nombre               |
| curso ('1º','2º')    |
| id_user_profesor (FK)|
| id_nota (FK, opcional)|
+----------------------+
        |1
        |        (una asignatura tiene muchas)
        |N
+----------------------+
|        notas         |
+----------------------+
| id (PK)              |
| id_user_alumno (FK)  |
| id_user_profesor (FK)|
| id_asignatura (FK)   |
| puntuacion           |
| fecha_registro       |
+----------------------+
        |1
        |        (una nota puede tener muchos registros)
        |N
+----------------------+
|   historico_notas    |
+----------------------+
| id_historico (PK)    |
| id_notas             |
| id_asignatura        |
| id_user_alumno       |
| id_user_profesor     |
| puntuacion_anterior  |
| puntuacion_nueva     |
| fecha_modificacion   |
| tipo_cambio          |
| motivo               |
+----------------------+
```

---

## ⚙️ Instalación y Ejecución

### 🔧 Requisitos previos
- Microsoft SQL Server (2019 o superior recomendado)
- SQL Server Management Studio (SSMS)
- Permisos para crear bases de datos y ejecutar scripts

### 🐾 Pasos para ejecutar el proyecto
1. Clona este repositorio:
   ```bash
   git clone https://github.com/laheraalvaro99/ProyectoAccesoADAtos.git
   ```
2. Abre el archivo SQL principal (`academia.sql`) en **SQL Server Management Studio**.
3. Ejecuta el script completo (F5) para crear:
   - La base de datos `academia`
   - Todas las tablas (`users`, `asignaturas`, `notas`, `historico_notas`)
   - Los triggers de trazabilidad
   - Los datos de ejemplo
4. Comprueba que se ha creado correctamente:
   ```sql
   USE academia;
   SELECT * FROM users;
   SELECT * FROM notas;
   SELECT * FROM historico_notas;
   ```

---

## 💾 Estructura de la Base de Datos

| Tabla | Descripción |
|-------|--------------|
| **users** | Almacena todos los usuarios del sistema (profesores y alumnos). El campo `rol` define el tipo de usuario. |
| **asignaturas** | Representa las materias impartidas por los profesores. Cada asignatura está vinculada a un profesor. |
| **notas** | Contiene las calificaciones asignadas por los profesores a los alumnos. |
| **historico_notas** | Guarda un registro de todos los cambios (inserciones, actualizaciones y eliminaciones) realizados sobre la tabla `notas`. |

---

## 👥 Autores

Proyecto desarrollado por:
- [argudoplomo](https://github.com/argudoplomo)
- [Gosha1030](https://github.com/Gosha1030)
- [jpenalvag04](https://github.com/jpenalvag04)
- [TJRC-Git](https://github.com/TJRC-Git)
- [laheraalvaro99](https://github.com/laheraalvaro99)

---

## 🧠 Futuras Funcionalidades
  
- Añadir recuperación de contraseñas mediante correo electrónico.  
- Incorporar exportación de informes en formato PDF o Excel.    
- Control de roles con permisos granulares (solo lectura, edición, administración).  

---

## 💼 Gestion del proyecto

https://trello.com/invite/b/69007a6f950cb024b8f287c7/ATTIac0bd088778cb33e9b4734ef6bb43907399E16E2/proyecto-acceso-a-datos

## 📖 JavaDoc

El javadoc se encuentra en el zip apidocs. Una vez descomprimido hay que iniciarl el index.html

## 🔑 Licencia

Este proyecto se distribuye bajo la licencia **MIT**, lo que permite su uso y modificación con fines educativos y personales.
