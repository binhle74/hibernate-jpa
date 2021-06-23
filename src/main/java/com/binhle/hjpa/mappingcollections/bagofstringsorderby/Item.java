package com.binhle.hjpa.mappingcollections.bagofstringsorderby;

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
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "C718BagOrderByItem")
@Table(name = "C718BAG_ORDERBY_ITEM")
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
    @CollectionTable(name = "C718BAG_ORDERBY_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @CollectionId(columns = @Column(name = "IMAGE_ID"), type = @Type(type = "long"), generator = "sequence")
    @OrderBy(clause = "FILENAME desc")
    @Column(name = "FILENAME")
    private Collection<String> images = new ArrayList<>();
}
