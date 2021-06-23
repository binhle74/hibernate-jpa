package com.binhle.hjpa.mappingcollections.setofstringsorderby;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "C718SetOrderByItem")
@Table(name = "C718SET_ORDERBY_ITEM")
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
    @CollectionTable(name = "C718SET_ORDERBY_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @OrderBy(clause = "FILENAME desc")
    @Column(name = "FILENAME")
    private Set<String> images = new LinkedHashSet<>();
}
