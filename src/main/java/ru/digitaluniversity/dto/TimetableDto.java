package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Timetable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public TimetableDto(Timetable timetable) {
        this.id = timetable.getId().toString();
        this.teacher = Optional.ofNullable(timetable.getTimetableTeacher())
                .map(TeacherDto::new).orElse(null);
        this.group = Optional.ofNullable(timetable.getTimetableGroup())
                .map(GroupDto::new).orElse(null);
        this.subject = Optional.ofNullable(timetable.getTimetableSubject())
                .map(SubjectDto::new).orElse(null);
        this.day = Optional.ofNullable(timetable.getTimetableDay())
                .map(DayDto::new).orElse(null);
        this.pair = Optional.ofNullable(timetable.getTimetablePair())
                .map(PairDto::new).orElse(null);
        this.journals = timetable.getTimetableJournal()
                .stream().map(JournalDto::new).collect(Collectors.toList());
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
