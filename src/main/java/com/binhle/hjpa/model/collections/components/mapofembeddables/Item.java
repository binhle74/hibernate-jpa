package com.binhle.hjpa.model.collections.components.mapofembeddables;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity(name = "C725MapOfEmbeddablesItem")
@Table(name = "C725_MAP_OF_EMBEDDABLES_ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ElementCollection
    @CollectionTable(name = "C725_MAP_OF_EMBEDDABLES_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    // Can not apply @MapKeyColumn and @AttributeOverrides
    @OrderBy(clause = "NAME desc")
    private Map<Filename, Image> images = new LinkedHashMap<>();
}
