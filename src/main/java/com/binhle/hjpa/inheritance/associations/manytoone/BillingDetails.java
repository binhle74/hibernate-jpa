package com.binhle.hjpa.inheritance.associations.manytoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity(name = "M2OBillingDetails")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    
    @NotNull
    @Column(nullable = false)
    protected String owner;
}
