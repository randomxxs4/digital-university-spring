package ru.digitaluniversity.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Journal.
 */
@Entity
@Table(name = "journals")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subject")
    private Subject journalSubject;
    @ManyToOne
    @JoinColumn(name = "student")
    private Student journalStudent;
    @ManyToOne
    @JoinColumn(name = "rating")
    private Rating journalRating;
    @ManyToOne
    @JoinColumn(name = "timetable")
    private Timetable journalTimetable;
    @Column(name = "date")
    private Date journalDate;

    public Journal() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subject getJournalSubject() {
        return journalSubject;
    }

    public void setJournalSubject(Subject journalSubject) {
        this.journalSubject = journalSubject;
    }

    public Student getJournalStudent() {
        return journalStudent;
    }

    public void setJournalStudent(Student journalStudent) {
        this.journalStudent = journalStudent;
    }

    public Rating getJournalRating() {
        return journalRating;
    }

    public void setJournalRating(Rating journalRating) {
        this.journalRating = journalRating;
    }

    public Timetable getJournalTimetable() {
        return journalTimetable;
    }

    public void setJournalTimetable(Timetable journalTimetable) {
        this.journalTimetable = journalTimetable;
    }

    public Date getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(Date journalDate) {
        this.journalDate = journalDate;
    }
}
