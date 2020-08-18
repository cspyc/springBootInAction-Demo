delete from users;
delete from authorities;
delete from groups;
delete from group_members;
delete from group_authorities;

insert into users(id,username,password,enabled)
    values(111,'admin','123','');