CREATE TABLE IF NOT EXISTS Users {
id SERIAL PRIMARY KEY,
login TEXT NOT NULL,
password TEXT NOT NULL,
name TEXT NOT NULL,
lastName TEXT NOT NULL,
roleId INT,
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

CREATE TABLE IF NOT EXISTS Admins {
id INT,
FOREIGN KEY (id) REFERENCES Users (id)
};

CREATE TABLE IF NOT EXISTS Students {
gradeId INT,
FOREIGN KEY (gradeId) REFERENCES Grades (id)
id INT,

FOREIGN KEY (id) REFERENCES Users (id)
};

CREATE TABLE IF NOT EXISTS Teachers {
id INT,
FOREIGN KEY (id) REFERENCES Users (id)
};

CREATE TABLE IF NOT EXISTS Subjects {
id PRIMARY KEY,
name TEXT NOT NULL,
description TEXT NOT NULL
};

CREATE TABLE IF NOT EXISTS Auditoriums {
id PRIMARY KEY,
name VARCHAR(5) NOT NULL
};

CREATE TABLE IF NOT EXISTS Enrollment {
userId INT,
FOREIGN KEY (userId) REFERENCES Users (id),
subjectId INT,
FOREIGN KEY(subjectId) REFERENCES Subjects (id)
};

CREATE TABLE IF NOT EXISTS Lessons {
subjectId INT,
FOREIGN KEY (subjectId) REFERENCES Subjects (id),
teacherId INT,
FOREIGN KEY (teacherId) REFERENCES Teachers (id),
time TIMESTAMP NOT NULL,
auditoriumId INT,
FOREIGN KEY (auditoriumId) REFERENCES Auditoriums (id),
gradeId INT,
FOREIGN KEY (gradeId) REFERENCES Grades (id)
};