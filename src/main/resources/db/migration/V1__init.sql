create table if not exists HEROES (
	id INT,
	name VARCHAR(50),
	forename VARCHAR(50),
	company VARCHAR(50)
);


-- SUPER POWERS
create table if not exists SUPER_POWERS (
    id INT,
    name VARCHAR(50)
);

-- HEROES SUPER POWERS
create table if not exists HEROES_SUPER_POWERS (
	id INT,
	hero_id INT,
	super_power_id INT
);

-- USERS
create table if not exists USERS (
	id INT,
	username VARCHAR(15),
	password VARCHAR(15),
	roles varchar(50)
);
