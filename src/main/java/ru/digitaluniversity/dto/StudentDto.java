package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Student;

import java.util.Optional;

/**
 * Класс, описывающий DTO сущности студента.
 */
public class StudentDto {
    private String id;
    private BasicInfoDto basicInfo;
    private SpecialityDto studentSpeciality;
    private GroupDto studentGroup;

    public StudentDto() {
    }

    public StudentDto(Student student){
        this.id = student.getId().toString();
        this.basicInfo = Optional.ofNullable(student.getUser())
                .map(BasicInfoDto::new).orElse(null);
        this.studentSpeciality = Optional.ofNullable(student.getStudentSpeciality())
                .map(SpecialityDto::new).orElse(null);
        this.studentGroup = Optional.ofNullable(student.getStudentGroup())
                .map(GroupDto::new).orElse(null);
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
        return basicInfo;
    }

    public void setBasicInfo(BasicInfoDto basicInfo) {
        this.basicInfo = basicInfo;
    }
}
