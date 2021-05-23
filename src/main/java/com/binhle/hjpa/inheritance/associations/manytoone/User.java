package com.binhle.hjpa.inheritance.associations.manytoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    
    @NotNull
    @Column(nullable = false)
    protected String username;
    
    @NotNull
    @Column(nullable = false)
    protected String firstName;
    
    @Column(nullable = false)
    protected String lastName;
    
    @Getter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected BillingDetails defaultBilling;
}
