package com.binhle.hjpa.basictypescollections.mapofstrings;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "C717MapItem")
public class Item {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Setter
    @Getter
    private String name;

    @Getter
    @ElementCollection
    @CollectionTable(name = "C717MAP_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @MapKeyColumn(name = "FILENAME")
    @Column(name = "IMAGENAME")
    private Map<String, String> images = new HashMap<>();
}
