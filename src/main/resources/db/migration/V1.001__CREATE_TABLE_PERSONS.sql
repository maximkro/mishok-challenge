create table person (
    id integer primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(256) not null,
    country varchar(100),
    university varchar(100),
    comment varchar(200),
    birthday timestamp not null);
