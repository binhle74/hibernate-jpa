package com.binhle.hjpa.model.collections.embeddablesetofstrings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity(name = "C726CollectionInEmbeddableUser")
@Table(name = "C726_COLLECTION_IN_EMBEDDABLE_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String firstName;

    private String lastName;

    private Address address;
}
