CREATE TABLE IF NOT EXISTS Houses (
id SERIAL PRIMARY KEY,
house VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS Roles (
id SERIAL PRIMARY KEY, 
roleName TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Years(
id SERIAL PRIMARY KEY,
yearValue INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Wizards (
id SERIAL PRIMARY KEY,
login TEXT NOT NULL,
password TEXT NOT NULL,
name TEXT NOT NULL,
lastName TEXT NOT NULL,
role_id INT,
house_id INT,
year_id INT,
FOREIGN KEY (house_id) REFERENCES Houses (id) ON DELETE CASCADE,
FOREIGN KEY (role_id) REFERENCES Roles(id) ON DELETE CASCADE,
FOREIGN KEY (year_id) REFERENCES Years(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Subjects (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Auditoriums (
id INT PRIMARY KEY,
name VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS Enrollments (
wizard_id INT,
FOREIGN KEY (wizard_id) REFERENCES Wizards (id) ON DELETE CASCADE,
subject_id INT,
FOREIGN KEY(subject_id) REFERENCES Subjects (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Lessons (
id SERIAL PRIMARY KEY,
subject_id INT NOT NULL,
FOREIGN KEY (subject_id) REFERENCES Subjects (id),
teacher_id INT NOT NULL,
FOREIGN KEY (teacher_id) REFERENCES Wizards (id),
lesson_date DATE NOT NULL,
lesson_time TIME NOT NULL,
lesson_end_time TIME,
auditorium_id INT NOT NULL,
FOREIGN KEY (auditorium_id) REFERENCES Auditoriums (id),
house_id INT NOT NULL,
FOREIGN KEY (house_id) REFERENCES Houses (id) ON DELETE CASCADE,
year_id INT,
FOREIGN KEY (year_id) REFERENCES Years(id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION update_end_time()
RETURNS TRIGGER AS $$
BEGIN
	NEW.lesson_end_time = NEW.lesson_time + interval '45 minutes';
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update_end_time
BEFORE INSERT OR UPDATE ON Lessons
FOR EACH ROW
EXECUTE FUNCTION update_end_time();


