package com.binhle.hjpa.inheritance.mixed;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.validation.constraints.NotNull;

@Entity(name = "MixedCreditCard")
@DiscriminatorValue("CC")
@SecondaryTable(name = "MIXEDCREDITCARD", pkJoinColumns = @PrimaryKeyJoinColumn(name = "CREDITCARD_ID"))
public class CreditCard extends BillingDetails {
    @NotNull
    @Column(table = "MIXEDCREDITCARD", nullable = false)
    protected String cardNumber;

    @NotNull
    @Column(table = "MIXEDCREDITCARD", nullable = false)
    protected String expMonth;

    @NotNull
    @Column(table = "MIXEDCREDITCARD", nullable = false)
    protected String expYear;
}
