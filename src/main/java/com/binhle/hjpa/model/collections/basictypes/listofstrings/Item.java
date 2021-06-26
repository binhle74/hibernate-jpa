package com.binhle.hjpa.model.collections.basictypes.listofstrings;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "C716ListItem")
@RequiredArgsConstructor
@NoArgsConstructor
public class Item {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NonNull
    @Getter
    @Setter
    private String name;

    @Getter
    @ElementCollection
    @CollectionTable(name = "C716LIST_IMAGE", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @OrderColumn
    @Column(name = "FILENAME")
    private List<String> images = new ArrayList<>();
}
