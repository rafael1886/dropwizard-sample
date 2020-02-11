insert into address(street, city, state, zip)
values ('Morwowa', 'Lublin', 'lubelskie', '22-500'),
       ('Agaty', 'Warszawa', 'mazowieckie', '22-345');

insert into users(name, surname, address_id)
values ('Monika', 'Niebo', 1), ('Natalia', 'Niebo', 1), ('Daria', 'Drzewo', 2);

insert into books (title, writer, user_id) values ('Wiedzmin', 'Sapkowski', 1);
insert into books (title, writer, user_id) values ('Wiedzmin', 'Sapkowski', 2);
