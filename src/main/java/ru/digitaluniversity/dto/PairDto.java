package ru.digitaluniversity.dto;

/**
 * Класс, описывающий DTO сущности пара.
 */
public class PairDto {
    private String id;
    private String number;

    public PairDto() {
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
