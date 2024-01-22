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
('teacher1', 'teacher1', 'John', 'Doe', 3),
('teacher2', 'teacher2', 'Jane', 'Smith', 3),
('teacher3', 'teacher3', 'Bob', 'Johnson', 3),
('teacher4', 'teacher4', 'Alice', 'Williams', 3);

-- Insert sample Students

INSERT INTO Users (login, password, name, lastName, roleId, gradeId) VALUES
('student1', 'student1', 'Alice', 'Johnson', 2, 1),
('student2', 'student2', 'Bob', 'Smith', 2, 2),
('student3', 'student3', 'Charlie', 'Brown', 2, 3),
('student4', 'student4', 'David', 'Wilson', 2, 1),
('student5', 'student5', 'Eva', 'Martin', 2, 2),
('student6', 'student6', 'Frank', 'Jones', 2, 3),
('student7', 'student7', 'Grace', 'White', 2, 1),
('student8', 'student8', 'Henry', 'Miller', 2, 2),
('student9', 'student9', 'Ivy', 'Taylor', 2, 3),
('student10', 'student10', 'Jack', 'Brown', 2, 1),
('student11', 'student11', 'Katie', 'Harris', 2, 2),
('student12', 'student12', 'Leo', 'Wilson', 2, 3),
('student13', 'student13', 'Mia', 'Johnson', 2, 1),
('student14', 'student14', 'Nick', 'Smith', 2, 2),
('student15', 'student15', 'Olivia', 'Brown', 2, 3),
('student16', 'student16', 'Peter', 'Martin', 2, 1),
('student17', 'student17', 'Quinn', 'Taylor', 2, 2),
('student18', 'student18', 'Rachel', 'Jones', 2, 3),
('student19', 'student19', 'Sam', 'White', 2, 1),
('student20', 'student20', 'Tom', 'Miller', 2, 2);

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
('admin', 'admin', 'Admin', 'User', 1);

-- Insert sample Enrollments for Students

INSERT INTO Enrollments (userId, subjectId) VALUES
-- Student 1
(1, 1),
(1, 2),
-- Student 2
(2, 3),
(2, 4),
-- Student 3
(3, 1),
(3, 2),
-- Student 4
(4, 3),
(4, 4),
-- Student 5
(5, 1),
(5, 2),
-- Student 6
(6, 3),
(6, 4),
-- Student 7
(7, 1),
(7, 2),
-- Student 8
(8, 3),
(8, 4),
-- Student 9
(9, 1),
(9, 2),
-- Student 10
(10, 3),
(10, 4),
-- Student 11
(11, 1),
(11, 2),
-- Student 12
(12, 3),
(12, 4),
-- Student 13
(13, 1),
(13, 2),
-- Student 14
(14, 3),
(14, 4),
-- Student 15
(15, 1),
(15, 2),
-- Student 16
(16, 3),
(16, 4),
-- Student 17
(17, 1),
(17, 2),
-- Student 18
(18, 3),
(18, 4),
-- Student 19
(19, 1),
(19, 2),
-- Student 20
(20, 3),
(20, 4);

-- Insert sample Enrollments for Teachers

INSERT INTO Enrollments (userId, subjectId) VALUES
-- Teacher 1 (Math)
(1, 1),
-- Teacher 2 (Physics)
(2, 2),
-- Teacher 3 (History)
(3, 3),
-- Teacher 4 (English)
(4, 4);

-- Insert sample Lessons

INSERT INTO Lessons (subjectId, teacherId, time, auditoriumId, gradeId) VALUES
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
(2, 2, '2024-01-29 19:00:00', 2, 1);