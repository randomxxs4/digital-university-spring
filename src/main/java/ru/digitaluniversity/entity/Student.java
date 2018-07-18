package ru.digitaluniversity.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;
    private String password;
    private String surname;
    private String name;
    private String middlename;
    @ManyToOne
    private Speciality studentSpeciality;
    @ManyToOne
    private Group studentGroup;

    public Student() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
