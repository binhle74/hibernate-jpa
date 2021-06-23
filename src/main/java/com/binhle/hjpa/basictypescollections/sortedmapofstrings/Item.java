package com.binhle.hjpa.basictypescollections.sortedmapofstrings;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.SortComparator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "C718SortedMapItem")
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
    @CollectionTable(name = "C718SORTED_MAP_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @MapKeyColumn(name = "FILENAME")
    @Column(name = "IMAGENAME")
    @SortComparator(ReverseStringComparator.class)
    private SortedMap<String, String> images = new TreeMap<>();
}
