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
    email NVARCHAR(255) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    rol CHAR(1) CHECK (rol IN ('a', 'p')) NOT NULL
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
    id_nota INT NULL,
    FOREIGN KEY (id_user_profesor) REFERENCES users(id)
);
GO

-- ============================================
-- TABLA DE NOTAS
-- ============================================
CREATE TABLE notas (
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_user_alumno INT NOT NULL,
    id_user_profesor INT NOT NULL,
    id_asignatura INT NOT NULL,
    puntuacion DECIMAL(4,2) CHECK (puntuacion BETWEEN 0 AND 10) NOT NULL,
    fecha_registro DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_user_alumno) REFERENCES users(id),
    FOREIGN KEY (id_user_profesor) REFERENCES users(id),
    FOREIGN KEY (id_asignatura) REFERENCES asignaturas(id)
);
GO

ALTER TABLE asignaturas
ADD CONSTRAINT FK_asignaturas_notas FOREIGN KEY (id_nota) REFERENCES notas(id);
GO

-- ============================================
-- TABLA HISTÓRICA DE NOTAS
-- ============================================
CREATE TABLE historico_notas (
    id_historico INT IDENTITY(1,1) PRIMARY KEY,
    id_notas INT NULL,
    id_asignatura INT NULL,
    id_user_alumno INT NULL,
    id_user_profesor INT NULL,
    puntuacion_anterior DECIMAL(4,2) NULL,
    puntuacion_nueva DECIMAL(4,2) NULL,
    fecha_modificacion DATETIME DEFAULT GETDATE(),
    tipo_cambio VARCHAR(20) NOT NULL,
    motivo NVARCHAR(255) NULL
);
GO

-- ============================================
-- TRIGGERS DE CONTROL HISTÓRICO
-- ============================================

CREATE TRIGGER trg_notas_insert
ON notas
AFTER INSERT
AS
BEGIN
    INSERT INTO historico_notas (id_notas, id_asignatura, id_user_alumno, id_user_profesor, puntuacion_nueva, tipo_cambio)
    SELECT i.id, i.id_asignatura, i.id_user_alumno, i.id_user_profesor, i.puntuacion, 'INSERT'
    FROM inserted i;
END;
GO

CREATE TRIGGER trg_notas_update
ON notas
AFTER UPDATE
AS
BEGIN
    INSERT INTO historico_notas (id_notas, id_asignatura, id_user_alumno, id_user_profesor,
                                 puntuacion_anterior, puntuacion_nueva, tipo_cambio)
    SELECT d.id, d.id_asignatura, d.id_user_alumno, d.id_user_profesor, d.puntuacion, i.puntuacion, 'UPDATE'
    FROM deleted d
    INNER JOIN inserted i ON d.id = i.id;
END;
GO

CREATE TRIGGER trg_notas_delete
ON notas
AFTER DELETE
AS
BEGIN
    INSERT INTO historico_notas (id_notas, id_asignatura, id_user_alumno, id_user_profesor,
                                 puntuacion_anterior, tipo_cambio)
    SELECT d.id, d.id_asignatura, d.id_user_alumno, d.id_user_profesor, d.puntuacion, 'DELETE'
    FROM deleted d;
END;
GO

-- ============================================
-- DATOS DE EJEMPLO
-- ============================================

INSERT INTO users (nombre, apellido, email, password, rol) VALUES 
('Luis', 'Fernandez', 'luis.fernandez@academia.com', 'pass123', 'p'),
('Marta', 'Gómez', 'marta.gomez@academia.com', 'pass123', 'p'),
('Ana', 'Lopez', 'ana.lopez@academia.com', '1234', 'a'),
('Carlos', 'Ruiz', 'carlos.ruiz@academia.com', 'abcd', 'a'),
('Elena', 'Martinez', 'elena.martinez@academia.com', 'xyz', 'a');
GO

INSERT INTO asignaturas (nombre, curso, id_user_profesor) VALUES
('Matemáticas', '1º', 1),
('Lengua', '1º', 2),
('Historia', '2º', 1);
GO

INSERT INTO notas (id_user_alumno, id_user_profesor, id_asignatura, puntuacion) VALUES
(3, 1, 1, 8.50),
(4, 1, 1, 6.75),
(5, 2, 2, 9.20),
(3, 2, 2, 7.00),
(4, 1, 3, 5.00);
GO

UPDATE asignaturas SET id_nota = 1 WHERE id = 1;
UPDATE asignaturas SET id_nota = 3 WHERE id = 2;
UPDATE asignaturas SET id_nota = 5 WHERE id = 3;
GO
