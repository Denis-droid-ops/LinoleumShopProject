DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS orders CASCADE ;
DROP TABLE IF EXISTS addresses CASCADE ;
DROP TABLE IF EXISTS layouts CASCADE ;
DROP TABLE IF EXISTS fragments CASCADE ;
DROP TABLE IF EXISTS linoleums CASCADE ;
DROP TABLE IF EXISTS rolls CASCADE ;

CREATE TABLE users
(
   id     IDENTITY PRIMARY KEY,
   name   VARCHAR(124) NOT NULL,
   email  VARCHAR(124) NOT NULL UNIQUE,
   password VARCHAR(50) NOT NULL,
   phone_number BIGINT NOT NULL UNIQUE ,
   role VARCHAR(20) NOT NULL

);

CREATE TABLE layouts
(
    id     IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE fragments
(
    id     IDENTITY PRIMARY KEY,
    width  FLOAT NOT NULL ,
    length FLOAT NOT NULL,
    layout_id INTEGER NOT NULL,
    FOREIGN KEY (layout_id) REFERENCES layouts(id) ON DELETE CASCADE
);

CREATE TABLE linoleums
(
    id     IDENTITY PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    protect FLOAT4 NOT NULL,
    thickness FLOAT4 NOT NULL,
    price INTEGER NOT NULL,
    image_path VARCHAR(100) NOT NULL
);

CREATE TABLE addresses
(
    id     IDENTITY PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    home_num VARCHAR(20) NOT NULL,
    apartment_num INTEGER NOT NULL,
    layout_id INTEGER NOT NULL,
    FOREIGN KEY (layout_id) REFERENCES layouts(id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id     IDENTITY PRIMARY KEY,
    creating_date TIMESTAMP  DEFAULT now() NOT NULL,
    status VARCHAR(20) NOT NULL,
    transporting VARCHAR(20) NOT NULL,
    transporting_date TIMESTAMP NOT NULL,
    cost INTEGER NOT NULL ,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    linoleum_id INTEGER NOT NULL,
    FOREIGN KEY (linoleum_id) REFERENCES linoleums(id) ON DELETE CASCADE,
    address_id INTEGER NOT NULL,
    FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE
);


CREATE TABLE rolls
(
    id     IDENTITY PRIMARY KEY,
    part_num  INTEGER NOT NULL ,
    width FLOAT NOT NULL,
    length FLOAT NOT NULL,
    linoleum_id INTEGER NOT NULL,
    FOREIGN KEY (linoleum_id) REFERENCES linoleums(id) ON DELETE CASCADE
);