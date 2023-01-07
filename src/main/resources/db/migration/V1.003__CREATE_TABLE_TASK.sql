create table task(
    id integer not null primary key,
    title varchar(250) not null,
    description varchar(500) not null,
    done boolean default false,
    due_date timestamp);