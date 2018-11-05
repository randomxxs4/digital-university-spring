package ru.digitaluniversity.dto;

/**
 * Класс, описывающий DTO сущности преподаватель.
 */
public class TeacherDto {
    private String id;
    private BasicInfoDto basicInfo;
    private PositionDto position;

    public TeacherDto() {
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
