package com.binhle.hjpa.model.associations.onetoone.sharedprimarykey;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "C811SharedPrimaryKeyUser")
@Table(name = "C811_SHARED_PRIMARY_KEY_USER")
public class User {
    @NonNull
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    // A foreign key from User to Address
    // Lazy loading required optional = false
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private Address shippingAddress;
}
