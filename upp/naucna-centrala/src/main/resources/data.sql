insert into db_camunda.scientific (id, name) values (1, 'matematika');
insert into db_camunda.scientific (id, name) values (2, 'informatika');
insert into db_camunda.scientific (id, name) values (3, 'web programiranje');
insert into db_camunda.scientific (id, name) values (4, 'jezici specificni za domen');

insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (1, 'Pera', 'Peric', 'demo', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'pera@gmail.com', 'sw', 4, true);

insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (2, 'Pera', 'Peric', 'editor', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'pera@gmail.com', 'sw', 3, true);

insert into db_camunda.editor(id) values(2);