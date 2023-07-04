DELETE FROM users;
DELETE FROM orders;
DELETE FROM layouts;
DELETE FROM layouts_names;
DELETE FROM fragments;
DELETE FROM linoleums;
DELETE FROM rolls;

INSERT INTO users(name, email, password, phone_number,role)
VALUES ('Denis','denis.denis.kuznecov@mail.ru','222','89179292245','ADMIN'),
       ('Dima','Petrov59@mail.ru','444','89609292245','USER'),
       ('Oleg','Kazan88@mail.ru','333','89659292645','USER'),
       ('Galina','Sidorova23@mail.ru','777','89099292245','USER');

INSERT INTO layouts_names(ln_name)
VALUES ('BNCH1'),
       ('BNCH2Single'),
       ('BNCH2Diff'),
       ('BNCH3Single'),
       ('BNCH3Diff'),
       ('84s1'),
       ('84s2Single'),
       ('84s3Single'),
       ('84s4Single'),
       ('84s4Angular'),
       ('84s5Single'),
       ('84s5Diff'),
       ('Chrushevka1'),
       ('Chrushevka2Single'),
       ('Chrushevka2Diff'),
       ('Chrushevka3Diff'),
       ('Chrushevka4Diff'),
       ('83s1'),
       ('83s2Diff'),
       ('Custom1');

INSERT INTO layouts(city, street, home_num,room_count,row_type,l_type,layout_name_id)
VALUES ('Nizhnekamsk','Korabelnaya',4,1,'SINGLE','TEMPLATE',1),
       ('Nizhnekamsk','Tukaya',17,1,'SINGLE','TEMPLATE',13),
       ('Nizhnekamsk','Korabelnaya',14,1,'SINGLE','TEMPLATE',1),
       ('Nizhnekamsk','Stroiteley',1,2,'DIFFERENT','TEMPLATE',2),
       ('Nizhnekamsk','Junosti',3,2,'SINGLE','TEMPLATE',14),
       ('Nizhnekamsk','Mira',44,3,'SINGLE','TEMPLATE',8),
       ('Nizhnekamsk','Mira',12,3,'SINGLE','TEMPLATE',8),
       ('Nizhnekamsk','Korabelnaya',25,4,'DIFFERENT','TEMPLATE',17),
       ('Nizhnekamsk','Himikov',10,3,'SINGLE','TEMPLATE',8),
       ('Nizhnekamsk','Himikov',94,4,'ANGULAR','TEMPLATE',10),
       ('Borok','Lenina',2,5,'HOME','CUSTOM',20),
       ('Kamskie polyani','Solnechnaya',5,1,'SINGLE','TEMPLATE',18);


INSERT INTO fragments(width, length,f_type,layout_name_id)
VALUES (3.5,4.7,'KITCHEN',1),
       (2.5,4,'CORRIDOR',1),
       (1.5,4,'HALL',1),
       (3.5,5,'CORRIDOR',1),
       (2.5,6,'KITCHEN',2),
       (3.5,4,'HALL',2),
       (3.5,5.2,'BEDROOM',2),
       (1.5,3,'CORRIDOR',2),
       (2,5,'CORRIDOR',2),
       (4,4.9,'KITCHEN',3),
       (4,4.2,'HALL',3),
       (2.5,5,'CORRIDOR',3),
       (3,5,'BEDROOM',3);

INSERT INTO linoleums(l_name, protect, thickness, price,image_path)
VALUES ('jane 4',0.5,3.5,850,'src/main/resources/images/alaska-1-300x300.jpg'),
       ('saratoga 4',0.5,3.5,850,'src/main/resources/images/saratoga-4-300x300.jpg'),
       ('duart 1',0.4,2.7,600,'src/main/resources/images/SPENSER-3-300x300.jpg'),
       ('baden 1',0.5,2,710,'src/main/resources/images/BADEN-1-300x300.jpg'),
       ('forest 3',0.4,4.7,800,'src/main/resources/images/saratoga-6-300x300.jpg'),
       ('rigard 4',0.15,2,400,'src/main/resources/images/tango-3-1-300x300.jpg');


INSERT INTO orders(creating_date, status, transporting, transporting_date, cost, apartment_num, user_id, linoleum_id, layout_id)
VALUES ('2022-01-30 10:00:00','executed','delivery','2022-01-30 15:00:00',15000,22,2,1,3);

INSERT INTO rolls(part_num, r_width, r_length, linoleum_id)
VALUES (552,3.5,50,1),
       (552,3,50,1),
       (444,4,36,2),
       (444,2.5,40,2),
       (445,2.5,40,2),
       (459,3,40,5),
       (459,3,40,3),
       (433,3.5,36,4),
       (222,4,40,6);











