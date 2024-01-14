CREATE TABLE IF NOT EXISTS Users {
id SERIAL PRIMARY KEY,
login TEXT NOT NULL,
password TEXT NOT NULL,
name TEXT NOT NULL,
lastName TEXT NOT NULL,
roleId INT,
gradeId INT,
FOREIGN KEY (gradeId) REFERENCES Grades (id),
FOREIGN KEY (roleId) REFERENCES Roles(id)
};

CREATE TABLE IF NOT EXISTS Roles {
id INT PRIMARY KEY, 
roleName TEXT NOT NULL UNIQUE
};

CREATE TABLE IF NOT EXISTS Grades {
id SERIAL PRIMARY KEY,
value VARCHAR(5) NOT NULL
};

INSERT INTO Roles (id, roleName) VALUES (1, 'Admin');
INSERT INTO Roles (id, roleName) VALUES (2, 'Student');
INSERT INTO Roles (id, roleName) VALUES (3, 'Teacher');

CREATE TABLE IF NOT EXISTS Subjects {
id PRIMARY KEY,
name TEXT NOT NULL,
description TEXT NOT NULL
};

CREATE TABLE IF NOT EXISTS Auditoriums {
id PRIMARY KEY,
name VARCHAR(5) NOT NULL
};

CREATE TABLE IF NOT EXISTS Enrollments {
userId INT,
FOREIGN KEY (userId) REFERENCES Users (id),
subjectId INT,
FOREIGN KEY(subjectId) REFERENCES Subjects (id)
};

CREATE TABLE IF NOT EXISTS Lessons {
id SERIAL PRIMARY KEY,
subjectId INT NOT NULL,
FOREIGN KEY (subjectId) REFERENCES Subjects (id),
teacherId INT NOT NULL,
FOREIGN KEY (teacherId) REFERENCES Teachers (id),
time TIMESTAMP NOT NULL,
auditoriumId INT NOT NULL,
FOREIGN KEY (auditoriumId) REFERENCES Auditoriums (id),
gradeId INT NOT NULL,
FOREIGN KEY (gradeId) REFERENCES Grades (id)
};