-- Insert sample Roles

INSERT INTO Roles (roleName) VALUES
('Admin'),
('Student'),
('Teacher');

-- Insert sample Grades

INSERT INTO Grades (grade) VALUES
('A'),
('B'),
('C');

-- Insert sample Teachers

INSERT INTO Users (login, password, name, lastName, roleId) VALUES
('teacher1', 'teacher1', 'John', 'Doe', (SELECT id FROM Roles WHERE roleName = 'Teacher')),
('teacher2', 'teacher2', 'Jane', 'Smith', (SELECT id FROM Roles WHERE roleName = 'Teacher')),
('teacher3', 'teacher3', 'Bob', 'Johnson', (SELECT id FROM Roles WHERE roleName = 'Teacher')),
('teacher4', 'teacher4', 'Alice', 'Williams', (SELECT id FROM Roles WHERE roleName = 'Teacher'));

-- Insert sample Students

INSERT INTO Users (login, password, name, lastName, roleId, gradeId) VALUES
('student1', 'student1', 'Alice', 'Johnson', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'A')),
('student2', 'student2', 'Bob', 'Smith', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'B')),
('student3', 'student3', 'Charlie', 'Brown', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'C')),
('student4', 'student4', 'David', 'Wilson', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'A')),
('student5', 'student5', 'Eva', 'Martin', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'B')),
('student6', 'student6', 'Frank', 'Jones', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'C')),
('student7', 'student7', 'Grace', 'White', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'A')),
('student8', 'student8', 'Henry', 'Miller', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'B')),
('student9', 'student9', 'Ivy', 'Taylor', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'C')),
('student10', 'student10', 'Jack', 'Brown', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'A')),
('student11', 'student11', 'Katie', 'Harris', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'B')),
('student12', 'student12', 'Leo', 'Wilson', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'C')),
('student13', 'student13', 'Mia', 'Johnson', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'A')),
('student14', 'student14', 'Nick', 'Smith', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'B')),
('student15', 'student15', 'Olivia', 'Brown', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'C')),
('student16', 'student16', 'Peter', 'Martin', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'A')),
('student17', 'student17', 'Quinn', 'Taylor', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'B')),
('student18', 'student18', 'Rachel', 'Jones', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'C')),
('student19', 'student19', 'Sam', 'White', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'A')),
('student20', 'student20', 'Tom', 'Miller', (SELECT id FROM Roles WHERE roleName = 'Student'), (SELECT id FROM Grades WHERE grade = 'B'));

-- Insert sample Subjects

INSERT INTO Subjects (name, description) VALUES
('Math', 'Advanced calculus'),
('Physics', 'Quantum mechanics'),
('History', 'World history'),
('English', 'Literature and language arts');

-- Insert sample Auditoriums

INSERT INTO Auditoriums (id, name) VALUES
(1, 'A1'),
(2, 'B1'),
(3, 'C1'),
(4, 'D1');

-- Insert sample Admin

INSERT INTO Users (login, password, name, lastName, roleId) VALUES
('admin', 'admin', 'Admin', 'User', (SELECT id FROM Roles WHERE roleName = 'Admin'));

-- Insert sample Enrollments for Students

INSERT INTO Enrollments (userId, subjectId) VALUES
-- Student 1
((SELECT id FROM Users WHERE login = 'student1'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student1'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 2
((SELECT id FROM Users WHERE login = 'student2'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student2'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 3
((SELECT id FROM Users WHERE login = 'student3'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student3'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 4
((SELECT id FROM Users WHERE login = 'student4'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student4'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 5
((SELECT id FROM Users WHERE login = 'student5'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student5'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 6
((SELECT id FROM Users WHERE login = 'student6'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student6'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 7
((SELECT id FROM Users WHERE login = 'student7'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student7'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 8
((SELECT id FROM Users WHERE login = 'student8'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student8'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 9
((SELECT id FROM Users WHERE login = 'student9'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student9'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 10
((SELECT id FROM Users WHERE login = 'student10'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student10'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 11
((SELECT id FROM Users WHERE login = 'student11'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student11'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 12
((SELECT id FROM Users WHERE login = 'student12'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student12'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 13
((SELECT id FROM Users WHERE login = 'student13'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student13'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 14
((SELECT id FROM Users WHERE login = 'student14'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student14'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 15
((SELECT id FROM Users WHERE login = 'student15'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student15'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 16
((SELECT id FROM Users WHERE login = 'student16'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student16'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 17
((SELECT id FROM Users WHERE login = 'student17'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student17'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 18
((SELECT id FROM Users WHERE login = 'student18'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student18'), (SELECT id FROM Subjects WHERE name = 'English')),
-- Student 19
((SELECT id FROM Users WHERE login = 'student19'), (SELECT id FROM Subjects WHERE name = 'Math')),
((SELECT id FROM Users WHERE login = 'student19'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Student 20
((SELECT id FROM Users WHERE login = 'student20'), (SELECT id FROM Subjects WHERE name = 'History')),
((SELECT id FROM Users WHERE login = 'student20'), (SELECT id FROM Subjects WHERE name = 'English'));

-- Insert sample Enrollments for Teachers

INSERT INTO Enrollments (userId, subjectId) VALUES
-- Teacher 1
((SELECT id FROM Users WHERE login = 'teacher1'), (SELECT id FROM Subjects WHERE name = 'Math')),
-- Teacher 2
((SELECT id FROM Users WHERE login = 'teacher2'), (SELECT id FROM Subjects WHERE name = 'Physics')),
-- Teacher 3
((SELECT id FROM Users WHERE login = 'teacher3'), (SELECT id FROM Subjects WHERE name = 'History')),
-- Teacher 4
((SELECT id FROM Users WHERE login = 'teacher4'), (SELECT id FROM Subjects WHERE name = 'English'));

-- Insert sample Lessons

INSERT INTO Lessons (subjectId, teacherId, time, auditoriumId, gradeId) VALUES
-- Lesson 1
((SELECT id FROM Subjects WHERE name = 'Math'), (SELECT id FROM Users WHERE login = 'teacher1'), '2024-01-20 10:00:00', 1, (SELECT id FROM Grades WHERE grade = 'A')),
-- Lesson 2
((SELECT id FROM Subjects WHERE name = 'Physics'), (SELECT id FROM Users WHERE login = 'teacher2'), '2024-01-21 11:00:00', 2, (SELECT id FROM Grades WHERE grade = 'B')),
-- Lesson 3
((SELECT id FROM Subjects WHERE name = 'History'), (SELECT id FROM Users WHERE login = 'teacher3'), '2024-01-22 12:00:00', 3, (SELECT id FROM Grades WHERE grade = 'C')),
-- Lesson 4
((SELECT id FROM Subjects WHERE name = 'English'), (SELECT id FROM Users WHERE login = 'teacher4'), '2024-01-23 13:00:00', 4, (SELECT id FROM Grades WHERE grade = 'A')),
-- Lesson 5
((SELECT id FROM Subjects WHERE name = 'Math'), (SELECT id FROM Users WHERE login = 'teacher1'), '2024-01-24 14:00:00', 1, (SELECT id FROM Grades WHERE grade = 'B')),
-- Lesson 6
((SELECT id FROM Subjects WHERE name = 'Physics'), (SELECT id FROM Users WHERE login = 'teacher2'), '2024-01-25 15:00:00', 2, (SELECT id FROM Grades WHERE grade = 'C')),
-- Lesson 7
((SELECT id FROM Subjects WHERE name = 'History'), (SELECT id FROM Users WHERE login = 'teacher3'), '2024-01-26 16:00:00', 3, (SELECT id FROM Grades WHERE grade = 'A')),
-- Lesson 8
((SELECT id FROM Subjects WHERE name = 'English'), (SELECT id FROM Users WHERE login = 'teacher4'), '2024-01-27 17:00:00', 4, (SELECT id FROM Grades WHERE grade = 'B')),
-- Lesson 9
((SELECT id FROM Subjects WHERE name = 'Math'), (SELECT id FROM Users WHERE login = 'teacher1'), '2024-01-28 18:00:00', 1, (SELECT id FROM Grades WHERE grade = 'C')),
-- Lesson 10
((SELECT id FROM Subjects WHERE name = 'Physics'), (SELECT id FROM Users WHERE login = 'teacher2'), '2024-01-29 19:00:00', 2, (SELECT id FROM Grades WHERE grade = 'A'));