package com.binhle.hjpa.model.associations.onetoone.foreignprimarykeygenerator;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.binhle.hjpa.BaseTest;

import org.junit.Test;

public class OneToOneForeignKeyGeneratorTest extends BaseTest {

    @Override
    public void before() {

    }

    @Override
    public void after() {

    }

    @Test
    public void testForeignKeyGenerator() {
        User user = new User();
        user.setFirstName("First Name 1");
        user.setLastName("Last Name 1");

        Address shippingAddress = new Address(user);
        user.setShippingAddress(shippingAddress);

        this.beginTransaction();
        // Grabs user id and assign to shipping address
        this.persist(user);
        this.commitTransaction().closeEntityManager();

        User savedUser = this.find(User.class, user.getId());

        // Verify
        assertNotNull(savedUser);
        assertNotNull(savedUser.getShippingAddress());
        assertNotEquals(Long.valueOf(0), savedUser.getShippingAddress().getId());
        this.closeEntityManager();

        // Clean data
        // Can not remove Address, need to be checked.
        // this.cleanData(Address.class);

        // Remove address by removing User and cascading REMOVE
        this.cleanData(User.class);
    }
}
