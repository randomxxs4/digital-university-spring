package ru.digitaluniversity.security.entity;

import org.springframework.security.core.GrantedAuthority;
import ru.digitaluniversity.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRole() {

    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
