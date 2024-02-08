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
	private String formattedTime;
	private String auditorium;
	private String house;
	private int year;

	public LessonDto(Lesson lesson) {
		this.id = lesson.getId();
		this.subject = lesson.getSubject().getName();
		this.professorName = lesson.getProfessor().getName();
		this.professorLastName = lesson.getProfessor().getLastName();
		this.formattedTime = formatTime(lesson.getTime());
		this.auditorium = lesson.getAuditorium().getName();
		this.house = lesson.getHouse().getHouse();
		this.year = lesson.getYear().getYearValue();
	}

	private String formatTime(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String startTime = dateFormat.format(timestamp);

		Timestamp endTime = new Timestamp(timestamp.getTime() + TimeUnit.HOURS.toMillis(1));
		String endTimeStr = dateFormat.format(endTime);

		return startTime + " - " + endTimeStr;
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

	public String getFormattedTime() {
		return formattedTime;
	}

	public void setFormattedTime(String formattedTime) {
		this.formattedTime = formattedTime;
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

	@Override
	public String toString() {
		return "LessonDto [id=" + id + ", subject=" + subject + ", professorName=" + professorName
				+ ", professorLastName=" + professorLastName + ", formattedTime=" + formattedTime + ", auditorium="
				+ auditorium + ", house=" + house + ", year=" + year + "]";
	}

}
