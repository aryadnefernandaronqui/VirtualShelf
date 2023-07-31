create table book_on_shelf(
    id UUID not null primary key,
    favorite boolean not null,
    status int not null,
    book_id UUID not null references books(id),
    shelf_id UUID not null references shelves(id),
    update_at timestamp default 'now()'
);