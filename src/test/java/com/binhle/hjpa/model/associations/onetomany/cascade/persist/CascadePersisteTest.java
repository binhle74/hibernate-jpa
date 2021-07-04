package com.binhle.hjpa.model.associations.onetomany.cascade.persist;

import static org.junit.Assert.assertEquals;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class CascadePersisteTest extends BaseTest {

    @Override
    public void before() {
    }

    @After
    @Override
    public void after() {
        // this.cleanData(Bid.class);
        // this.cleanData(Item.class);
    }

    @Test
    public void testSaveItemAndBidsWithCascadePersist() {
        this.beginTransaction();
        Item item = new Item();
        item.setName("Cascade item test");
        this.persist(item);
        for (int i = 1; i < 5; i++) {
            Bid bid = new Bid(item);
            bid.setAmount(i);
            item.getBids().add(bid);
        }

        this.commitTransaction();
        this.closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        assertEquals(4, savedItem.getBids().size());
        this.closeEntityManager();
    }
}
