-- ============================================
-- CREACIÓN DE BASE DE DATOS
-- ============================================
CREATE DATABASE academia;
GO

USE academia;
GO

-- ============================================
-- TABLA DE USUARIOS
-- ============================================
CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    apellido NVARCHAR(100) NOT NULL,
    rol CHAR(1) CHECK (rol IN ('a', 'p')) NOT NULL  -- 'a' = alumno, 'p' = profesor
);
GO

-- ============================================
-- TABLA DE ASIGNATURAS
-- ============================================
CREATE TABLE asignaturas (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    curso VARCHAR(10) CHECK (curso IN ('1º', '2º')) NOT NULL,
    id_user_profesor INT NOT NULL,
    FOREIGN KEY (id_user_profesor) REFERENCES users(id)
);
GO

-- ============================================
-- TABLA DE NOTAS
-- ============================================
CREATE TABLE notas (
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_user_alumno INT NOT NULL,      -- alumno que recibe la nota
    id_user_profesor INT NOT NULL,    -- profesor que pone la nota
    id_asignatura INT NOT NULL,       -- asignatura evaluada
    puntuacion DECIMAL(4,2) CHECK (puntuacion BETWEEN 0 AND 10) NOT NULL,
    fecha_registro DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_user_alumno) REFERENCES users(id),
    FOREIGN KEY (id_user_profesor) REFERENCES users(id),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id)
);
GO

-- ============================================
-- TABLA HISTÓRICA DE NOTAS
-- ============================================
CREATE TABLE historico_notas (
    id_historico INT IDENTITY(1,1) PRIMARY KEY,
    id_nota INT NOT NULL,
    id_asignatura INT NOT NULL,
    id_user_alumno INT NOT NULL,
    id_user_profesor INT NOT NULL,
    puntuacion_anterior DECIMAL(4,2) NULL,
    puntuacion_nueva DECIMAL(4,2) NULL,
    fecha_modificacion DATETIME DEFAULT GETDATE(),
    tipo_cambio VARCHAR(20) NOT NULL,  -- INSERT, UPDATE, DELETE
    motivo NVARCHAR(255) NULL,
    FOREIGN KEY (id_nota) REFERENCES notas(id),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id),
    FOREIGN KEY (id_user_alumno) REFERENCES users(id),
    FOREIGN KEY (id_user_profesor) REFERENCES users(id)
);
GO

-- ============================================
-- TRIGGERS DE CONTROL HISTÓRICO
-- ============================================

-- Trigger INSERT
CREATE TRIGGER trg_notas_insert
ON notas
AFTER INSERT
AS
BEGIN
    INSERT INTO historico_notas (id_nota, id_asignatura, id_user_alumno, id_user_profesor, puntuacion_nueva, tipo_cambio)
    SELECT i.id, i.id_asignatura, i.id_user_alumno, i.id_user_profesor, i.puntuacion, 'INSERT'
    FROM inserted i;
END;
GO

-- Trigger UPDATE
CREATE TRIGGER trg_notas_update
ON notas
AFTER UPDATE
AS
BEGIN
    INSERT INTO historico_notas (id_nota, id_asignatura, id_user_alumno, id_user_profesor,
                                 puntuacion_anterior, puntuacion_nueva, tipo_cambio)
    SELECT d.id, d.id_asignatura, d.id_user_alumno, d.id_user_profesor, d.puntuacion, i.puntuacion, 'UPDATE'
    FROM deleted d
    INNER JOIN inserted i ON d.id = i.id;
END;
GO

-- Trigger DELETE
CREATE TRIGGER trg_notas_delete
ON notas
AFTER DELETE
AS
BEGIN
    INSERT INTO historico_notas (id_nota, id_asignatura, id_user_alumno, id_user_profesor,
                                 puntuacion_anterior, tipo_cambio)
    SELECT d.id, d.id_asignatura, d.id_user_alumno, d.id_user_profesor, d.puntuacion, 'DELETE'
    FROM deleted d;
END;
GO

-- ============================================
-- DATOS DE EJEMPLO
-- ============================================

-- Profesores
INSERT INTO users (nombre, apellido, rol) VALUES 
('Luis', 'Fernandez', 'p'),
('Marta', 'Gómez', 'p');

-- Alumnos
INSERT INTO users (nombre, apellido, rol) VALUES
('Ana', 'Lopez', 'a'),
('Carlos', 'Ruiz', 'a'),
('Elena', 'Martinez', 'a');

-- Asignaturas (impartidas por los profesores)
INSERT INTO asignaturas (nombre, curso, id_user_profesor) VALUES
('Matemáticas', '1º', 1),
('Lengua', '1º', 2),
('Historia', '2º', 1);

-- Notas iniciales (profesores ponen las notas a alumnos)
INSERT INTO notas (id_user_alumno, id_user_profesor, id_asignatura, puntuacion) VALUES
(1, 1, 1, 8.50),  -- Ana en Matemáticas (Luis)
(2, 1, 1, 6.75),  -- Carlos en Matemáticas (Luis)
(3, 2, 2, 9.20),  -- Elena en Lengua (Marta)
(1, 2, 2, 7.00),  -- Ana en Lengua (Marta)
(2, 1, 3, 5.00);  -- Carlos en Historia (Luis)
GO

-- ============================================
-- PRUEBAS DE FUNCIONAMIENTO
-- ============================================

-- 1️⃣ Consulta general de notas
SELECT * FROM notas;

-- 2️⃣ Actualizar una nota (esto generará un registro histórico)
UPDATE notas SET puntuacion = 9.00 WHERE id = 2;

-- 3️⃣ Eliminar una nota (también genera histórico)
DELETE FROM notas WHERE id = 5;

-- 4️⃣ Consultar histórico
SELECT * FROM historico_notas;
GO
