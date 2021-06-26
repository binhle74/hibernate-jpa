package com.binhle.hjpa.model.collections.embeddablesetofstrings;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;

import lombok.Data;

@Data
@Embeddable
public class Address {
    private String street;

    private String zipcode;

    private String city;

    @ElementCollection
    @CollectionTable(name = "C726_COLLECTION_IN_EMBEDDALE_CONTACT", joinColumns = @JoinColumn(name = "USER_ID"))
    @OrderBy
    @Column(name = "NAME")
    // Can apply collection of another embeddable component in this component
    private Set<String> contacts = new LinkedHashSet<>();
}
