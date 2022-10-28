create table user
(
    id         int     not null
        constraint user_pk
            primary key,
    first_name varchar not null,
    last_name  varchar,
    email      varchar not null,
    is_active  boolean,
    role_id    int,
    address_id int,
    account_id int
);

create unique index user_email_uindex
    on user (email);
-----------------------------
create table role
(
    id   int     not null
        constraint role_pk
            primary key,
    name varchar not null
);
-----------------------------
create table address
(
    id     int     not null
        constraint address_pk
            primary key,
    street varchar not null,
    house  int,
    flat   int
);
-----------------------------
create table account
(
    id          int not null
        constraint account_pk
            primary key,
    value       float,
    currency_id int
);
-----------------------------
create table payment
(
    id          int not null
        constraint payment_pk
            primary key,
    currency_id int,
    description varchar
);
-----------------------------
create table currency
(
    id   int     not null
        constraint currency_pk
            primary key,
    name varchar not null
);
