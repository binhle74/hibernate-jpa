package com.binhle.hjpa.model.associations.onetoone.sharedprimarykey;

import static org.junit.Assert.assertNotNull;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OneToOneSharedPrimaryKeyTest extends BaseTest {

    @Before
    @Override
    public void before() {
    }

    @After
    @Override
    public void after() {
    }

    @Test
    public void testSharedPrimaryKey_Lazy_Load_Optional_False() {
        Address shippingAddress = new Address();
        shippingAddress.setStreet("Street 1");
        shippingAddress.setZipcode("70000");
        shippingAddress.setCity("HCM");
        // Need persist to generate a shared primary key
        this.beginTransaction().persist(shippingAddress);

        // Then, use the key for user entity
        User user = new User(shippingAddress.getId());
        user.setFirstName("First Name 1");
        user.setLastName("Last Name 1");
        user.setShippingAddress(shippingAddress);

        this.persist(user).commitTransaction().closeEntityManager();

        User savedUser = this.find(User.class, user.getId());

        assertNotNull(savedUser);
        // Shipping address is proxy object
        assertNotNull(savedUser.getShippingAddress().getCity());
        assertNotNull(savedUser.getShippingAddress().getUser());

        this.cleanData(User.class);
        this.cleanData(Address.class);
    }

    @Test
    public void testSharedPrimaryKey_Lazy_Load_Optional_True() {
        Address2 shippingAddress = new Address2();
        shippingAddress.setStreet("Street 1");
        shippingAddress.setZipcode("70000");
        shippingAddress.setCity("HCM");
        this.beginTransaction().persist(shippingAddress);

        User2 user = new User2(shippingAddress.getId());
        user.setFirstName("First Name 1");
        user.setLastName("Last Name 1");

        this.persist(user).commitTransaction().closeEntityManager();

        User2 savedUser = this.find(User2.class, user.getId());

        assertNotNull(savedUser);
        assertNotNull(savedUser.getShippingAddress());

        this.cleanData(Address2.class);
        this.cleanData(User2.class);
    }
}
