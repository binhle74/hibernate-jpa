package com.binhle.hjpa.inheritance.tableperclass;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "TPCBankAccount")
public class BankAccount extends BillingDetails {
    @NotNull
    protected String account;
    
    @NotNull
    protected String bankName;
    
    @NotNull
    protected String swift;
}
