package ru.digitaluniversity.dto;

import java.util.List;

/**
 * Класс, описывающий DTO сущности расписание.
 */
public class TimetableDto {
    private String id;
    private TeacherDto teacher;
    private GroupDto group;
    private SubjectDto subject;
    private DayDto day;
    private PairDto pair;
    private List<JournalDto> journals;

    public TimetableDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public DayDto getDay() {
        return day;
    }

    public void setDay(DayDto day) {
        this.day = day;
    }

    public PairDto getPair() {
        return pair;
    }

    public void setPair(PairDto pair) {
        this.pair = pair;
    }

    public List<JournalDto> getJournals() {
        return journals;
    }

    public void setJournals(List<JournalDto> journals) {
        this.journals = journals;
    }
}
