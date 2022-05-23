drop table if exists asignado;
drop table if exists cientificos;
drop table if exists proyecto;

create table proyectos (
id varchar(3) primary key, 
nombre varchar(255),
horas int);

create table cientificos (
id varchar(3) primary key,
nombre varchar(255));

create table asignado (
id int primary key auto_increment,
cientifico varchar(8),
proyecto varchar(4),
foreign key(cientifico) references cientificos(id)
on delete cascade
on update cascade,
foreign key(proyecto) references proyectos(id)
on delete cascade
on update cascade);

insert into proyectos values
('001', "MSKA", 10),
('002', "Skynet", 10),
('003', "Algarrobas", 10),
('004', "Chuck Norris", 10),
('005', "Stand-Alone", 10),
('006', "secreto", 10);

insert into cientificos values
("001", "Chuck"),
("002", "VanDame"),
("003", "Torrente"),
("004", "Fred Durst"),
("005", "Till Lindemann"),
("006", "Joe Satriany");

insert into asignado (cientifico, proyecto) values
("001", "001"),
("002", "002"),
("003", "003"),
("004", "004"),
("005", "005"),
("006", "006");