package com.binhle.hjpa.inheritance.tableperclass;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "TPCCreditCard")
public class CreditCard extends BillingDetails {
    @NotNull
    protected String cardNumber;
    
    @NotNull
    protected String expMonth;
    
    @NotNull
    protected String expYear;
}
