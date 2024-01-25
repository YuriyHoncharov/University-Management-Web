-- Insert sample Roles

INSERT INTO Roles (roleName) VALUES
('Headmaster'),
('Student'),
('Professor');

-- Insert sample Houses

INSERT INTO Houses (house) VALUES
('Gryffindor'),
('Hufflepuff'),
('Ravenclaw'),
('Slytherin');

-- Insert sample Professors
INSERT INTO Wizards (login, password, name, lastName, roleId) VALUES
('mcgonagall', 'mcgonagall', 'Minerva', 'McGonagall', 3),  -- Transfiguration
('snape', 'snape', 'Severus', 'Snape', 3),  -- Potions
('flitwick', 'flitwick', 'Filius', 'Flitwick', 3),  -- Charms
('sprout', 'sprout', 'Pomona', 'Sprout', 3);  -- Herbology

-- Insert sample Students (Harry Potter-themed)

INSERT INTO Wizards (login, password, name, lastName, roleId, houseId) VALUES

-- Gryffindor

('harry', 'harry', 'Harry', 'Potter', 2, 1),
('hermione', 'hermione', 'Hermione', 'Granger', 2, 1),
('ron', 'ron', 'Ron', 'Weasley', 2, 1),
('ginny', 'ginny', 'Ginny', 'Weasley', 2, 1),
('lily', 'lily', 'Lily', 'Evans', 2, 1),
('sirius', 'sirius', 'Sirius', 'Black', 2, 1),
('fred', 'fred', 'Fred', 'Weasley', 2, 1),
('george', 'george', 'George', 'Weasley', 2, 1),
('neville', 'neville', 'Neville', 'Longbottom', 2, 1),
('seamus', 'seamus', 'Seamus', 'Finnigan', 2, 1),

-- Hufflepuff

('cedric3', 'cedric3', 'Cedric', 'Diggory', 2, 2),
('nymphadora2', 'nymphadora2', 'Nymphadora', 'Tonks', 2, 2),
('newton', 'newton', 'Newton', 'Scamander', 2, 2),
('hannah2', 'hannah2', 'Hannah', 'Abbott', 2, 2),
('helga', 'helga', 'Helga', 'Hufflepuff', 2, 2),
('pomona', 'pomona', 'Pomona', 'Sprout', 2, 2),
('ernie2', 'ernie2', 'Ernie', 'Macmillan', 2, 2),
('justin2', 'justin2', 'Justin', 'Finch-Fletchley', 2, 2),
('zacharias2', 'zacharias2', 'Zacharias', 'Smith', 2, 2),
('nymphadora3', 'nymphadora3', 'Nymphadora', 'Tonks', 2, 2),

-- Ravenclaw

('luna3', 'luna3', 'Luna', 'Lovegood', 2, 3),
('cho3', 'cho3', 'Cho', 'Chang', 2, 3),
('padma2', 'padma2', 'Padma', 'Patil', 2, 3),
('parvati3', 'parvati3', 'Parvati', 'Patil', 2, 3),
('terry2', 'terry2', 'Terry', 'Boot', 2, 3),
('michael3', 'michael3', 'Michael', 'Corner', 2, 3),
('megan2', 'megan2', 'Megan', 'Jones', 2, 3),
('lisa2', 'lisa2', 'Lisa', 'Turpin', 2, 3),
('anthony2', 'anthony2', 'Anthony', 'Goldstein', 2, 3),
('terence2', 'terence2', 'Terence', 'Higgs', 2, 3),

-- Slytherin

('draco3', 'draco3', 'Draco', 'Malfoy', 2, 4),
('pansy', 'pansy', 'Pansy', 'Parkinson', 2, 4),
('blaise', 'blaise', 'Blaise', 'Zabini', 2, 4),
('millicent', 'millicent', 'Millicent', 'Bulstrode', 2, 4),
('theodore', 'theodore', 'Theodore', 'Nott', 2, 4),
('daphne', 'daphne', 'Daphne', 'Greengrass', 2, 4),
('vincent', 'vincent', 'Vincent', 'Crabbe', 2, 4),
('gregory', 'gregory', 'Gregory', 'Goyle', 2, 4),
('lucius3', 'lucius3', 'Lucius', 'Malfoy', 2, 4),
('bellatrix3', 'bellatrix3', 'Bellatrix', 'Lestrange', 2, 4),
('narcissa', 'narcissa', 'Narcissa', 'Malfoy', 2, 4),
('rudolphus', 'rudolphus', 'Rudolphus', 'Lestrange', 2, 4);


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
INSERT INTO Wizards (login, password, name, lastName, roleId) VALUES
('dumbledore', 'dumbledore', 'Albus', 'Dumbledore', 1);

-- Insert sample Enrollments for Students
-- Gryffindor

INSERT INTO Enrollments (userId, subjectId) VALUES

(1, 1), (1, 2),
(2, 1), (2, 4),
(3, 1), (3, 3),
(4, 2), (4, 4),
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


-- Insert sample Enrollments for Teachers (Harry Potter-themed)
INSERT INTO Enrollments (userId, subjectId) VALUES
-- Professor McGonagall (Transfiguration)
(1, 1),
-- Professor Snape (Potions)
(2, 2),
-- Professor Flitwick (Charms)
(3, 3),
-- Professor Sprout (Herbology)
(4, 4);

-- Insert sample Lessons
INSERT INTO Lessons (subjectId, teacherId, time, auditoriumId, houseId) VALUES
-- Lesson 1
(1, 1, '2024-01-20 10:00:00', 1, 1),
-- Lesson 2
(2, 2, '2024-01-21 11:00:00', 2, 2),
-- Lesson 3
(3, 3, '2024-01-22 12:00:00', 3, 3),
-- Lesson 4
(4, 4, '2024-01-23 13:00:00', 4, 1),
-- Lesson 5
(1, 1, '2024-01-24 14:00:00', 1, 2),
-- Lesson 6
(2, 2, '2024-01-25 15:00:00', 2, 3),
-- Lesson 7
(3, 3, '2024-01-26 16:00:00', 3, 1),
-- Lesson 8
(4, 4, '2024-01-27 17:00:00', 4, 2),
-- Lesson 9
(1, 1, '2024-01-28 18:00:00', 1, 3),
-- Lesson 10
(2, 2, '2024-01-29 19:00:00', 2, 1),
-- Lesson 11
(3, 3, '2024-01-30 20:00:00', 3, 2),
-- Lesson 12
(4, 4, '2024-02-01 21:00:00', 4, 3),
-- Lesson 13
(1, 1, '2024-02-02 22:00:00', 1, 4),
-- Lesson 14
(2, 2, '2024-02-03 23:00:00', 2, 1),
-- Lesson 15
(3, 3, '2024-02-04 00:00:00', 3, 2),
-- Lesson 16
(4, 4, '2024-02-05 01:00:00', 4, 3),
-- Lesson 17
(1, 1, '2024-02-06 02:00:00', 1, 4),
-- Lesson 18
(2, 2, '2024-02-07 03:00:00', 2, 1),
-- Lesson 19
(3, 3, '2024-02-08 04:00:00', 3, 2),
-- Lesson 20
(4, 4, '2024-02-09 05:00:00', 4, 3);


