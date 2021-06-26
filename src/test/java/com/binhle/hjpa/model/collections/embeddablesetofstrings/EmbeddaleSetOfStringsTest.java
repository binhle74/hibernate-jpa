package com.binhle.hjpa.model.collections.embeddablesetofstrings;

import static org.junit.Assert.assertEquals;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class EmbeddaleSetOfStringsTest extends BaseTest {

    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(User.class);
    }

    @Test
    public void testContactColllectionInEmbeddableAddress() {
        User user = new User();
        user.setFirstName("Binh");
        user.setLastName("Le");
        Address address = new Address();
        user.setAddress(address);
        address.setCity("Ho Chi Minh");
        address.setStreet("R34");
        address.setZipcode("70000");
        for (int i = 5; i > 0; i--) {
            address.getContacts().add(String.format("Contact %s", i));
        }
        String expectedContacts = address.getContacts().stream().sorted().reduce("", this::concat);
        this.beginTransaction().persist(user).commitTransaction().closeEntityManager();

        // Query user
        User savedUser = this.find(User.class, user.getId());
        String savedContacts = savedUser.getAddress().getContacts().stream().reduce("", this::concat);

        // Verify
        assertEquals(expectedContacts, savedContacts);
    }
}
