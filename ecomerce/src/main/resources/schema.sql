-- Crear tabla paises
CREATE TABLE paises (
    pais_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    dominio CHAR(2) NOT NULL UNIQUE
);

-- Crear tabla clientes
CREATE TABLE clientes (
    cliente_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    telefono VARCHAR(10) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(100),
    cp CHAR(5),
    ciudad VARCHAR(50),
    pais INT,
    CONSTRAINT fk_pais FOREIGN KEY (pais) REFERENCES paises(pais_id)
);

-- Crear tabla productos
CREATE TABLE productos (
    producto_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT,
    foto VARCHAR(255),
    precio FLOAT NOT NULL,
    cantidad INT NOT NULL
);

-- Crear tabla ventas
CREATE TABLE ventas (
    venta_id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha TIMESTAMP NOT NULL,
    monto FLOAT NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id)
);

-- Crear tabla articulos_x_venta
CREATE TABLE articulos_x_venta (
    articulo_id SERIAL PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    CONSTRAINT fk_venta FOREIGN KEY (venta_id) REFERENCES ventas(venta_id),
    CONSTRAINT fk_producto FOREIGN KEY (producto_id) REFERENCES productos(producto_id)
);



-- Insertar datos en la tabla paises
INSERT INTO paises (nombre, dominio) VALUES ('México', 'MX'), ('Estados Unidos', 'US');

-- Insertar datos en la tabla clientes
INSERT INTO clientes (nombre, apellidos, telefono, email, direccion, cp, ciudad, pais)
VALUES ('Juan', 'Perez', '5551234567', 'juan.perez@example.com', 'Calle Falsa 123', '12345', 'Ciudad de México', 1);

-- Insertar datos en la tabla productos
INSERT INTO productos (nombre, descripcion, foto, precio, cantidad)
VALUES ('Producto A', 'Descripción del producto A', 'ruta/a/la/fotoA.jpg', 19.99, 100);

-- Insertar datos en la tabla ventas
INSERT INTO ventas (cliente_id, fecha, monto)
VALUES (1, '2024-06-04 12:00:00', 39.98);

-- Insertar datos en la tabla articulos_x_venta
INSERT INTO articulos_x_venta (venta_id, producto_id, cantidad)
VALUES (1, 1, 2);



-- Consultar todos los clientes
SELECT * FROM clientes;

-- Consultar todas las ventas
SELECT * FROM ventas;

-- Consultar todos los productos
SELECT * FROM productos;

-- Consultar todos los artículos por venta
SELECT * FROM articulos_x_venta;