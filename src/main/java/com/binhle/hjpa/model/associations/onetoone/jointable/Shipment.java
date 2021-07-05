package com.binhle.hjpa.model.associations.onetoone.jointable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode
@Entity(name = "C814JoinTableShipment")
@Table(name = "C814_JOIN_TABLE_SHIPMENT")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "C814_JOIN_TABLE_ITEM_SHIPMENT", joinColumns = @JoinColumn(name = "SHIPMENT_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, unique = true))
    private Item auction;
}
