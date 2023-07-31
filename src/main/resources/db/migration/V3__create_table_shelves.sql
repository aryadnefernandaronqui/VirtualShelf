create table shelves(
    id UUID not null primary key,
    user_id UUID not null references users(id),
    name varchar(50) not null,
    update_at timestamp default 'now()'
);