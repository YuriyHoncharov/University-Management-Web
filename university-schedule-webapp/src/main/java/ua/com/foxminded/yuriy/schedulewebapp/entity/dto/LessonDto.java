package ua.com.foxminded.yuriy.schedulewebapp.entity.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Auditorium;
import ua.com.foxminded.yuriy.schedulewebapp.entity.House;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Professor;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Subject;
import ua.com.foxminded.yuriy.schedulewebapp.entity.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LessonDto {

	private Long id;
	private String subject;
	private String professorName;
	private String professorLastName;
	private String auditorium;
	private String house;
	private String date;
	private String time;
	private String endTime;
	private String year;

	public LessonDto(Lesson lesson) {
		this.id = lesson.getId();
		this.subject = lesson.getSubject().getName();
		this.professorName = lesson.getProfessor().getName();
		this.professorLastName = lesson.getProfessor().getLastName();
		this.auditorium = lesson.getAuditorium().getName();
		if(lesson.getHouse() != null) {
			this.house = lesson.getHouse().getHouse();
		} else {
			this.house = "Waiting for Assignement";
		}
		if(lesson.getYear() != null) {
			this.year =String.valueOf(lesson.getYear().getYearValue());
		} else {
			this.year = "Waiting for Assignement";
		}
		this.date = lesson.getDate().toString();
		this.time = lesson.getTime().toString();
		this.endTime = lesson.getEndTime().toString();

	}

	private String formatDate(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(timestamp);
	}

	private String formatTime(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.format(timestamp);
	}
}
