create database prueba_db;

use prueba_db;

create table clientes(
  id_cliente bigint primary key auto_increment,
  apellido varchar(255),
  direccion varchar(255),
  email varchar(255),
  fecha_nacimiento varchar(255),
  nombre varchar(255),
  telefono double
);

create table productos(
  id_producto bigint primary key auto_increment,
  nombre varchar(255) not null,
  precio double  not null,
  stock int not null
);

create table facturas(
  num_factura bigint primary key auto_increment,
  fecha varchar(255) not null,
  id_cliente bigint not null,
  FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

create table detalles(
  num_detalle bigint primary key auto_increment,
  cantidad int not null,
  precio double not null,
  id_factura bigint not null,
  id_producto bigint not null,
  FOREIGN KEY (id_factura) REFERENCES factura(num_factura),
  FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Lucia","Hernandez","1981-06-09","lucia.hernandez@gmail.com","cl 1 # 23-41",3145217849);
insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Andres","Arevalo","1970-04-19","andres.arevalo@gmail.com","kr 1 # 23-41",3201544812);
insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Carlos","Castillo","1987-09-14","carlos.castillo@gmail.com","av 1 # 23-41",3154218659);
insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Andrea","Grijalba","1991-02-24","andrea.grijalba@gmail.com","trans 1 # 23-41",3102365984);
insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Amanda","Garmendia","1985-11-09","amanda.garmendia@gmail.com","cl 1 # 23-41",3215487695);
insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Patricia","Ortiz","1984-07-18","patricia.ortiz@gmail.com","cl 1 # 23-41",3124151214);
insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Javier","Joya","1992-06-21","javier.joya@gmail.com","kr 1 # 23-41",3301245876);
insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Luis","Barrera","1971-06-09","luis.barrera@gmail.com","kr 1 # 23-41",3115487651);
insert into clientes(nombre, apellido, fecha_nacimiento, email,direccion,telefono) values("Ruben","Martinez","1981-10-19","ruben.martinez@gmail.com","cl 1 # 23-41",3221458476);

insert into productos(nombre, precio, stock) values("Cuaderno", 2300, 50);
insert into productos(nombre, precio, stock) values("Lapiz", 800, 80);
insert into productos(nombre, precio, stock) values("Regla", 1200, 60);
insert into productos(nombre, precio, stock) values("Maleta", 89900, 10);
insert into productos(nombre, precio, stock) values("Plumones X20", 34900, 20);
insert into productos(nombre, precio, stock) values("Colores X12", 19900, 30);
insert into productos(nombre, precio, stock) values("Borrador", 500, 100);