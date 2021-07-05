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
@Entity(name = "C811SharedPrimaryKeyUser2")
@Table(name = "C811_SHARED_PRIMARY_KEY_USER2")
public class User2 {
    @NonNull
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    // @OneToOne(optional = true) does not support Lazy loading.
    // With this setting works similar to EAGER.
    // NO foreign key from User to Address
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Address2 shippingAddress;
}
