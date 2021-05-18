package com.binhle.hjpa.inheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Entity(name = "STBillingDetails")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE")
public abstract class BillingDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    
    @NotNull
    @Column(nullable = false)
    protected String owner;
}
