package ru.digitaluniversity.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @OneToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Speciality studentSpeciality;
    @ManyToOne
    private Group studentGroup;

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Speciality getStudentSpeciality() {
        return studentSpeciality;
    }

    public void setStudentSpeciality(Speciality studentSpeciality) {
        this.studentSpeciality = studentSpeciality;
    }

    public Group getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(Group studentGroup) {
        this.studentGroup = studentGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
