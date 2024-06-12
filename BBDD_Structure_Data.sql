drop database if exists CageTech;
create database if not exists CageTech;
use CageTech;

create table if not exists Users (
	email varchar(255) primary key,
    nombre varchar(255) not null,
    apellidos varchar(355) not null,
    contrasena varchar(255) not null
);

create table if not exists Ejercicios (
	id int auto_increment primary key,
    us_email varchar(255) not null,
    nombre varchar(255) not null,
    series int not null,
    repeticiones int not null,
    peso float not null,
    
    foreign key (us_email) references Users(email)
);

create table if not exists ArtesMarciales (
	id int auto_increment primary key,
    us_email varchar(255) not null,
    nombre varchar(255) not null,
    calentamiento time not null,
    tecnica time not null,
    sparring time not null,
    num_sparring int not null,
    
    foreign key (us_email) references Users(email)
);

insert into Users values
('user01@example.com', 'User', '01', 'pass01'), 
('user02@example.com', 'User', '02', 'pass02');

insert into Ejercicios(us_email, nombre, series, repeticiones, peso) values
('user01@example.com', 'Sentadilla', 3, 12, 100.0),
('user01@example.com', 'Zancadas', 4, 10, 50.0), 
('user01@example.com', 'Levantamiento de pesas', 4, 12, 20.0);

insert into ArtesMarciales(us_email, nombre, calentamiento, tecnica, sparring, num_sparring) values
('user02@example.com', 'BJJ', '00:10:00', '00:30:00', '00:03:00', 3),
('user02@example.com', 'MMA', '00:10:00', '00:30:00', '00:03:00', 3),
('user02@example.com', 'Judo', '00:10:00', '00:30:00', '00:06:00', 6);