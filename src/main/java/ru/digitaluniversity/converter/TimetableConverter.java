package ru.digitaluniversity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.*;
import ru.digitaluniversity.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimetableConverter implements Converter<Timetable, TimetableDto> {

    @Autowired
    private Converter<Teacher, TeacherDto> teacherConverter;

    @Autowired
    private Converter<Journal, JournalDto> journalConverter;

    @Autowired
    private Converter<Group, GroupDto> groupConverter;

    @Autowired
    private Converter<Subject, SubjectDto> subjectConverter;

    @Autowired
    private Converter<Day, DayDto> dayConverter;

    @Autowired
    private Converter<Pair, PairDto> pairConverter;

    @Override
    public TimetableDto convertToDto(Timetable obj) {
        TimetableDto timetableDto = new TimetableDto();
        if (obj.getId() != null) {
            timetableDto.setId(obj.getId().toString());
        }
        if (obj.getTimetableTeacher() != null) {
            timetableDto.setTeacher(teacherConverter.convertToDto(obj.getTimetableTeacher()));
        }
        if (obj.getTimetableJournal() != null && !obj.getTimetableJournal().isEmpty()) {
            List<Journal> timetableJournals = obj.getTimetableJournal();
            List<JournalDto> journalDtos = timetableJournals.stream().map(journal -> journalConverter.convertToDto(journal)).collect(Collectors.toList());
            timetableDto.setJournals(journalDtos);
        }
        if (obj.getTimetableGroup() != null) {
            timetableDto.setGroup(groupConverter.convertToDto(obj.getTimetableGroup()));
        }
        if (obj.getTimetableSubject() != null) {
            timetableDto.setSubject(subjectConverter.convertToDto(obj.getTimetableSubject()));
        }
        if (obj.getTimetableDay() != null) {
            timetableDto.setDay(dayConverter.convertToDto(obj.getTimetableDay()));
        }
        if (obj.getTimetablePair() != null) {
            timetableDto.setPair(pairConverter.convertToDto(obj.getTimetablePair()));
        }
        return timetableDto;
    }

    @Override
    public Timetable convertToEntity(TimetableDto obj) {
        Timetable timetable = new Timetable();
        if (obj.getId() != null) {
            timetable.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getDay() != null) {
            timetable.setTimetableDay(dayConverter.convertToEntity(obj.getDay()));
        }
        if (obj.getGroup() != null) {
            timetable.setTimetableGroup(groupConverter.convertToEntity(obj.getGroup()));
        }
        if (obj.getPair() != null) {
            timetable.setTimetablePair(pairConverter.convertToEntity(obj.getPair()));
        }
        if (obj.getSubject() != null) {
            timetable.setTimetableSubject(subjectConverter.convertToEntity(obj.getSubject()));
        }
        if (obj.getTeacher() != null) {
            timetable.setTimetableTeacher(teacherConverter.convertToEntity(obj.getTeacher()));
        }
        if (obj.getJournals() != null && !obj.getJournals().isEmpty()) {
            List<JournalDto> journalDtos = obj.getJournals();
            List<Journal> journals = journalDtos.stream().map((journalDto -> journalConverter.convertToEntity(journalDto))).collect(Collectors.toList());
            timetable.setTimetableJournal(journals);
        }
        return timetable;
    }
}
