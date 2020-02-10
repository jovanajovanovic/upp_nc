insert into db_camunda.scientific (id, name, code) values (1, 'matematika', 'mat');
insert into db_camunda.scientific (id, name, code) values (2, 'informatika', 'info');
insert into db_camunda.scientific (id, name, code) values (3, 'web programiranje', 'wp');
insert into db_camunda.scientific (id, name, code) values (4, 'jezici specificni za domen', 'dsl');

insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (1, 'Pera', 'Peric', 'demo', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'jovanaj33@gmail.com', 'sw', 4, true);

insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (2, 'Pera', 'Peric', 'editor', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'jovanaj33@gmail.com', 'sw', 3, true);
insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (3, 'Petra', 'Peric', 'petra', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'jovanaj33@gmail.com', 'sw', 3, true);
insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (4, 'Mira', 'Peric', 'mira', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'jovanaj33@gmail.com', 'sw', 3, true);

insert into db_camunda.editor(id) values(2);
insert into db_camunda.editor(id) values(3);
insert into db_camunda.editor(id) values(4);
insert into db_camunda.editor_scientific_field_list (editor_id, scientific_field_list_id) values (2, 1);
insert into db_camunda.editor_scientific_field_list (editor_id, scientific_field_list_id) values (2, 3);
insert into db_camunda.editor_scientific_field_list (editor_id, scientific_field_list_id) values (3, 2);
insert into db_camunda.editor_scientific_field_list (editor_id, scientific_field_list_id) values (4, 2);
insert into db_camunda.editor_scientific_field_list (editor_id, scientific_field_list_id) values (4, 1);

insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (5, 'Pera', 'Peric', 'peraRev', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'pera@gmail.com', 'sw', 2, true);
insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (6, 'Mihailo', 'Peric', 'miki', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'pera@gmail.com', 'sw', 2, true);
insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (7, 'Marija', 'Peric', 'mara', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'pera@gmail.com', 'sw', 2, true);
insert into db_camunda.user(id, name, surname, username, password, city, country, email, title, role, activate)
values (8, 'Milica', 'Milic', 'milicamilic', '$2a$10$kx6ymttdiBQ/3NAz1ssxoeOF8Vwm3LSKFnEkSADPc5x8kgaj/vKnC', 'Ns', 'Srb', 'jovanaj33@gmail.com', 'sw', 1, true);
insert into db_camunda.reviewer(id) values(5);
insert into db_camunda.reviewer(id) values(6);
insert into db_camunda.reviewer(id) values(7);
insert into db_camunda.author(id) values (8);
insert into db_camunda.reviewer_scientific_field_list (reviewer_id, scientific_field_list_id) values (5, 1);
insert into db_camunda.reviewer_scientific_field_list (reviewer_id, scientific_field_list_id) values (5, 3);
insert into db_camunda.reviewer_scientific_field_list (reviewer_id, scientific_field_list_id) values (6, 2);
insert into db_camunda.reviewer_scientific_field_list (reviewer_id, scientific_field_list_id) values (7, 2);
insert into db_camunda.reviewer_scientific_field_list (reviewer_id, scientific_field_list_id) values (7, 1);


insert into db_camunda.magazine (id, activate, issn, title, type, chief_editor_id) values (1, 1, 'mi123', 'casopis informaticar', 0, 2);
insert into db_camunda.magazine_reviewers(magazine_id, reviewers_id) values (1, 7);
insert into db_camunda.magazine_reviewers(magazine_id, reviewers_id) values (1, 5);
insert into db_camunda.magazine_scientific_field(magazine_id, scientific_field_id) values (1, 1);
insert into db_camunda.magazine_scientific_field(magazine_id, scientific_field_id) values (1, 3);
insert into db_camunda.editor_board(id, editor_id, magazine_id, scientific_field_id) values (1, 2,1,1);
insert into db_camunda.editor_board(id, editor_id, magazine_id, scientific_field_id) values (2, 2, 1, 3);

