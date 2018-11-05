package ru.digitaluniversity.dto;

/**
 * The type Query journal by teacher dto.
 */
public class QueryJournalByTeacherDto {

    private TeacherDto teacher;
    private JournalDto journalId;

    public QueryJournalByTeacherDto() {
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public JournalDto getJournalId() {
        return journalId;
    }

    public void setJournalId(JournalDto journalId) {
        this.journalId = journalId;
    }
}
