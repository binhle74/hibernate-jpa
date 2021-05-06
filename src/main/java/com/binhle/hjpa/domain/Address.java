package com.binhle.hjpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Address {
    @Id
    @GeneratedValue
    @Getter
    private long id;
    
    @Getter @Setter
    private String street;
}
