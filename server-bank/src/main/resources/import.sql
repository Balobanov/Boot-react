insert into user(id, name, login, password) values (1,'Den','den','den');
insert into role(id, name) values (1,'ROLE_USER');

insert into user_role(user_id, role_id) values (1,1);
