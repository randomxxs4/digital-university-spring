package ru.digitaluniversity.security.entity;

import ru.digitaluniversity.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tokenString;
    private Date experationDate;
    @ManyToOne
    private User user;

    public Token() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    public Date getExperationDate() {
        return experationDate;
    }

    public void setExperationDate(Date experationDate) {
        this.experationDate = experationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
