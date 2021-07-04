package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.component;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode
@Entity(name = "C732O2MOrphanComponentItem")
@Table(name = "C732_O2M_ORPHAN_COMPONENT_ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "C732_O2M_ORPHAN_COMPONENT_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @AttributeOverride(name = "filename", column = @Column(name = "FNAME"))
    private Set<Image> images = new HashSet<>();
}
