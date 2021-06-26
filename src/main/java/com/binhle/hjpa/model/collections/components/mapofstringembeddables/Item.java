package com.binhle.hjpa.model.collections.components.mapofstringembeddables;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity(name = "C724MapStringEmbeddablesItem")
@Table(name = "C724_MAP_STRING_EMBEDDABLES_ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ElementCollection
    @CollectionTable(name = "C724_MAP_STRING_EMBEDDABLES_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @MapKeyColumn(name = "FILENAME")
    @OrderBy(clause = "FILENAME desc")
    private Map<String, Image> images = new LinkedHashMap<>();
}
