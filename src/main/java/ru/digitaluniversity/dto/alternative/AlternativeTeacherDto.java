package ru.digitaluniversity.dto.alternative;

import ru.digitaluniversity.dto.BasicInfoDto;
import ru.digitaluniversity.dto.PositionDto;

/**
 * The type Alternative teacher dto.
 */
public class AlternativeTeacherDto extends BasicInfoDto {
    private PositionDto position;


    public AlternativeTeacherDto() {
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }
}
