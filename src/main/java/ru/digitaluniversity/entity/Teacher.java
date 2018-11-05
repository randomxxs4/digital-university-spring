package ru.digitaluniversity.entity;

import javax.persistence.*;
import java.util.List;

/**
 * The type Teacher.
 */
@Entity
@Table(name = "teachers")
public class Teacher{

    @OneToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
