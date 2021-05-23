package com.binhle.hjpa.inheritance.associations.onetomany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity(name = "IO2MUser")
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    
    protected String username;
    
    protected String firstName;
    
    protected String lastName;
    
    @OneToMany(mappedBy = "owner")
    protected Set<BillingDetails> billingDetails = new HashSet<>();
}
