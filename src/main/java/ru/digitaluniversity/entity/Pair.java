package ru.digitaluniversity.entity;

import javax.persistence.*;

/**
 * The type Pair.
 */
@Entity
@Table(name = "pairs")
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer number;

    public Pair() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
