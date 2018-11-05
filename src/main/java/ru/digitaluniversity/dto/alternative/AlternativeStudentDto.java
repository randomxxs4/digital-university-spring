package ru.digitaluniversity.dto.alternative;

import ru.digitaluniversity.dto.BasicInfoDto;
import ru.digitaluniversity.dto.SpecialityDto;

/**
 * The type Alternative student dto.
 */
public class AlternativeStudentDto extends BasicInfoDto{
    private SpecialityDto studentSpeciality;
    private AlternativeGroupDto studentGroup;

    public AlternativeStudentDto() {
    }

    public SpecialityDto getStudentSpeciality() {
        return studentSpeciality;
    }

    public void setStudentSpeciality(SpecialityDto studentSpeciality) {
        this.studentSpeciality = studentSpeciality;
    }

    public AlternativeGroupDto getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(AlternativeGroupDto studentGroup) {
        this.studentGroup = studentGroup;
    }
}
