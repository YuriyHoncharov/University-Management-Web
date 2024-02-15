-- Insert sample Roles

INSERT INTO Roles (roleName) VALUES
('HEADMASTER'),
('STUDENT'),
('PROFESSOR');

-- Insert sample Houses

INSERT INTO Houses (house) VALUES
('Gryffindor'),
('Hufflepuff'),
('Ravenclaw'),
('Slytherin');

-- Insert sample Years

INSERT INTO Years (yearValue) VALUES
(1),
(2),
(3),
(4);

-- Insert sample Professors
INSERT INTO Wizards (login, password, name, lastName, role_id) VALUES
('mcgonagall', 'mcgonagall', 'Minerva', 'McGonagall', 3),  -- Transfiguration
('snape', '$2a$12$PK5.Fq62gjipWGW5fPjb4OhlLC22uyoJGxPeWl1mVT5SZShNoXlEm', 'Severus', 'Snape', 3),  -- Potions
('flitwick', 'flitwick', 'Filius', 'Flitwick', 3),  -- Charms
('sprout', 'sprout', 'Pomona', 'Sprout', 3);  -- Herbology

-- Insert sample Students (Harry Potter-themed)

INSERT INTO Wizards (login, password, name, lastName, role_id, house_id, year_id) VALUES

-- Gryffindor

('harry', '$2a$12$tI5MAdTqct0W1yr8eVUWee.FWLfDjkBykC4sr.UNiOL18E95Ha.QW', 'Harry', 'Potter', 2, 1, 1),
('hermione', 'hermione', 'Hermione', 'Granger', 2, 1, 1),
('ron', 'ron', 'Ron', 'Weasley', 2, 1, 1),
('ginny', 'ginny', 'Ginny', 'Weasley', 2, 1, 1),
('lily', 'lily', 'Lily', 'Evans', 2, 1, 2),
('sirius', 'sirius', 'Sirius', 'Black', 2, 1, 2),
('fred', 'fred', 'Fred', 'Weasley', 2, 1, 3),
('george', 'george', 'George', 'Weasley', 2, 1, 3),
('neville', 'neville', 'Neville', 'Longbottom', 2, 1, 4),
('seamus', 'seamus', 'Seamus', 'Finnigan', 2, 1, 4),

-- Hufflepuff

('cedric3', 'cedric3', 'Cedric', 'Diggory', 2, 2, 1),
('nymphadora2', 'nymphadora2', 'Nymphadora', 'Tonks', 2, 2, 1),
('newton', 'newton', 'Newton', 'Scamander', 2, 2, 1),
('hannah2', 'hannah2', 'Hannah', 'Abbott', 2, 2, 2),
('helga', 'helga', 'Helga', 'Hufflepuff', 2, 2, 2),
('pomona', 'pomona', 'Pomona', 'Sprout', 2, 2, 2),
('ernie2', 'ernie2', 'Ernie', 'Macmillan', 2, 2, 2),
('justin2', 'justin2', 'Justin', 'Finch-Fletchley', 2, 2, 3),
('zacharias2', 'zacharias2', 'Zacharias', 'Smith', 2, 2, 3),
('nymphadora3', 'nymphadora3', 'Nymphadora', 'Tonks', 2, 2, 4),

-- Ravenclaw

('luna3', 'luna3', 'Luna', 'Lovegood', 2, 3, 1),
('cho3', 'cho3', 'Cho', 'Chang', 2, 3, 1),
('padma2', 'padma2', 'Padma', 'Patil', 2, 3, 1),
('parvati3', 'parvati3', 'Parvati', 'Patil', 2, 3, 2),
('terry2', 'terry2', 'Terry', 'Boot', 2, 3, 2),
('michael3', 'michael3', 'Michael', 'Corner', 2, 3, 2),
('megan2', 'megan2', 'Megan', 'Jones', 2, 3, 3),
('lisa2', 'lisa2', 'Lisa', 'Turpin', 2, 3, 3),
('anthony2', 'anthony2', 'Anthony', 'Goldstein', 2, 3, 4),
('terence2', 'terence2', 'Terence', 'Higgs', 2, 3, 4),

-- Slytherin

('draco3', 'draco3', 'Draco', 'Malfoy', 2, 4, 1),
('pansy', 'pansy', 'Pansy', 'Parkinson', 2, 4, 1),
('blaise', 'blaise', 'Blaise', 'Zabini', 2, 4, 1),
('millicent', 'millicent', 'Millicent', 'Bulstrode', 2, 4, 2),
('theodore', 'theodore', 'Theodore', 'Nott', 2, 4, 2),
('daphne', 'daphne', 'Daphne', 'Greengrass', 2, 4, 2),
('vincent', 'vincent', 'Vincent', 'Crabbe', 2, 4, 2),
('gregory', 'gregory', 'Gregory', 'Goyle', 2, 4, 3),
('lucius3', 'lucius3', 'Lucius', 'Malfoy', 2, 4, 3),
('bellatrix3', 'bellatrix3', 'Bellatrix', 'Lestrange', 2, 4, 4),
('narcissa', 'narcissa', 'Narcissa', 'Malfoy', 2, 4, 4),
('rudolphus', 'rudolphus', 'Rudolphus', 'Lestrange', 2, 4, 4);


-- Insert sample Subjects

INSERT INTO Subjects (name, description) VALUES
('Transfiguration', 'Changing the form or appearance of an object'),
('Potions', 'Magical mixtures and elixirs'),
('Charms', 'Spells and incantations'),
('Herbology', 'Study of magical plants and fungi');


-- Insert sample Auditoriums

INSERT INTO Auditoriums (id, name) VALUES
(1, 'Room of Requirement'),
(2, 'Great Hall'),
(3, 'Chamber of Secrets'),
(4, 'Forbidden Forest');

-- Insert Headmaster Dumbledore
INSERT INTO Wizards (login, password, name, lastName, role_id) VALUES
('dumbledore', '$2a$12$s.4vLeV2gdXa5HttrTwmoO82GXqiUFxB0lwV58kOjr1pmmnoWjDdu', 'Albus', 'Dumbledore', 1);

-- Insert sample Enrollments for Students
-- Gryffindor

INSERT INTO Enrollments (wizard_id, subject_id) VALUES

(5, 2), (5, 3),
(6, 3), (6, 4),
(7, 2), (7, 4),
(8, 1), (8, 3),
(9, 1), (9, 2),
(10, 3), (10, 4),

-- Hufflepuff

(11, 1), (11, 4),
(12, 2), (12, 3),
(13, 1), (13, 2),
(14, 3), (14, 4),
(15, 1), (15, 3),
(16, 2), (16, 4),
(17, 3), (17, 4),
(18, 1), (18, 2),
(19, 2), (19, 3),
(20, 1), (20, 4),

-- Ravenclaw

(21, 1), (21, 2),
(22, 2), (22, 3),
(23, 3), (23, 4),
(24, 1), (24, 4),
(25, 2), (25, 3),
(26, 1), (26, 2),
(27, 2), (27, 3),
(28, 3), (28, 4),
(29, 1), (29, 4),
(30, 1), (30, 3),

-- Slytherin

(31, 1), (31, 4),
(32, 2), (32, 4),
(33, 3), (33, 4),
(34, 1), (34, 2),
(35, 1), (35, 3),
(36, 2), (36, 3),
(37, 3), (37, 4),
(38, 1), (38, 2),
(39, 2), (39, 3),
(40, 1), (40, 4);


-- Insert sample Enrollments for Teachers
INSERT INTO Enrollments (wizard_id, subject_id) VALUES
-- Professor McGonagall (Transfiguration)
(1, 1),
-- Professor Snape (Potions)
(2, 2),
-- Professor Flitwick (Charms)
(3, 3),
-- Professor Sprout (Herbology)
(4, 4);

-- Insert sample Lessons
INSERT INTO Lessons (subject_id, teacher_id, time, auditorium_id, house_id, year_id) VALUES
-- Lesson 1
(1, 1, '2024-01-20 10:00:00', 1, 1, 1),
-- Lesson 2
(2, 2, '2024-01-21 11:00:00', 2, 2, 2),
-- Lesson 3
(3, 3, '2024-01-22 12:00:00', 3, 3, 3),
-- Lesson 4
(4, 4, '2024-01-23 13:00:00', 4, 1, 4),
-- Lesson 5
(1, 1, '2024-01-24 14:00:00', 1, 2, 1),
-- Lesson 6
(2, 2, '2024-01-25 15:00:00', 2, 3, 2),
-- Lesson 7
(3, 3, '2024-01-26 16:00:00', 3, 1, 3),
-- Lesson 8
(4, 4, '2024-01-27 17:00:00', 4, 2, 4),
-- Lesson 9
(1, 1, '2024-01-28 18:00:00', 1, 3, 1),
-- Lesson 10
(2, 2, '2024-01-29 19:00:00', 2, 1, 2),
-- Lesson 11
(3, 3, '2024-01-30 20:00:00', 3, 2, 3),
-- Lesson 12
(4, 4, '2024-02-01 21:00:00', 4, 3, 4),
-- Lesson 13
(1, 1, '2024-02-02 22:00:00', 1, 4, 1),
-- Lesson 14
(2, 2, '2024-02-03 23:00:00', 2, 1, 2),
-- Lesson 15
(3, 3, '2024-02-04 00:00:00', 3, 2, 3),
-- Lesson 16
(4, 4, '2024-02-05 01:00:00', 4, 3, 4),
-- Lesson 17
(1, 1, '2024-02-06 02:00:00', 1, 4, 1),
-- Lesson 18
(2, 2, '2024-02-07 03:00:00', 2, 1, 2),
-- Lesson 19
(3, 3, '2024-02-08 04:00:00', 3, 2, 3),
-- Lesson 20
(4, 4, '2024-02-09 05:00:00', 4, 3, 4),
-- Lesson 21
(1, 1, '2024-02-10 06:00:00', 1, 4, 1),
-- Lesson 22
(2, 2, '2024-02-11 07:00:00', 2, 1, 2),
-- Lesson 23
(3, 3, '2024-02-12 08:00:00', 3, 2, 3),
-- Lesson 24
(4, 4, '2024-02-13 09:00:00', 4, 3, 4),
-- Lesson 25
(1, 1, '2024-02-14 10:00:00', 1, 4, 1),
-- Lesson 26
(2, 2, '2024-02-15 11:00:00', 2, 2, 2),
-- Lesson 27
(3, 3, '2024-02-16 12:00:00', 3, 3, 3),
-- Lesson 28
(4, 4, '2024-02-17 13:00:00', 4, 1, 4),
-- Lesson 29
(1, 1, '2024-02-18 14:00:00', 1, 2, 1),
-- Lesson 30
(2, 2, '2024-02-19 15:00:00', 2, 3, 2),
-- Lesson 31
(3, 3, '2024-02-20 16:00:00', 3, 1, 3),
-- Lesson 32
(4, 4, '2024-02-21 17:00:00', 4, 2, 4),
-- Lesson 33
(1, 1, '2024-02-22 18:00:00', 1, 3, 1),
-- Lesson 34
(2, 2, '2024-02-23 19:00:00', 2, 1, 2),
-- Lesson 35
(3, 3, '2024-02-24 20:00:00', 3, 2, 3),
-- Lesson 36
(4, 4, '2024-02-25 21:00:00', 4, 3, 4),
-- Lesson 37
(1, 1, '2024-02-26 22:00:00', 1, 4, 1),
-- Lesson 38
(2, 2, '2024-02-27 23:00:00', 2, 1, 2),
-- Lesson 39
(3, 3, '2024-02-28 00:00:00', 3, 2, 3),
-- Lesson 40
(4, 4, '2024-02-29 01:00:00', 4, 3, 4),
-- Lesson 41
(1, 1, '2024-03-01 02:00:00', 1, 4, 1),
-- Lesson 42
(2, 2, '2024-03-02 03:00:00', 2, 1, 2),
-- Lesson 43
(3, 3, '2024-03-03 04:00:00', 3, 2, 3),
-- Lesson 44
(4, 4, '2024-03-04 05:00:00', 4, 3, 4),
-- Lesson 45
(1, 1, '2024-03-05 06:00:00', 1, 1, 1),
-- Lesson 46
(2, 2, '2024-03-06 07:00:00', 2, 2, 2),
-- Lesson 47
(3, 3, '2024-03-07 08:00:00', 3, 3, 3),
-- Lesson 48
(4, 4, '2024-03-08 09:00:00', 4, 1, 4),
-- Lesson 49
(1, 1, '2024-03-09 10:00:00', 1, 2, 1),
-- Lesson 50
(2, 2, '2024-03-10 11:00:00', 2, 3, 2),
-- Lesson 51
(3, 3, '2024-03-11 12:00:00', 3, 1, 3),
-- Lesson 52
(4, 4, '2024-03-12 13:00:00', 4, 2, 4),
-- Lesson 53
(1, 1, '2024-03-13 14:00:00', 1, 3, 1),
-- Lesson 54
(2, 2, '2024-03-14 15:00:00', 2, 1, 2),
-- Lesson 55
(3, 3, '2024-03-15 16:00:00', 3, 2, 3),
-- Lesson 56
(4, 4, '2024-03-16 17:00:00', 4, 3, 4),
-- Lesson 57
(1, 1, '2024-03-17 18:00:00', 1, 4, 1),
-- Lesson 58
(2, 2, '2024-03-18 19:00:00', 2, 1, 2),
-- Lesson 59
(3, 3, '2024-03-19 20:00:00', 3, 2, 3),
-- Lesson 60
(4, 4, '2024-03-20 21:00:00', 4, 3, 4),
-- Lesson 61
(1, 1, '2024-03-21 22:00:00', 1, 4, 1),
-- Lesson 62
(2, 2, '2024-03-22 23:00:00', 2, 1, 2),
-- Lesson 63
(3, 3, '2024-03-23 00:00:00', 3, 2, 3),
-- Lesson 64
(4, 4, '2024-03-24 01:00:00', 4, 3, 4),
-- Lesson 65
(1, 1, '2024-03-25 02:00:00', 1, 1, 1),
-- Lesson 66
(2, 2, '2024-03-26 03:00:00', 2, 2, 2),
-- Lesson 67
(3, 3, '2024-03-27 04:00:00', 3, 3, 3),
-- Lesson 68
(4, 4, '2024-03-28 05:00:00', 4, 1, 4),
-- Lesson 69
(1, 1, '2024-03-29 06:00:00', 1, 2, 1),
-- Lesson 70
(2, 2, '2024-03-30 07:00:00', 2, 3, 2),
-- Lesson 71
(2, 2, '2024-03-30 10:00:00', 1, 1, 1),
-- Lesson 72
(3, 3, '2024-03-30 12:00:00', 1, 1, 1);
