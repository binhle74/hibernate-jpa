package com.binhle.hjpa.inheritance.joined;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity(name = "JCreditCard")
@PrimaryKeyJoinColumn(name = "CREDITCARD_ID")
@DiscriminatorValue("JCC")
public class CreditCard extends BillingDetails {
    @NotNull
    protected String cardNumber;
    
    @NotNull
    protected String expMonth;
    
    @NotNull
    protected String expYear;
}
