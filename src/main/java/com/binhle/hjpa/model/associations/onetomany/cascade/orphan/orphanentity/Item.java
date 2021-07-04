package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.orphanentity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(exclude = "bids")
@EqualsAndHashCode(exclude = "bids")
@Data
@Entity(name = "C732O2MOrphanOrphanEntityItem")
@Table(name = "C732_O2M_ORPHAN_ORPHAN_ENTITY_ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST }, orphanRemoval = true)
    private Set<Bid> bids = new HashSet<>();
}
