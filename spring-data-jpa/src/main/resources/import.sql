insert into book (id, title) values (1, 'Pierwsza książka');
insert into book (id, title) values (2, 'Druga książka');
insert into book (id, title) values (3, 'Trzecia książka');

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);

insert into library (id, name) values (1, 'Biblioteka Miejska');
insert into library (id, name) values (2, 'Biblioteka Wiejska');
insert into library (id, name) values (3, 'Biblioteka Swiatowa');