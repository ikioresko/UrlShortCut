create table site (
id serial primary key not null,
url varchar(200) unique,
login varchar(50),
password varchar(250)
);

create table url (
id serial primary key not null,
url varchar(300) unique,
code varchar(50),
call INT DEFAULT 0
);