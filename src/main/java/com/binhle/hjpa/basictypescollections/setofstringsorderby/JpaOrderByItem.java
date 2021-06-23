package com.binhle.hjpa.basictypescollections.setofstringsorderby;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "C718SetJpaOrderByItem")
@Table(name = "C718SET_JPA_ORDERBY_ITEM")
public class JpaOrderByItem {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @ElementCollection
    @CollectionTable(name = "C718SET_JPA_ORDERBY_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @OrderBy // Always ASC by basic object likes: String, Integer
    @Column(name = "FILENAME")
    private Set<String> images = new LinkedHashSet<>();
}
