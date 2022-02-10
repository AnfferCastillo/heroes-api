create table heroes (
	id INT,
	name VARCHAR(50),
	forename VARCHAR(50),
	company VARCHAR(50)
);

insert into heroes (id, name, forename, company) values (1, 'Adeline', 'Adeline Haggerwood', 'MARVEL');
insert into heroes (id, name, forename, company) values (2, 'Goldi', 'Goldi Ecob', 'DC');
insert into heroes (id, name, forename, company) values (3, 'Josi', 'Josi Kryszka', 'DC');
insert into heroes (id, name, forename, company) values (4, 'Farra', 'Farra Dranfield', 'DC');
insert into heroes (id, name, forename, company) values (5, 'Delinda', 'Delinda Nother', 'MARVEL');
insert into heroes (id, name, forename, company) values (6, 'Enriqueta', 'Enriqueta Jennrich', 'MARVEL');
insert into heroes (id, name, forename, company) values (7, 'Benny', 'Benny O''Geneay', 'DC');
insert into heroes (id, name, forename, company) values (8, 'Lanette', 'Lanette Shiell', 'MARVEL');
insert into heroes (id, name, forename, company) values (9, 'Fidel', 'Fidel Spary', 'DC');
insert into heroes (id, name, forename, company) values (10, 'Trueman', 'Trueman Connachan', 'MARVEL');
insert into heroes (id, name, forename, company) values (11, 'Shandeigh', 'Shandeigh Dossett', 'MARVEL');
insert into heroes (id, name, forename, company) values (12, 'Cherilynn', 'Cherilynn Dorn', 'MARVEL');
insert into heroes (id, name, forename, company) values (13, 'Beverie', 'Beverie Beaby', 'DC');
insert into heroes (id, name, forename, company) values (14, 'Anita', 'Anita Gasparro', 'MARVEL');
insert into heroes (id, name, forename, company) values (15, 'Amelie', 'Amelie Tellett', 'MARVEL');
insert into heroes (id, name, forename, company) values (16, 'Felicdad', 'Felicdad Juett', 'MARVEL');
insert into heroes (id, name, forename, company) values (17, 'Lalo', 'Lalo Povah', 'MARVEL');
insert into heroes (id, name, forename, company) values (18, 'Lindsay', 'Lindsay Aimson', 'MARVEL');
insert into heroes (id, name, forename, company) values (19, 'Mead', 'Mead Stickels', 'DC');
insert into heroes (id, name, forename, company) values (20, 'Bobbe', 'Bobbe Webberley', 'MARVEL');

-- SUPER POWERS
create table super_powers (
    id INT,
    name VARCHAR(50)
);

insert into super_powers (id, name) values (2, 'invisibilidad');
insert into super_powers (id, name) values (3, 'super velocidad');
insert into super_powers (id, name) values (4, 'cambio de forma');
insert into super_powers (id, name) values (5, 'super fuerza-Dox');
insert into super_powers (id, name) values (6, 'volar');
insert into super_powers (id, name) values (7, 'super sentidos');
insert into super_powers (id, name) values (8, 'telepatia');
insert into super_powers (id, name) values (9, 'telequinesis');
insert into super_powers (id, name) values (10, 'teleportacion');
insert into super_powers (id, name) values (11, 'regeneracion');
insert into super_powers (id, name) values (12, 'super eslaticidad');
insert into super_powers (id, name) values (13, 'manipulacion de elementos');
insert into super_powers (id, name) values (14, 'vision de rayos x');
insert into super_powers (id, name) values (15, 'riqueza');

-- HEROES SUPER POWERS
create table heroes_super_powers (
	id INT,
	hero_id INT,
	super_power_id INT
);
insert into heroes_super_powers (id, hero_id, super_power_id) values (1, 12, 1);
insert into heroes_super_powers (id, hero_id, super_power_id) values (2, 17, 10);
insert into heroes_super_powers (id, hero_id, super_power_id) values (3, 15, 15);
insert into heroes_super_powers (id, hero_id, super_power_id) values (4, 2, 14);
insert into heroes_super_powers (id, hero_id, super_power_id) values (5, 16, 14);
insert into heroes_super_powers (id, hero_id, super_power_id) values (6, 1, 11);
insert into heroes_super_powers (id, hero_id, super_power_id) values (7, 2, 13);
insert into heroes_super_powers (id, hero_id, super_power_id) values (8, 4, 12);
insert into heroes_super_powers (id, hero_id, super_power_id) values (9, 3, 10);
insert into heroes_super_powers (id, hero_id, super_power_id) values (10, 16, 14);
insert into heroes_super_powers (id, hero_id, super_power_id) values (11, 11, 4);
insert into heroes_super_powers (id, hero_id, super_power_id) values (12, 20, 5);
insert into heroes_super_powers (id, hero_id, super_power_id) values (13, 15, 7);
insert into heroes_super_powers (id, hero_id, super_power_id) values (14, 10, 8);
insert into heroes_super_powers (id, hero_id, super_power_id) values (15, 5, 12);
insert into heroes_super_powers (id, hero_id, super_power_id) values (16, 11, 1);
insert into heroes_super_powers (id, hero_id, super_power_id) values (17, 4, 4);
insert into heroes_super_powers (id, hero_id, super_power_id) values (18, 2, 13);
insert into heroes_super_powers (id, hero_id, super_power_id) values (19, 19, 8);
insert into heroes_super_powers (id, hero_id, super_power_id) values (20, 19, 2);
insert into heroes_super_powers (id, hero_id, super_power_id) values (21, 2, 5);
insert into heroes_super_powers (id, hero_id, super_power_id) values (22, 11, 10);
insert into heroes_super_powers (id, hero_id, super_power_id) values (23, 2, 3);
insert into heroes_super_powers (id, hero_id, super_power_id) values (24, 17, 2);
insert into heroes_super_powers (id, hero_id, super_power_id) values (25, 15, 11);
insert into heroes_super_powers (id, hero_id, super_power_id) values (26, 10, 1);
insert into heroes_super_powers (id, hero_id, super_power_id) values (27, 1, 4);
insert into heroes_super_powers (id, hero_id, super_power_id) values (28, 11, 7);
insert into heroes_super_powers (id, hero_id, super_power_id) values (29, 5, 15);
insert into heroes_super_powers (id, hero_id, super_power_id) values (30, 17, 4);
insert into heroes_super_powers (id, hero_id, super_power_id) values (31, 3, 5);
insert into heroes_super_powers (id, hero_id, super_power_id) values (32, 20, 8);
insert into heroes_super_powers (id, hero_id, super_power_id) values (33, 15, 5);
insert into heroes_super_powers (id, hero_id, super_power_id) values (34, 2, 15);
insert into heroes_super_powers (id, hero_id, super_power_id) values (35, 7, 1);
insert into heroes_super_powers (id, hero_id, super_power_id) values (36, 4, 3);
insert into heroes_super_powers (id, hero_id, super_power_id) values (37, 15, 9);
insert into heroes_super_powers (id, hero_id, super_power_id) values (38, 13, 14);
insert into heroes_super_powers (id, hero_id, super_power_id) values (39, 17, 4);
insert into heroes_super_powers (id, hero_id, super_power_id) values (40, 4, 6);
insert into heroes_super_powers (id, hero_id, super_power_id) values (41, 6, 3);
insert into heroes_super_powers (id, hero_id, super_power_id) values (42, 15, 8);
insert into heroes_super_powers (id, hero_id, super_power_id) values (43, 18, 7);
insert into heroes_super_powers (id, hero_id, super_power_id) values (44, 2, 6);
insert into heroes_super_powers (id, hero_id, super_power_id) values (45, 3, 4);
insert into heroes_super_powers (id, hero_id, super_power_id) values (46, 4, 2);
insert into heroes_super_powers (id, hero_id, super_power_id) values (47, 17, 13);
insert into heroes_super_powers (id, hero_id, super_power_id) values (48, 5, 11);
insert into heroes_super_powers (id, hero_id, super_power_id) values (49, 9, 12);
insert into heroes_super_powers (id, hero_id, super_power_id) values (50, 6, 3);
insert into heroes_super_powers (id, hero_id, super_power_id) values (51, 6, 13);
insert into heroes_super_powers (id, hero_id, super_power_id) values (52, 7, 11);
insert into heroes_super_powers (id, hero_id, super_power_id) values (53, 9, 6);
insert into heroes_super_powers (id, hero_id, super_power_id) values (54, 20, 1);
insert into heroes_super_powers (id, hero_id, super_power_id) values (55, 19, 7);
insert into heroes_super_powers (id, hero_id, super_power_id) values (56, 9, 9);
insert into heroes_super_powers (id, hero_id, super_power_id) values (57, 1, 10);
insert into heroes_super_powers (id, hero_id, super_power_id) values (58, 4, 14);
insert into heroes_super_powers (id, hero_id, super_power_id) values (59, 3, 9);
insert into heroes_super_powers (id, hero_id, super_power_id) values (60, 15, 14);
insert into heroes_super_powers (id, hero_id, super_power_id) values (61, 10, 14);
insert into heroes_super_powers (id, hero_id, super_power_id) values (62, 5, 15);
insert into heroes_super_powers (id, hero_id, super_power_id) values (63, 8, 15);
insert into heroes_super_powers (id, hero_id, super_power_id) values (64, 7, 5);
insert into heroes_super_powers (id, hero_id, super_power_id) values (65, 12, 5);
insert into heroes_super_powers (id, hero_id, super_power_id) values (66, 12, 6);
insert into heroes_super_powers (id, hero_id, super_power_id) values (67, 8, 1);
insert into heroes_super_powers (id, hero_id, super_power_id) values (68, 17, 5);
insert into heroes_super_powers (id, hero_id, super_power_id) values (69, 20, 13);
insert into heroes_super_powers (id, hero_id, super_power_id) values (70, 3, 5);
insert into heroes_super_powers (id, hero_id, super_power_id) values (71, 10, 8);
insert into heroes_super_powers (id, hero_id, super_power_id) values (72, 19, 15);
insert into heroes_super_powers (id, hero_id, super_power_id) values (73, 11, 9);
insert into heroes_super_powers (id, hero_id, super_power_id) values (74, 10, 12);
insert into heroes_super_powers (id, hero_id, super_power_id) values (75, 7, 12);
insert into heroes_super_powers (id, hero_id, super_power_id) values (76, 3, 4);
insert into heroes_super_powers (id, hero_id, super_power_id) values (77, 13, 3);
insert into heroes_super_powers (id, hero_id, super_power_id) values (78, 13, 12);
insert into heroes_super_powers (id, hero_id, super_power_id) values (79, 14, 14);
insert into heroes_super_powers (id, hero_id, super_power_id) values (80, 20, 7);

-- USERS
create table users (
	id INT,
	username VARCHAR(15),
	password VARCHAR(15),
	roles varchar(50)
);

insert into users (id, username, password, roles) values (1, 'user1', 'testing', 'ROLE_ADMIN');
insert into users (id, username, password, roles) values (2, 'user2', 'testing', 'ROLE_USER');

