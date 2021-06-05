package com.binhle.hjpa.mappingcollections.bagofstrings;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "C715BagItem")
public class Item {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Getter
    @Setter
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Getter
    @ElementCollection
    @CollectionTable(name = "C715BAG_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @org.hibernate.annotations.CollectionId(columns = @Column(name = "IMAGE_ID"), generator = "sequence", type = @org.hibernate.annotations.Type(type = "long"))
    @Column(name = "FILENAME")
    private Collection<String> images = new ArrayList<>();
}
