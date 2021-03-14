package com.binhle.hjpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "book_generator")
    @TableGenerator(name = "book_generator", table = "book_id_generator")
    @Getter
    private long id;
    
    @Getter @Setter
    private String title;
}
