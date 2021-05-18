package com.binhle.hjpa.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "STCreditCard")
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {
    @NotNull
    protected String cardNumber;
    
    @NotNull
    protected String expMonth;
    
    @NotNull
    protected String expYear;
}
