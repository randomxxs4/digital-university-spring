package ru.digitaluniversity.security.dto;

import ru.digitaluniversity.security.entity.UserRole;

public class RoleDto {
    private String id;
    private String role;

    public RoleDto() {
    }

    public RoleDto(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleDto(UserRole role) {
        this.id = role.getId().toString();
        this.role = role.getRole();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
