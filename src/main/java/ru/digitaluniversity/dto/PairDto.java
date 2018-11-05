package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Pair;

/**
 * Класс, описывающий DTO сущности пара.
 */
public class PairDto {
    private String id;
    private String number;

    public PairDto() {
    }

    public PairDto(Pair pair) {
        this.id = pair.getId().toString();
        this.number = pair.getNumber().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
