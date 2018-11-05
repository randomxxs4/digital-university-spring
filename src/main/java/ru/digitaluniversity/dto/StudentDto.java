package ru.digitaluniversity.dto;

/**
 * Класс, описывающий DTO сущности студента.
 */
public class StudentDto {
    private String id;
    private BasicInfoDto basicInfoDto;
    private SpecialityDto studentSpeciality;
    private GroupDto studentGroup;

    public StudentDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SpecialityDto getStudentSpeciality() {
        return studentSpeciality;
    }

    public void setStudentSpeciality(SpecialityDto studentSpeciality) {
        this.studentSpeciality = studentSpeciality;
    }

    public GroupDto getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(GroupDto studentGroup) {
        this.studentGroup = studentGroup;
    }

    public BasicInfoDto getBasicInfo() {
        return basicInfoDto;
    }

    public void setBasicInfo(BasicInfoDto basicInfo) {
        this.basicInfoDto = basicInfo;
    }
}
