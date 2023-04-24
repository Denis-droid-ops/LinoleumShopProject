DELETE FROM users;
DELETE FROM orders;
DELETE FROM addresses;
DELETE FROM layouts;
DELETE FROM fragments;
DELETE FROM linoleums;
DELETE FROM rolls;

INSERT INTO users(name, email, password, phone_number,role)
VALUES ('Denis','denis.denis.kuznecov@mail.ru','222','89179292245','admin'),
       ('Petr','Petrov59@mail.ru','444','89609292245','user');


INSERT INTO layouts(name)
VALUES ('BNCH'),
       ('84s'),
       ('83s');

INSERT INTO fragments(width, length, layout_id)
VALUES (3.5,4.7,1),
       (2.5,4,1),
       (1.5,4,1),
       (3.5,5,1),
       (2.5,6,2),
       (3.5,4,2),
       (3.5,5.2,2),
       (1.5,3,2),
       (2,5,3),
       (4,4.9,3),
       (4,4.2,3),
       (2.5,5,3),
       (3,5,3);

INSERT INTO linoleums(name, protect, thickness, price,image_path)
VALUES ('jane 4',0.5,3.5,850,'src/main/resources/images/alaska-1-300x300.jpg'),
       ('saratoga 4',0.5,3.5,850,'src/main/resources/images/saratoga-4-300x300.jpg'),
       ('duart 1',0.4,2.7,600,'src/main/resources/images/SPENSER-3-300x300.jpg'),
       ('baden 1',0.5,2,710,'src/main/resources/images/BADEN-1-300x300.jpg'),
       ('forest 3',0.4,4.7,800,'src/main/resources/images/saratoga-6-300x300.jpg'),
       ('rigard 4',0.15,2,400,'src/main/resources/images/tango-3-1-300x300.jpg');

INSERT INTO addresses(city, street, home_num, apartment_num, layout_id)
VALUES ('Nizhnekamsk','Korabelnaya',4,36,1),
       ('Nizhnekamsk','Tukaya',17,1,1),
       ('Nizhnekamsk','Korabelnaya',14,12,1),
       ('Nizhnekamsk','Stroiteley',1,33,1),
       ('Nizhnekamsk','Junosti',3,36,2),
       ('Nizhnekamsk','Mira',44,32,2),
       ('Nizhnekamsk','Mira',12,45,2),
       ('Nizhnekamsk','Korabelnaya',25,77,2),
       ('Nizhnekamsk','Himikov',10,98,3),
       ('Nizhnekamsk','Himikov',94,115,3),
       ('Borok','Lenina',2,11,3),
       ('Kamskie polyani','Solnechnaya',5,10,3);

INSERT INTO orders(creating_date, status, transporting, transporting_date, cost, user_id, linoleum_id, address_id)
VALUES ('2022-01-30 10:00:00','executed','delivery','2022-01-30 15:00:00',15000,2,1,3);

INSERT INTO rolls(part_num, width, length, linoleum_id)
VALUES (552,3.5,50,1),
       (552,3,50,1),
       (444,4,36,2),
       (444,2.5,40,2),
       (445,2.5,40,2),
       (459,3,40,5),
       (459,3,40,3),
       (433,3.5,36,4),
       (222,4,40,6);











