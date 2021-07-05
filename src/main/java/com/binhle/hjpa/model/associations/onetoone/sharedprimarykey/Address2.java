package com.binhle.hjpa.model.associations.onetoone.sharedprimarykey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode
@Entity(name = "C811SharedPrimaryKeyAddress2")
@Table(name = "C811_SHARED_PRIMARY_KEY_ADDRESS2")
public class Address2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String street;

    private String zipcode;

    private String city;

    @OneToOne(mappedBy = "shippingAddress")
    private User2 user;
}
