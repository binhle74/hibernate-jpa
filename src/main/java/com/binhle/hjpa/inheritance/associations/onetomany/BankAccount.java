package com.binhle.hjpa.inheritance.associations.onetomany;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "IO2MBankAccount")
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails {
    protected String account;
    
    protected String bankName;
    
    protected String swift;
}
