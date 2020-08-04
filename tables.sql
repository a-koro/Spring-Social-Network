create database social_network;
use social_network;

create table users (
	user_id int auto_increment not null,
    email varchar(60) not null unique,
    password varchar(60) not null,
    first_name varchar(60) not null,
    last_name varchar(60) not null,
    birthday date not null,
    primary key (user_id)
);

create table roles (
	role_id int auto_increment not null,
    role_name varchar(20) not null unique,
    primary key (role_id)
);

create table user_role(
	user_id int not null,
    role_id int not null,
    constraint user_role_fk_uid foreign key (user_id) references users(user_id),
    constraint user_role_fk_rid foreign key (role_id) references roles(role_id),
    primary key (user_id, role_id)
);

insert into roles values(null, "ROLE_USER");
insert into users values(null, "korove@hotmail.com", "$2a$10$ZamKjG//nEHQV3ItVNxRFuffy7HM7frbEiheHlrj4bfUCygXi8dLa", "Alexandros", "Korovesis", "1987-12-07");
insert into user_role values(1,2);

