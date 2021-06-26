package com.binhle.hjpa.model.collections.components.setofembeddables;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.ToString;

@ToString
@Entity(name = "C722SetOfEmbeddablesItem")
@Table(name = "C722SET_OF_EMBEDDABLES_ITEM")
public class Item {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Getter
    @ElementCollection
    @CollectionTable(name = "C722SET_OF_EMBEDDABLES_IMAGES", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @AttributeOverride(name = "filename", column = @Column(name = "FNAME", nullable = false))
    private Set<Image> images = new HashSet<>();
}
