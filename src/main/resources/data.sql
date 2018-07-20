INSERT INTO days (id, day) VALUES
  (1, 'Понедельник'),
  (2, 'Вторник'),
  (3, 'Среда'),
  (4, 'Четверг'),
  (5, 'Пятница'),
  (6, 'Суббота'),
  (7, 'Воскресенье');

INSERT INTO groups (id, title) VALUES
  (1, 'М-11'),
  (2, 'Физ-11'),
  (3, 'Фил-11');

INSERT INTO pairs (id, number) VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5),
  (6, 6),
  (7, 7);

INSERT INTO positions (id, title) VALUES
  (1, 'Преподаватель математики'),
  (2, 'Преподаватель физики'),
  (3, 'Преподаватель философии');

INSERT INTO ratings (id, rating) VALUES
  (1, '1'),
  (2, '2'),
  (3, '3'),
  (4, '4'),
  (5, '5'),
  (6, 'y'),
  (7, ' ');

INSERT INTO roles (id, role) VALUES
  (1, 'roleteacher'),
  (2, 'rolestudent');

INSERT INTO specialities (id, title) VALUES
  (1, 'Математик'),
  (2, 'Физик'),
  (3, 'Философ');

INSERT INTO subjects (id, title) VALUES
  (1, 'Высшая математика'),
  (2, 'Физика'),
  (3, 'Философия');

INSERT INTO position_subject (position_id, subject_id) VALUES
  (1, 1),
  (2, 2),
  (3, 3);

INSERT INTO users(id,middlename, name, password, surname, username) VALUES
  (1, 'Иванович', 'Иван', '123', 'Иванов', 'teacher'),
  (2, 'Владимирович', 'Александр', '456', 'Малявский', 'student');

INSERT INTO users_roles (users_id, roles_id) VALUES
  (1, 1),
  (2, 2);

INSERT INTO timetables (id, timetable_day_id, timetable_group_id, timetable_pair_id, timetable_subject_id, timetable_teacher_id)
VALUES
  --   Иванов
  (1, 1, 1, 1, 1, 1),
  (2, 2, 1, 3, 1, 1),
  (3, 3, 2, 2, 1, 1),
  (4, 4, 3, 5, 1, 1),
  (5, 5, 1, 2, 1, 1),
  (6, 6, 3, 1, 1, 1);

INSERT INTO journals (id, date, rating, student, subject, timetable) VALUES
  --   Малявский математика
  (1, now()::date, 5, 1, 1, 1),
  (2, now()::date, 3, 1, 1, 5),
  (3, now()::date, 3, 1, 1, 2);
