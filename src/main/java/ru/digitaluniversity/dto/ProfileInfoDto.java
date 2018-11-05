package ru.digitaluniversity.dto;

public class ProfileInfoDto {
    private BasicInfoDto basicInfo;
    private SpecialityDto studentSpeciality;
    private GroupDto studentGroup;
    private PositionDto position;

    public ProfileInfoDto() {
    }

    public BasicInfoDto getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfoDto basicInfo) {
        this.basicInfo = basicInfo;
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

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto positionDto) {
        this.position = positionDto;
    }
}
