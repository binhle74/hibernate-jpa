package com.binhle.hjpa.mappingcollections.mapofstringsorderby;

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
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "C718MapOrderByItem")
@Table(name = "C718MAP_ORDERBY_ITEM")
public class Item {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @ElementCollection
    @CollectionTable(name = "C718MAP_ORDERBY_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @MapKeyColumn(name = "FILENAME")
    @OrderBy(clause = "FILENAME desc")
    @Column(name = "IMAGENAME")
    private Map<String, String> images = new HashMap<>();
}
