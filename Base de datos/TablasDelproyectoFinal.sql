USE proyectoFinal;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL
);


CREATE TABLE amigos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    amigo_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (amigo_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE (usuario_id, amigo_id) -- Evita duplicados en la relación de amistad
);



CREATE TABLE juego (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    consola VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    anio_lanzamiento INT NOT NULL,
    precio INT NOT NULL
);

CREATE TABLE lista_deseados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    juego_id INT NOT NULL,
    fecha_agregado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (juego_id) REFERENCES juego(id) ON DELETE CASCADE
);


CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    total DOUBLE,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES users(id)
);

CREATE TABLE pedido_juegos (
    pedido_id INT,
    juego_id INT,
    PRIMARY KEY (pedido_id, juego_id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (juego_id) REFERENCES juego(id)
);

CREATE TABLE historial (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_juego VARCHAR(255),
    fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    precio VARCHAR(50)
);

