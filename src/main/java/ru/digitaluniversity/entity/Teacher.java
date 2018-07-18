package ru.digitaluniversity.entity;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher{

    @OneToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "position")
    private Position teacherPosition;

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

    public Position getTeacherPosition() {
        return teacherPosition;
    }

    public void setTeacherPosition(Position teacherPosition) {
        this.teacherPosition = teacherPosition;
    }
}
