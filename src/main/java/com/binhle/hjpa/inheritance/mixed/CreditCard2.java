package com.binhle.hjpa.inheritance.mixed;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.validation.constraints.NotNull;

@Entity(name = "CreditCard2")
@DiscriminatorValue("CC2")
@SecondaryTable(name = "CREDITCARD2", pkJoinColumns = @PrimaryKeyJoinColumn(name = "CREDITCARD_ID"))
public class CreditCard2 extends BillingDetails {
    @NotNull
    @Column(table = "CREDITCARD2", nullable = false)
    protected String cardNumber;

    @NotNull
    @Column(table = "CREDITCARD2", nullable = false)
    protected String expMonth;

    @NotNull
    @Column(table = "CREDITCARD2", nullable = false)
    protected String expYear;
}
