package com.binhle.hjpa.model.associations.onetoone.foreignprimarykeygenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "C812ForeignPrimaryKeyGeneratorAddress")
@Table(name = "C812_FOREIGN_PRIMAR_KEY_GENERATOR_ADDRESS")
public class Address {
    public Address(User user) {
        this.user = user;
    }

    public Address(User user, String street, String zipcode, String city) {
        this.user = user;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    @Id
    @GeneratedValue(generator = "addressKeyGenerator")
    @GenericGenerator(name = "addressKeyGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
    private Long id;

    private String street;

    private String zipcode;

    private String city;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private User user;
}
