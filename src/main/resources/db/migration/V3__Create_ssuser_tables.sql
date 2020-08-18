create table if not exists users (
    id identity not null,
    username varchar(25) not null,
    password varchar (30) not null,
    enabled varchar (2)
);

create table if not exists authorities(
    id identity ,
    username varchar (25) not null ,
    authority varchar(50) not null
);

create table if not exists groups(
   id identity not null,
   group_name varchar (50) not null
);

create table if not exists group_members(
   id identity not null,
   group_name varchar (50) not null,
   username varchar (25) not null
);

create table if not exists group_authorities(
   id identity not null,
   group_name varchar (50) not null,
   authority varchar (25) not null
);


alter table authorities
    add foreign key (username) references users(username);

alter table group_members
    add foreign key (group_name) references groups(group_name);

alter table group_members
    add foreign key (username) references users(username);

alter table group_authorities
    add foreign key (group_name) references groups(group_name);
