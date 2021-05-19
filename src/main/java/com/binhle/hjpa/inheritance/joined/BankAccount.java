package com.binhle.hjpa.inheritance.joined;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "JBankAccount")
@DiscriminatorValue("JBA")
public class BankAccount extends BillingDetails {
    @NotNull
    protected String account;
    
    @NotNull
    protected String bankName;
    
    @NotNull
    protected String swift;
}
