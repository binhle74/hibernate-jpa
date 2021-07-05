package com.binhle.hjpa.model.associations.onetoone.foreignkey;

import static org.junit.Assert.assertNotNull;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OneToOneForeignKeyTest extends BaseTest {

    @Before
    @Override
    public void before() {
    }

    @After
    @Override
    public void after() {
        this.cleanData(User.class);
        this.cleanData(Address.class);
    }

    @Test
    public void testForeignKey() {
        User user = new User();
        user.setFirstName("First Name 1");
        user.setLastName("Last Name 1");

        Address shippingAddress = new Address();
        shippingAddress.setStreet("Street 1");
        shippingAddress.setZipcode("70000");
        shippingAddress.setCity("HCM");
        user.setShippingAddress(shippingAddress);

        this.beginTransaction().persist(user).commitTransaction().closeEntityManager();

        User savedUser = this.find(User.class, user.getId());

        // Verify
        assertNotNull(savedUser);
        assertNotNull(savedUser.getShippingAddress());
    }
}
