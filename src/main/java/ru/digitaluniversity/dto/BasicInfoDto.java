package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.User;

/**
 * The type Basic info dto.
 */
public class BasicInfoDto {
    private String id;
    private String name;
    private String surname;
    private String middlename;

    public BasicInfoDto() {
    }

    public BasicInfoDto(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.middlename = user.getMiddlename();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }


}
