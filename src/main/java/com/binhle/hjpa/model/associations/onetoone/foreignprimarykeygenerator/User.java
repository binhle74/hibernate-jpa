package com.binhle.hjpa.model.associations.onetoone.foreignprimarykeygenerator;

import javax.persistence.CascadeType;
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
@Entity(name = "C812ForeignPrimaryKeyGeneratorUser")
@Table(name = "C812_FOREIGN_PRIMAR_KEY_GENERATOR_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private Address shippingAddress;
}
