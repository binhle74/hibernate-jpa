package com.binhle.hjpa.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "STBankAccount")
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails {
    @NotNull
    protected String account;
    
    @NotNull
    protected String bankName;
    
    @NotNull
    protected String swift;
}
