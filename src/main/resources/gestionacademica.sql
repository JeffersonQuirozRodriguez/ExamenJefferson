CREATE DATABASE IF NOT EXISTS bd_gestion_academica;
USE bd_gestion_academica;

-- Tabla Estudiante
INSERT INTO estudiante (nombre, email, matricula) VALUES
('Ana Torres', 'ana.torres@gmail.com', 'MAT2023001'),
('Luis Gómez', 'luis.gomez@gmail.com', 'MAT2023002'),
('Carmen Silva', 'carmen.silva@gmail.com', 'MAT2023003'),
('Ricardo Paredes', 'ricardo.paredes@gmail.com', 'MAT2023004'),
('Laura Quispe', 'laura.quispe@gmail.com', 'MAT2023005');

-- Tabla Profesor
INSERT INTO profesor (nombre, especialidad) VALUES
('Pedro Martínez', 'Matemáticas'),
('Julia Ríos', 'Lenguaje'),
('Carlos Navarro', 'Ciencias'),
('Martha Luján', 'Historia'),
('Fernando Ruiz', 'Computación');

-- Tabla Curso
INSERT INTO curso (nombre, codigo, creditos) VALUES
('Álgebra Lineal', 'MAT101', 4),
('Literatura Peruana', 'LIT202', 3),
('Biología General', 'BIO110', 5),
('Historia Mundial', 'HIS120', 4),
('Programación Java', 'CS301', 6);

-- Relación Curso_Profesor (asignando profesores a cursos)
INSERT INTO curso_profesor (curso_id, profesor_id) VALUES
(1, 1),  -- Álgebra Lineal → Pedro Martínez
(2, 2),  -- Literatura Peruana → Julia Ríos
(3, 3),  -- Biología General → Carlos Navarro
(4, 4),  -- Historia Mundial → Martha Luján
(5, 5),  -- Programación Java → Fernando Ruiz
(5, 3);  -- Programación Java → también Carlos Navarro

-- Inscripciones
INSERT INTO inscripcion (curso_id, estudiante_id, fechaInscripcion, estado) VALUES
(1, 1, '2022-07-01', 'CONFIRMADA'),
(1, 2, '2023-07-02', 'PENDIENTE'),
(2, 1, '2022-07-03', 'CONFIRMADA'),
(2, 3, '2025-07-04', 'CANCELADA'),
(3, 4, '2025-07-05', 'CONFIRMADA'),
(3, 1, '2023-07-06', 'CONFIRMADA'),
(5, 1, '2024-07-07', 'PENDIENTE'),
(5, 2, '2022-07-08', 'CONFIRMADA'),
(5, 3, '2023-07-09', 'CONFIRMADA'),
(5, 4, '2022-07-10', 'CANCELADA'),
(5, 5, '2023-07-11', 'CONFIRMADA');