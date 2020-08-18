create table if not exists User (
    id identity not null,
    username varchar (25) not null ,
    password varchar (50) not null ,
    fullname varchar (25) ,
    street varchar (50) ,
    city varchar (25) ,
    state varchar (25) ,
    zip varchar (10) ,
    phoneNumber varchar(20)
);
