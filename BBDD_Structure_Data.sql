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
    rutina_id int not null,
    nombre varchar(255) not null,
    series int not null,
    repeticiones int not null,
    peso float not null,
    
    foreign key (rutina_id) references Rutinas(id)
);

create table if not exists ArtesMarciales (
	id int auto_increment primary key,
    rutina_id int not null,
    nombre varchar(255) not null,
    calentamiento time not null,
    tecnica time not null,
    sparring time not null,
    num_sparring int not null,
    
    foreign key (rutina_id) references Rutinas(id)
);

insert into Users(email, nombre, apellidos, contrasena) values
('user01@example.com', 'User', '01', 'pass01'), 
('user02@example.com', 'User', '02', 'pass02');

insert into Rutinas(nombre, us_email) values
('Rutina 1', 'user01@example.com'),
('Rutina 2', 'user02@example.com');

insert into Ejercicios(rutina_id, nombre, series, repeticiones, peso) values
(1, 'Sentadilla', 3, 12, 100.0),
(1, 'Zancadas', 4, 10, 50.0), 
(1, 'Levantamiento de pesas', 4, 12, 20.0);

insert into ArtesMarciales(rutina_id, nombre, calentamiento, tecnica, sparring, num_sparring) values
(2, 'BJJ', '00:10:00', '00:30:00', '00:03:00', 3),
(2, 'MMA', '00:10:00', '00:30:00', '00:03:00', 3),
(2, 'Judo', '00:10:00', '00:30:00', '00:06:00', 6);