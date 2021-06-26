package com.binhle.hjpa.model.collections.components.setofembeddables;

import java.util.LinkedHashSet;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Data
@Entity(name = "C722SetOfEmbeddablesOrderByItem")
@Table(name = "C722_SET_OF_EMBEDDABLES_ORDERBY_ITEM")
public class OrderByItem {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ElementCollection
    @CollectionTable(name = "C722_SET_OF_EMBEDDABLES_ORDERBY_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @AttributeOverride(column = @Column(name = "FNAME"), name = "filename")
    @OrderBy("filename, width DESC")
    private Set<OrderByImage> images = new LinkedHashSet<>();
}
