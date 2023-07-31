create table books(
    id UUID not null primary key,
    name varchar(100) not null,
    author varchar(100) not null,
    publisher varchar(100) not null,
    genre varchar(50) not null,
    "language" varchar(30) not null
);