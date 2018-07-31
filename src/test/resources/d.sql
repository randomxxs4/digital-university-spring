-- INSERT INTO days (id, day) VALUES
--   (101, 'Понедельник'),
--   (102, 'Вторник'),
--   (103, 'Среда'),
--   (104, 'Четверг'),
--   (105, 'Пятница'),
--   (106, 'Суббота'),
--   (107, 'Воскресенье');
--
-- INSERT INTO groups (id, title) VALUES
--   (111, 'М-11'),
--   (112, 'Физ-11'),
--   (113, 'Фил-11');
--
-- INSERT INTO pairs (id, number) VALUES
--   (121, 1),
--   (122, 2),
--   (123, 3),
--   (124, 4),
--   (125, 5),
--   (126, 6),
--   (127, 7);
--
-- INSERT INTO positions (id, title) VALUES
--   (131, 'Преподаватель математики'),
--   (132, 'Преподаватель физики'),
--   (133, 'Преподаватель философии');
--
-- INSERT INTO ratings (id, rating) VALUES
--   (141, '1'),
--   (142, '2'),
--   (143, '3'),
--   (144, '4'),
--   (145, '5'),
--   (146, 'y'),
--   (147, ' ');
--
-- INSERT INTO roles (id, role) VALUES
--   (151, 'roleteacher'),
--   (152, 'rolestudent');
--
-- INSERT INTO specialities (id, title) VALUES
--   (161, 'Математик'),
--   (162, 'Физик'),
--   (163, 'Философ');
--
-- INSERT INTO subjects (id, title) VALUES
--   (171, 'Высшая математика'),
--   (172, 'Физика'),
--   (173, 'Философия');
--
-- INSERT INTO position_subject (position_id, subject_id) VALUES
--   (131, 171),
--   (132, 172),
--   (133, 173);
--
-- INSERT INTO users (id, middlename, name, password, surname, username) VALUES
--   (181, 'Иванович', 'Иван', '123', 'Иванов', 'teacher'),
--   (182, 'Владимирович', 'Александр', '456', 'Малявский', 'student'),
--   (183, 'Дмитриевич', 'Дмитрий', '456', 'Дмитриев', 'student1'),
--   (184, 'Алексеевич', 'Юрий', '456', 'Гагарин', 'student2');
--
-- INSERT INTO users_roles (users_id, roles_id) VALUES
--   (181, 151),
--   (182, 152),
--   (183, 152),
--   (184, 152);
--
-- INSERT INTO teachers (id, position, user_id) VALUES
--   (181, 131, 181);
--
-- INSERT INTO students (id, student_group_id, student_speciality_id, user_id) VALUES
--   (191, 111, 161, 182),
--   (192, 112, 162, 183),
--   (193, 113, 163, 184);
--
--
-- INSERT INTO timetables (id, timetable_day_id, timetable_group_id, timetable_pair_id, timetable_subject_id, timetable_teacher_id)
-- VALUES
--   --   Иванов
--   (201, 101, 111, 121, 171, 181),
--   (202, 102, 111, 123, 171, 181),
--   (203, 103, 112, 122, 171, 181),
--   (204, 104, 113, 125, 171, 181),
--   (205, 105, 111, 122, 171, 181),
--   (206, 106, 113, 121, 171, 181);
--
-- INSERT INTO journals (id, date, rating, student, subject, timetable) VALUES
--   --   Малявский математика
--   (211, now() :: DATE, 145, 191, 171, 201),
--   (212, now() :: DATE, 143, 191, 171, 205),
--   (213, now() :: DATE, 143, 191, 171, 202),
--   (214, now() :: DATE, 145, 192, 171, 203),
--   (215, now() :: DATE, 145, 193, 171, 204),
--   (216, now() :: DATE, 145, 193, 171, 206);
--
-- INSERT INTO tokens (id, experation_date, token_string, user_id) VALUES
--   (333, cast('2018-09-23 15:53:55.249' as TIMESTAMP), 'TOKEN11532961371638', 181);