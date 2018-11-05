package ru.digitaluniversity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.*;
import ru.digitaluniversity.entity.*;
import ru.digitaluniversity.exception.ConvertException;

import java.util.Date;

@Service
public class JournalConverter implements Converter<Journal, JournalDto> {

    @Autowired
    private Converter<Rating, RatingDto> ratingConverter;

    @Autowired
    private Converter<Student, StudentDto> studentConverter;

    @Autowired
    private ManyToManyConverter<Subject, SubjectDto> subjectConverter;

    @Autowired
    private Converter<Teacher, TeacherDto> teacherConverter;

    @Autowired
    private Converter<Timetable, TimetableDto> timetableConverter;

    @Override
    public JournalDto convertToDto(Journal obj) {
        JournalDto journalDto = new JournalDto();
        if (obj.getId() != null) {
            journalDto.setId(obj.getId().toString());
        }
        if (obj.getJournalDate() != null) {
            journalDto.setDate(obj.getJournalDate());
        }
        if (obj.getJournalRating() != null){
            journalDto.setRating(ratingConverter.convertToDto(obj.getJournalRating()));
        }
        if (obj.getJournalStudent() != null){
            journalDto.setStudent(studentConverter.convertToDto(obj.getJournalStudent()));
        }
        if (obj.getJournalSubject() != null) {
            journalDto.setSubject(subjectConverter.convertManyToManyLink(obj.getJournalSubject()));
        }
        if (obj.getJournalTimetable() != null) {
            journalDto.setTimetableTeacher(teacherConverter.convertToDto(obj.getJournalTimetable().getTimetableTeacher()));
            journalDto.setTimetable(timetableConverter.convertToDto(obj.getJournalTimetable()));
        }

        return journalDto;
    }

    @Override
    public Journal convertToEntity(JournalDto obj) {
        Journal journal = new Journal();
        if (obj.getId() != null) {
            journal.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getSubject() != null) {
            journal.setJournalSubject(subjectConverter.convertToEntity(obj.getSubject()));
        }
        if (obj.getRating() != null) {
            journal.setJournalRating(ratingConverter.convertToEntity(obj.getRating()));
        }
        if (obj.getStudent() != null) {
            journal.setJournalStudent(studentConverter.convertToEntity(obj.getStudent()));
        }
        if (obj.getTimetable() != null) {
            journal.setJournalTimetable(timetableConverter.convertToEntity(obj.getTimetable()));
        }
        if (obj.getDate() != null) {
            journal.setJournalDate(new Date());
        }
        return journal;
    }
}
