package ru.digitaluniversity.security.dto;

public class UserDto {
    private String id;
    private String name;
    private String surname;
    private String middlename;
    private String username;

    public UserDto() {
    }

    public UserDto(String id, String name, String surname, String middlename, String username) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
