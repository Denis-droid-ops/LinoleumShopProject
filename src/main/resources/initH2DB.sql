DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS orders CASCADE ;
DROP TABLE IF EXISTS orders_with_layout CASCADE ;
DROP TABLE IF EXISTS orders_with_delivery_address CASCADE ;
DROP TABLE IF EXISTS layouts CASCADE ;
DROP TABLE IF EXISTS layouts_names CASCADE ;
DROP TABLE IF EXISTS fragments CASCADE ;
DROP TABLE IF EXISTS linoleums CASCADE ;
DROP TABLE IF EXISTS delivery_addresses CASCADE ;
DROP TABLE IF EXISTS fragments_without_layout CASCADE ;
DROP TABLE IF EXISTS fragments_orders CASCADE ;
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

CREATE INDEX password_idx ON users(password);

CREATE TABLE layouts_names
(
    id     IDENTITY PRIMARY KEY,
    ln_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE layouts
(
    id    IDENTITY PRIMARY KEY,
    city VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    home_num VARCHAR(10) NOT NULL,
    room_count INTEGER NOT NULL,
    row_type VARCHAR NOT NULL,
    l_type VARCHAR(50) NOT NULL,
    layout_name_id INTEGER NOT NULL,
    FOREIGN KEY (layout_name_id) REFERENCES layouts_names(id) ON DELETE CASCADE
);

CREATE INDEX layouts_name_id_idx ON layouts(layout_name_id);
CREATE INDEX l_type_idx ON layouts(l_type);

CREATE TABLE fragments
(
    id     IDENTITY PRIMARY KEY,
    width  FLOAT4 NOT NULL ,
    length FLOAT4 NOT NULL,
    f_type VARCHAR(30) NOT NULL,
    layout_name_id INTEGER NOT NULL,
    FOREIGN KEY (layout_name_id) REFERENCES layouts_names(id) ON DELETE CASCADE

);


CREATE TABLE linoleums
(
    id     IDENTITY PRIMARY KEY,
    l_name  VARCHAR(50) NOT NULL,
    protect FLOAT4 NOT NULL,
    thickness FLOAT4 NOT NULL,
    price INTEGER NOT NULL,
    image_path VARCHAR(100) NOT NULL
);

CREATE TABLE delivery_addresses
(
    id     IDENTITY PRIMARY KEY,
    d_city VARCHAR(50) NOT NULL,
    d_street VARCHAR(50) NOT NULL,
    d_home_num VARCHAR(10) NOT NULL
);

CREATE TABLE orders
(
    id     IDENTITY PRIMARY KEY,
    creating_date TIMESTAMP  NOT NULL,
    status VARCHAR(20) NOT NULL,
    transporting VARCHAR(20) NOT NULL,
    transporting_date TIMESTAMP NOT NULL,
    cost INTEGER NOT NULL ,
    apartment_num INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    linoleum_id INTEGER NOT NULL,
    FOREIGN KEY (linoleum_id) REFERENCES linoleums(id) ON DELETE CASCADE

);

CREATE INDEX user_id_idx ON orders(user_id);
CREATE INDEX linoleum_id_idx ON orders(linoleum_id);

CREATE TABLE orders_with_layout
(
    id  INTEGER PRIMARY KEY REFERENCES orders(id) ON DELETE CASCADE ,
    layout_id INTEGER,
    FOREIGN KEY (layout_id) REFERENCES layouts(id) ON DELETE CASCADE
);

CREATE INDEX layout_id_idx ON orders_with_layout(layout_id);

CREATE TABLE orders_with_delivery_address
(
    id  INTEGER PRIMARY KEY REFERENCES orders(id) ON DELETE CASCADE ,
    delivery_address_id INTEGER,
    FOREIGN KEY (delivery_address_id) REFERENCES delivery_addresses(id) ON DELETE CASCADE
);

CREATE INDEX delivery_address_id_idx ON orders_with_delivery_address(delivery_address_id);

CREATE TABLE fragments_without_layout
(
    id     IDENTITY PRIMARY KEY,
    f_width  FLOAT4 NOT NULL ,
    f_length FLOAT4 NOT NULL,
    order_id INTEGER,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

CREATE TABLE fragments_orders
(
    id     IDENTITY PRIMARY KEY,
    fragment_id  INTEGER ,
    FOREIGN KEY (fragment_id) REFERENCES fragments(id) ON DELETE CASCADE,
    order_id INTEGER,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

CREATE INDEX order_id_idx ON fragments_orders(order_id);

CREATE TABLE rolls
(
    id     IDENTITY PRIMARY KEY,
    part_num  INTEGER NOT NULL ,
    r_width FLOAT NOT NULL,
    r_length FLOAT NOT NULL,
    is_remain BOOLEAN NOT NULL,
    linoleum_id INTEGER NOT NULL,
    FOREIGN KEY (linoleum_id) REFERENCES linoleums(id) ON DELETE CASCADE
);