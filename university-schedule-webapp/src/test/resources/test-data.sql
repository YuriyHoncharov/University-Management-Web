-- Test data for Grades
INSERT INTO Grades (grade) VALUES ('A');
INSERT INTO Grades (grade) VALUES ('B');
INSERT INTO Grades (grade) VALUES ('C');

-- Test data for Users

INSERT INTO Users (login, password, name, lastName, roleId, gradeId) VALUES
('admin', 'admin123', 'Admin', 'User', 1, NULL),
('student1', 'student123', 'Student', 'One', 2, 1),
('student2', 'student123', 'Student', 'Two', 2, 2),
('teacher1', 'teacher123', 'Teacher', 'One', 3, NULL),
('teacher2', 'teacher123', 'Teacher', 'Two', 3, NULL);

-- Test data for Subjects

INSERT INTO Subjects (id, name, description) VALUES
(1, 'Math', 'Mathematics course'),
(2, 'Physics', 'Physics course'),
(3, 'History', 'History course');

-- Test data for Auditoriums

INSERT INTO Auditoriums (id, name) VALUES
(1, 'A101'),
(2, 'B202'),
(3, 'C303');

-- Test data for Enrollments

INSERT INTO Enrollments (userId, subjectId) VALUES (2, 1);
INSERT INTO Enrollments (userId, subjectId) VALUES (3, 2);
INSERT INTO Enrollments (userId, subjectId) VALUES (4, 3);

-- Test data for Lessons

INSERT INTO Lessons (subjectId, teacherId, time, auditoriumId, gradeId) VALUES
(1, 4, '2024-01-15 09:00:00', 1, 1),
(2, 5, '2024-01-16 10:30:00', 2, 2),
(3, 4, '2024-01-17 13:45:00', 3, 3);