package ua.com.foxminded.yuriy.schedulewebapp.entity.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import ua.com.foxminded.yuriy.schedulewebapp.entity.Lesson;

public class LessonDto {

	private Long id;
	private String subject;
	private String professorName;
	private String professorLastName;
	private String auditorium;
	private String house;
	private String date;
	private String startTime;
	private String endTime;
	private int year;

	public LessonDto(Lesson lesson) {
		this.id = lesson.getId();
		this.subject = lesson.getSubject().getName();
		this.professorName = lesson.getProfessor().getName();
		this.professorLastName = lesson.getProfessor().getLastName();
		this.auditorium = lesson.getAuditorium().getName();
		this.house = lesson.getHouse().getHouse();
		this.year = lesson.getYear().getYearValue();
		Timestamp startTimeStamp = lesson.getTime();
		this.date = formatDate(startTimeStamp);
		this.startTime = formatTime(startTimeStamp);
		this.endTime = formatTime(new Timestamp(startTimeStamp.getTime() + TimeUnit.HOURS.toMillis(1)));

	}

	private String formatDate(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(timestamp);
	}

	private String formatTime(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.format(timestamp);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getProfessorLastName() {
		return professorLastName;
	}

	public void setProfessorLastName(String professorLastName) {
		this.professorLastName = professorLastName;
	}

	public String getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(String auditorium) {
		this.auditorium = auditorium;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
