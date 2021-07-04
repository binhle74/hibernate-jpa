package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.valuetype;

import java.util.HashSet;
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

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity(name = "C732O2MOrphanValueTypeItem")
@Table(name = "C732_O2M_ORPHAN_VALUE_TYPE_ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "C732_O2M_ORPHAN_VALUE_TYPE_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @Column(name = "FILENAME")
    private Set<String> images = new HashSet<>();
}
