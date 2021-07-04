package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(exclude = "item")
@EqualsAndHashCode(exclude = "item")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "C732O2MOrphanEntityBid")
@Table(name = "C732_O2M_ORPHAN_ENTITY_BID")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private float amount;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;
}
