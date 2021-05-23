package com.binhle.hjpa.inheritance.associations.manytoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "IM2OCreditCard")
public class CreditCard extends BillingDetails {
    @NotNull
    @Column(nullable = false)
    protected String cardNumber;
    
    @NotNull
    @Column(nullable = false)
    protected String expMonth;
    
    @NotNull
    @Column(nullable = false)
    protected String expYear;
}
