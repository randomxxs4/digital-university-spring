package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Teacher;

import java.util.Optional;

/**
 * Класс, описывающий DTO сущности преподаватель.
 */
public class TeacherDto {
    private String id;
    private BasicInfoDto basicInfo;
    private PositionDto position;

    public TeacherDto() {
    }

    public TeacherDto(Teacher teacher) {
        this.id = teacher.getId().toString();
        this.basicInfo = Optional.ofNullable(teacher.getUser())
                .map(BasicInfoDto::new).orElse(null);
        this.position = Optional.ofNullable(teacher.getPosition())
                .map(PositionDto::new).orElse(null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public BasicInfoDto getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfoDto basicInfo) {
        this.basicInfo = basicInfo;
    }
}
