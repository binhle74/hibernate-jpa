package com.binhle.hjpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Author {
    @Id
    @GeneratedValue(generator = "authorSeq")
    @SequenceGenerator(name = "authorSeq", sequenceName = "author_sequence", initialValue = 3)
    @Getter
    private long id;
    
    @Setter @Getter
    private String firstName;
    
    @Setter @Getter
    private String lastName;
}
