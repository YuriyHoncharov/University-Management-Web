INSERT INTO Roles (roleName) VALUES ('Headmaster');
INSERT INTO Roles (roleName) VALUES ('Student');
INSERT INTO Roles (roleName) VALUES ('Professor');

-- Test data for Houses
INSERT INTO Houses (house) VALUES ('A');
INSERT INTO Houses (house) VALUES ('B');
INSERT INTO Houses (house) VALUES ('C');

-- Insert sample Years

INSERT INTO Years (yearValue) VALUES
(1),
(2),
(3),
(4);

-- Test data for Wizards

INSERT INTO Wizards (login, password, name, lastName, role_id, house_id, year_id) VALUES
('headmaster', 'headmaster123', 'Headmaster', 'User', 1, NULL, NULL),
('student1', 'student123', 'Student', 'One', 2, 1, 1),
('student2', 'student123', 'Student', 'Two', 2, 2, 1),
('teacher1', 'teacher123', 'Professor', 'One', 3, NULL, NULL),
('teacher2', 'teacher123', 'Professor', 'Two', 3, NULL, NULL);

-- Test data for Subjects

INSERT INTO Subjects (name, description) VALUES
('Math', 'Mathematics course'),
('Physics', 'Physics course'),
('History', 'History course');

-- Test data for Auditoriums

INSERT INTO Auditoriums (id, name) VALUES
(1, 'A101'),
(2, 'B202'),
(3, 'C303');

-- Test data for Enrollments

INSERT INTO Enrollments (wizard_id, subject_id) VALUES (2, 1);
INSERT INTO Enrollments (wizard_id, subject_id) VALUES (3, 2);
INSERT INTO Enrollments (wizard_id, subject_id) VALUES (4, 3);

-- Test data for Lessons

INSERT INTO Lessons (subject_id, teacher_id, time, auditorium_id, house_id) VALUES
(1, 4, '2024-01-15 09:00:00', 1, 1),
(2, 5, '2024-01-16 10:30:00', 2, 2),
(3, 4, '2024-01-17 13:45:00', 3, 3);