
insert into HEROES (id, name, forename, company) values (1, 'El Chapulin', 'Chespirito', 'MARVEL');
insert into HEROES (id, name, forename, company) values (2, 'Goldi', 'Goldi Ecob', 'DC');
insert into HEROES (id, name, forename, company) values (3, 'Josi', 'Josi Kryszka', 'DC');
insert into HEROES (id, name, forename, company) values (4, 'Farra', 'Farra Dranfield', 'DC');
insert into HEROES (id, name, forename, company) values (5, 'Delinda', 'Delinda Nother', 'MARVEL');


insert into SUPER_POWERS (id, name) values (2, 'invisibilidad');
insert into SUPER_POWERS (id, name) values (3, 'super velocidad');
insert into SUPER_POWERS (id, name) values (4, 'cambio de forma');
insert into SUPER_POWERS (id, name) values (5, 'chicharra paralizadora');
insert into SUPER_POWERS (id, name) values (6, 'volar');
insert into SUPER_POWERS (id, name) values (7, 'super sentidos');
insert into SUPER_POWERS (id, name) values (8, 'antenitas de vinil');
insert into SUPER_POWERS (id, name) values (9, 'telequinesis');
insert into SUPER_POWERS (id, name) values (10, 'teleportacion');
insert into SUPER_POWERS (id, name) values (11, 'regeneracion');
insert into SUPER_POWERS (id, name) values (12, 'super eslaticidad');
insert into SUPER_POWERS (id, name) values (13, 'manipulacion de elementos');
insert into SUPER_POWERS (id, name) values (14, 'chipote chillon');
insert into SUPER_POWERS (id, name) values (15, 'pastillas de chiquitolina');

insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (1, 15);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (1, 14);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (1, 8);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (1, 10);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (1, 5);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (1, 4);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (2, 13);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (4, 12);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (3, 10);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (5, 12);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (4, 4);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (2, 13);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (2, 3);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (5, 15);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (3, 5);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (2, 15);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (4, 3);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (4, 6);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (2, 6);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (3, 4);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (4, 2);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (5, 11);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (4, 14);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (3, 9);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (5, 15);
insert into HEROES_SUPER_POWERS (hero_id, super_power_id) values (3, 4);

insert into USERS (id, username, password, roles) values (1, 'user1', '{bcrypt}$2a$10$IYKJlnWkstg4nIZDip1IMONr04Bn8hynWpSh6DVcOYotJnX7NikoS', 'ROLE_ADMIN');
insert into USERS (id, username, password, roles) values (2, 'user2', '{bcrypt}$2a$10$IYKJlnWkstg4nIZDip1IMONr04Bn8hynWpSh6DVcOYotJnX7NikoS', 'ROLE_USER');

