create database if not exists auth_creepy;

CREATE TABLE IF NOT EXISTS users(
    id bigint(20) auto_increment primary key,
    username varchar(50) not null,
    email varchar(255) unique not null,
    password varchar(255) not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS permissions(
    id bigint(20) auto_increment primary key,
    description varchar(50) not null
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE IF NOT EXISTS user_permissions(
    id_user bigint(20) auto_increment NOT NULL,
    id_permission bigint(20) NOT NULL,
    PRIMARY KEY(id_user, id_permission),
    FOREIGN KEY(id_user) REFERENCES users(id),
    FOREIGN KEY(id_permission) REFERENCES permissions(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

