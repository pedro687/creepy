create database if not exists creepy;

CREATE TABLE IF NOT EXISTS users(
    id bigint(20) auto_increment primary key,
    uuid varchar(50) unique not null,
    username varchar(50) not null,
    email varchar(255) unique not null,
    password varchar(255) not null,
    gender varchar(1) not null,
    uf varchar(2) not null,
    city varchar(25) not null,
    tellphone varchar(11) not null,
    country varchar(20) not null
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
