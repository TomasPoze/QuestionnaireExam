create table admin
(
    id        serial not null,
    firstname varchar,
    password  varchar
);

alter table admin
    owner to postgres;

INSERT INTO public.admin (id, firstname, password) VALUES (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
create table answers
(
    id             serial not null
        constraint answers_pk
            primary key,
    answer_text    varchar,
    question_id    serial not null
        constraint answers_questions_id_fk
            references questions,
    true_false     boolean,
    answer_options varchar
);

alter table answers
    owner to postgres;

INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (4, 'Kaunas', 3, false, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (5, 'Vilnius', 3, true, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (6, 'Memelis', 3, false, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (3, '0', 1, false, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (1, '4', 1, true, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (2, '3', 1, false, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (7, '20', 2, false, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (9, '30', 2, false, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (10, '15', 2, true, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (11, '12', 4, false, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (12, '36', 4, true, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (13, '30', 4, false, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (17, 'Kijevas', 12, false, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (18, 'Ryga', 12, true, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (19, 'Talinas', 12, false, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (14, '1917', 11, false, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (15, '1929', 11, false, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (16, '1939', 11, true, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (20, '1999', 13, false, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (21, '1985', 13, false, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (22, '1990', 13, true, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (23, 'Antanas Smetona', 14, true, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (24, 'Kazys Grinius', 14, false, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (25, 'Aleksandras Stulginskis', 14, false, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (27, '1922', 15, true, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (28, '1918', 15, false, 'C');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (26, '1920', 15, false, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (29, '30', 16, false, 'A');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (30, '32', 16, true, 'B');
INSERT INTO public.answers (id, answer_text, question_id, true_false, answer_options) VALUES (31, '28', 16, false, 'C');
create table questionnaire
(
    id   serial not null
        constraint questionnaire_pk
            primary key,
    name varchar
);

alter table questionnaire
    owner to postgres;

INSERT INTO public.questionnaire (id, name) VALUES (1, 'Geografija');
INSERT INTO public.questionnaire (id, name) VALUES (2, 'Matematika');
INSERT INTO public.questionnaire (id, name) VALUES (3, 'Istorija');
create table questions
(
    id               serial not null
        constraint questions_pk
            primary key,
    question_text    varchar,
    questionnaire_id serial not null
        constraint questions_questionnaire_id_fk
            references questionnaire
);

alter table questions
    owner to postgres;

INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (2, 'Kiek bus 5 + 5 * 2?', 2);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (3, 'Lietuvos sostine?', 1);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (4, 'Kiek bus 6 * 6?', 2);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (12, 'Latvijos sostine?', 1);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (13, 'Kada Lietuva tapo nepriklausoma valstybe?', 3);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (14, 'Pirmasis Lietuvos valstybės prezidentas?', 3);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (15, 'Pirmoji nuolatine Lietuvos Konstitucija priimta?', 3);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (11, 'Antro pasaulinio karo pradzia?', 3);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (16, 'Kiek bus 16 x 4 / 2 ?', 2);
INSERT INTO public.questions (id, question_text, questionnaire_id) VALUES (1, 'Kiek bus 2 + 2 ?', 2);
create table results
(
    id                         serial not null
        constraint results_questionnaire_id_fk
            references questionnaire,
    name                       varchar,
    total_finished_exams_count integer,
    total_correct_answers      integer,
    total_a                    integer,
    total_b                    integer,
    total_c                    integer
);

alter table results
    owner to postgres;

create unique index results_name_uindex
    on results (name);

INSERT INTO public.results (id, name, total_finished_exams_count, total_correct_answers, total_a, total_b, total_c) VALUES (2, 'Matematika', 4, 17, 6, 12, 3);
INSERT INTO public.results (id, name, total_finished_exams_count, total_correct_answers, total_a, total_b, total_c) VALUES (1, 'Geografija', 9, 10, 4, 10, 4);
INSERT INTO public.results (id, name, total_finished_exams_count, total_correct_answers, total_a, total_b, total_c) VALUES (3, 'Istorija', 8, 18, 14, 6, 11);