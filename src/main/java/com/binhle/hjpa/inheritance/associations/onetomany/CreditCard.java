package com.binhle.hjpa.inheritance.associations.onetomany;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "IO2MCreditCard")
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {
    protected String cardNumber;
    
    protected String expMonth;
    
    protected String expYear;
}
