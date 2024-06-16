drop database if exists CageTech;
create database if not exists CageTech;
use CageTech;

create table if not exists Users (
	email varchar(255) primary key,
    nombre varchar(255) not null,
    apellidos varchar(355) not null,
    contrasena varchar(255) not null,
    peso float,
    altura float
);

create table if not exists Rutinas(
	id int auto_increment primary key, 
    nombre varchar(255) not null,
    us_email varchar(255) not null,
    
    foreign key (us_email) references Users(email)
);

create table if not exists Ejercicios (
	id int auto_increment primary key,
    nombre varchar(255) not null,
    series int not null,
    repeticiones int not null,
    peso float not null
);

create table if not exists RutinasEjercicios (
    rutina_id int not null,
    ejercicio_id int not null,
    
    primary key (rutina_id, ejercicio_id),

    foreign key (rutina_id) references Rutinas(id),
    foreign key (ejercicio_id) references Ejercicios(id)
);

create table if not exists ArtesMarciales (
	id int auto_increment primary key,
    nombre varchar(255) not null,
    calentamiento time not null,
    tecnica time not null,
    sparring time not null,
    num_sparring int not null
);

create table if not exists RutinasArtesMarciales (
    rutina_id int not null,
    arte_marcial_id int not null,
    
    primary key (rutina_id, arte_marcial_id),
    
    foreign key (rutina_id) references Rutinas(id),
    foreign key (arte_marcial_id) references ArtesMarciales(id)
);

INSERT INTO Users(email, nombre, apellidos, contrasena) VALUES
('user01@example.com', 'User', '01', 'pass01'), 
('user02@example.com', 'User', '02', 'pass02');

-- Insertar datos en la tabla de rutinas
INSERT INTO Rutinas(nombre, us_email) VALUES
('Rutina 1', 'user01@example.com'),
('Rutina 2', 'user02@example.com');

-- Insertar datos en la tabla de ejercicios
INSERT INTO Ejercicios(nombre, series, repeticiones, peso) VALUES
('Sentadilla', 3, 12, 100.0),
('Zancadas', 4, 10, 50.0), 
('Levantamiento de pesas', 4, 12, 20.0);

-- Insertar datos en la tabla intermedia de rutinas y ejercicios
INSERT INTO RutinasEjercicios(rutina_id, ejercicio_id) VALUES
(1, 1),  -- Rutina 1 tiene Sentadilla
(1, 2),  -- Rutina 1 tiene Zancadas
(1, 3);  -- Rutina 1 tiene Levantamiento de pesas

-- Insertar datos en la tabla de artes marciales
INSERT INTO ArtesMarciales(nombre, calentamiento, tecnica, sparring, num_sparring) VALUES
('BJJ', '00:10:00', '00:30:00', '00:03:00', 3),
('MMA', '00:10:00', '00:30:00', '00:03:00', 3),
('Judo', '00:10:00', '00:30:00', '00:06:00', 6);

-- Insertar datos en la tabla intermedia de rutinas y artes marciales
INSERT INTO RutinasArtesMarciales(rutina_id, arte_marcial_id) VALUES
(2, 1),  -- Rutina 2 tiene BJJ
(2, 2),  -- Rutina 2 tiene MMA
(2, 3);  -- Rutina 2 tiene Judo