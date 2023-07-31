create table users(
    id UUID not null primary key,
    name varchar(40) not null,
    email varchar(60) not null,
    password varchar(20) not null
);