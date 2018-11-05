package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.entity.Rating;
import ru.digitaluniversity.entity.Subject;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * Класс, описывающий DTO сущности журнал.
 */
public class JournalDto {
    private String id;
    private SubjectDto subject;
    private StudentDto student;
    private RatingDto rating;
    private TeacherDto timetableTeacher;
    private Date date;

    public JournalDto() {
    }

    public JournalDto(Journal journal) {
        this.id = journal.getId().toString();
        this.subject = Optional.ofNullable(journal.getJournalSubject())
                .map(SubjectDto::new).orElse(null);
        this.student = Optional.ofNullable(journal.getJournalStudent())
                .map(StudentDto::new).orElse(null);
        this.rating = Optional.ofNullable(journal.getJournalRating())
                .map(RatingDto::new).orElse(null);
        this.timetableTeacher = Optional.ofNullable(journal.getJournalTimetable().getTimetableTeacher())
                .map(TeacherDto::new).orElse(null);
        this.date = journal.getJournalDate();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
