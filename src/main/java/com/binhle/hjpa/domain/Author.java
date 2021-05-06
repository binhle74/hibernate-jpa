package com.binhle.hjpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Author {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Getter @Setter
    private String firstName;
    
    @Getter @Setter
    private String lastName;
}
