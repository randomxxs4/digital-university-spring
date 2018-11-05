package ru.digitaluniversity.dto;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Класс, описывающий DTO сущности журнал.
 */
public class JournalDto {
    private String id;
    private SubjectDto subject;
    private StudentDto student;
    private RatingDto rating;
    private TeacherDto timetableTeacher;
//    private String date;
    private Date date;
    private TimetableDto timetable;

    private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public JournalDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public RatingDto getRating() {
        return rating;
    }

    public void setRating(RatingDto rating) {
        this.rating = rating;
    }

    public TeacherDto getTimetableTeacher() {
        return timetableTeacher;
    }

    public void setTimetableTeacher(TeacherDto timetableTeacher) {
        this.timetableTeacher = timetableTeacher;
    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        this.date = simpleDateFormat.format(date);
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TimetableDto getTimetable() {
        return timetable;
    }

    public void setTimetable(TimetableDto timetable) {
        this.timetable = timetable;
    }
}
