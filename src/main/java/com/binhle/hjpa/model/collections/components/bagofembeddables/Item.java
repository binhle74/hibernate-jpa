package com.binhle.hjpa.model.collections.components.bagofembeddables;

import java.util.ArrayList;
import java.util.Collection;

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

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity(name = "C723BagOfEmbeddablesItem")
@Table(name = "C723_BAG_OF_EMBEDDABLES_ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ElementCollection
    @CollectionTable(name = "C723_BAG_OF_EMBEDDABLES_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @CollectionId(columns = @Column(name = "IMAGE_ID"), generator = "sequence", type = @Type(type = "long"))
    @AttributeOverride(name = "filename", column = @Column(name = "FNAME"))
    @OrderBy("filename, width desc")
    private Collection<Image> images = new ArrayList<>();
}
