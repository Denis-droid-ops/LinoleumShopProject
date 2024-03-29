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
VALUES ('84s2xInc'),
       ('84s2xStd'),
       ('84s1xGCorr'),
       ('84s1xStrCorr'),
       ('84s1x'),
       ('84s3xGCorr'),
       ('84s3xTCorr'),
       ('84s3xAnglr'),
       ('84s3xOvrArch'),
       ('84s3xOvrArch2balc'),
       ('BNCH1x'),
       ('BNCH2xDiffZCorr'),
       ('BNCH2xDiffGCorr'),
       ('BNCH2xOne'),
       ('BNCH3x'),
       ('Chrushevka2xDiff'),
       ('Chrushevka2xOne');



INSERT INTO layouts(city, street, home_num,room_count,row_type,l_type,layout_name_id)
VALUES ('Nizhnekamsk','Korabelnaya',4,1,'ONE_LINE','TEMPLATE',1),
       ('Nizhnekamsk','Tukaya',17,1,'ONE_LINE','TEMPLATE',13),
       ('Nizhnekamsk','Korabelnaya',14,1,'ONE_LINE','TEMPLATE',1),
       ('Nizhnekamsk','Stroiteley',1,2,'DIFFERENT','TEMPLATE',2),
       ('Nizhnekamsk','Junosti',3,2,'ONE_LINE','TEMPLATE',14),
       ('Nizhnekamsk','Mira',44,3,'ONE_LINE','TEMPLATE',8),
       ('Nizhnekamsk','Mira',12,3,'ONE_LINE','TEMPLATE',8),
       ('Nizhnekamsk','Korabelnaya',25,4,'DIFFERENT','TEMPLATE',17),
       ('Nizhnekamsk','Himikov',10,3,'ONE_LINE','TEMPLATE',8),
       ('Nizhnekamsk','Himikov',94,4,'ANGULAR','TEMPLATE',10),
       ('Borok','Lenina',2,5,'HOME','CUSTOM',17),
       ('Kamskie polyani','Solnechnaya',5,1,'ONE_LINE','TEMPLATE',15);

INSERT INTO fragments(width, length,f_type,layout_name_id)
VALUES  (3.5,5.85,'HALL',1),
        (3,4.3,'BEDROOM',1),
        (1.5,2.9,'CORRIDOR',1),
        (3.5,3.05,'KITCHEN',1),
        (3,1.6,'CORRIDOR',1),
        (3.5,4.65,'HALL',2),
        (3,4.4,'BEDROOM',2),
        (1.5,3.3,'CORRIDOR',2),
        (3,1.45,'CORRIDOR',2),
        (3.5,2.6,'KITCHEN',2),
        (3.5,5.85,'HALL',3),
        (3.5,2.6,'KITCHEN',3),
        (1.5,3.3,'CORRIDOR',3),
        (2.5,1.6,'CORRIDOR',3),
        (3.5,5.85,'HALL',4),
        (3.5,3.05,'KITCHEN',4),
        (2,3.1,'CORRIDOR',4),
        (3.5,4.3,'HALL',5),
        (3.5,3.05,'KITCHEN',5),
        (2,2.9,'CORRIDOR',5),
        (3.5,1.6,'CORRIDOR',5),
        (4,4.3,'HALL',6),
        (1,4.3,'HALL',6),
        (2.5,3.6,'KITCHEN',6),
        (3.5,3.6,'BEDROOM',6),
        (3.5,3.1,'BEDROOM',6),
        (2,2.9,'CORRIDOR',6),
        (2.5,1.1,'CORRIDOR',6),
        (3.5,1.6,'CORRIDOR',6),
        (4,4.3,'HALL',7),
        (1,4.3,'HALL',7),
        (2.5,3.5,'KITCHEN',7),
        (3.5,2.9,'BEDROOM',7),
        (3.5,3.55,'BEDROOM',7),
        (2,3.1,'CORRIDOR',7),
        (2.5,1.3,'CORRIDOR',7),
        (3.5,1.6,'CORRIDOR',7),
        (3.5,2.9,'BEDROOM',8),
        (3.5,4.8,'HALL',8),
        (2.5,4.8,'KITCHEN',8),
        (2,3.1,'CORRIDOR',8),
        (3.5,3.55,'BEDROOM',8),
        (2,2.4,'CORRIDOR',8),
        (3,2.4,'CORRIDOR',8),
        (4,4.65,'HALL',9),
        (1,4.65,'HALL',9),
        (3.5,4.4,'BEDROOM',9),
        (3,4.4,'KITCHEN',9),
        (3.5,2.6,'CORRIDOR',9),
        (4,3.25,'BEDROOM',9),
        (1.5,3.3,'CORRIDOR',9),
        (4,4.65,'HALL',10),
        (1,4.65,'HALL',10),
        (3.5,3.2,'BEDROOM',10),
        (3,4.4,'KITCHEN',10),
        (3.5,2.6,'CORRIDOR',10),
        (4,3.25,'BEDROOM',10),
        (1.5,3.3,'CORRIDOR',10),
        (3.5,5.3,'HALL',11),
        (1,2.8,'CORRIDOR',11),
        (2.5,2.8,'KITCHEN',11),
        (4,4.1,'HALL',12),
        (2,3.1,'CORRIDOR',12),
        (3,3.9,'BEDROOM',12),
        (3,2.3,'KITCHEN',12),
        (1.5,2.6,'CORRIDOR',12),
        (4,4,'HALL',13),
        (1,4,'HALL',13),
        (2,4,'KITCHEN',13),
        (3,4.25,'BEDROOM',13),
        (1.5,2.3,'CORRIDOR',13),
        (3,1.3,'CORRIDOR',13),
        (2.5,2.55,'KITCHEN',14),
        (3.5,3.8,'BEDROOM',14),
        (3,5.1,'HALL',14),
        (1,2.65,'CORRIDOR',14),
        (3.5,1.55,'CORRIDOR',14),
        (4,4.9,'HALL',15),
        (2,3.1,'CORRIDOR',15),
        (3,3.1,'BEDROOM',15),
        (3,4.45,'BEDROOM',15),
        (1.5,2.6,'CORRIDOR',15),
        (3,3,'KITCHEN',15),
        (3.5,5.3,'HALL',16),
        (3,5.25,'BEDROOM',16),
        (3,2.1,'KITCHEN',16),
        (1,2,'CORRIDOR',16),
        (3,1.3,'CORRIDOR',16),
        (4,5.1,'HALL',17),
        (3,1.3,'CORRIDOR',17),
        (3,2.2,'KITCHEN',17),
        (3,5.1,'BEDROOM',17);

INSERT INTO linoleums(l_name, protect, thickness, price,image_path)
VALUES ('jane 4',0.5,3.5,850,'src/main/resources/images/alaska-1-300x300.jpg'),
       ('saratoga 4',0.5,3.5,850,'src/main/resources/images/saratoga-4-300x300.jpg'),
       ('duart 1',0.4,2.7,600,'src/main/resources/images/SPENSER-3-300x300.jpg'),
       ('baden 1',0.5,2,710,'src/main/resources/images/BADEN-1-300x300.jpg'),
       ('forest 3',0.4,4.7,800,'src/main/resources/images/saratoga-6-300x300.jpg'),
       ('rigard 4',0.15,2,400,'src/main/resources/images/tango-3-1-300x300.jpg');


INSERT INTO orders(creating_date, status, transporting, transporting_date, cost, apartment_num, user_id, linoleum_id)
VALUES ('2022-01-30 10:00:00','COMPLETED','DELIVERY','2022-01-30 15:00:00',15000,22,2,1);

INSERT INTO rolls(part_num, r_width, r_length,is_remain,linoleum_id)
VALUES (552,3.5,50,false,1),
       (552,3,50,false,1),
       (444,4,36,false,2),
       (444,2.5,40,false,2),
       (445,2.5,40,false,2),
       (459,3,40,false,5),
       (459,3,40,false,3),
       (433,3.5,36,false,4),
       (222,4,40,false,6);











