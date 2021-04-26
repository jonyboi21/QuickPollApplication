package com.apress.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Poll {


    @Id
    @GeneratedValue
    @Column(name = "POLL_ID")
    private Long id;

    @Column(name="QUESTION")
    private String question;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="POLL_ID")
    @OrderBy
    private Set<Option> options;


    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }
}
