package ru.digitaluniversity.entity;

import javax.persistence.*;

@Entity
@Table(name = "timetables")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Teacher timetableTeacher;
    @ManyToOne
    private Group timetableGroup;
    @ManyToOne
    private Subject timetableSubject;
    @ManyToOne
    private Day timetableDay;
    @ManyToOne
    private Pair timetablePair;
    @ManyToOne
    private Journal timetableJournal;

    public Timetable() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTimetableTeacher() {
        return timetableTeacher;
    }

    public void setTimetableTeacher(Teacher timetableTeacher) {
        this.timetableTeacher = timetableTeacher;
    }

    public Group getTimetableGroup() {
        return timetableGroup;
    }

    public void setTimetableGroup(Group timetableGroup) {
        this.timetableGroup = timetableGroup;
    }

    public Subject getTimetableSubject() {
        return timetableSubject;
    }

    public void setTimetableSubject(Subject timetableSubject) {
        this.timetableSubject = timetableSubject;
    }

    public Day getTimetableDay() {
        return timetableDay;
    }

    public void setTimetableDay(Day timetableDay) {
        this.timetableDay = timetableDay;
    }

    public Pair getTimetablePair() {
        return timetablePair;
    }

    public void setTimetablePair(Pair timetablePair) {
        this.timetablePair = timetablePair;
    }

    public Journal getTimetableJournal() {
        return timetableJournal;
    }

    public void setTimetableJournal(Journal timetableJournal) {
        this.timetableJournal = timetableJournal;
    }
}
