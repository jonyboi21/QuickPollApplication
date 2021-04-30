package com.apress.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class Option {


    public Option() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OPTION_ID")
    private Long id;

    @Column(name = "OPTION_VALUE")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
