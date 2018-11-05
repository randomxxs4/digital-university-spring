package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Group;

/**
 * Класс, описывающий DTO сущности группа
 */
public class GroupDto {
    private String id;
    private String title;
    private int countStudent;

    public GroupDto() {
    }

    public GroupDto(Group group) {
        this.id = group.getId().toString();
        this.title = group.getTitle();
        this.countStudent = group.getStudents().size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCountStudent() {
        return countStudent;
    }

    public void setCountStudent(int countStudent) {
        this.countStudent = countStudent;
    }
}
