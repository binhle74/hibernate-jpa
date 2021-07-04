package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.orphanentity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class OrphanOrphanEntityTest extends BaseTest {

    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testRemoveOne() {
        Item item = new Item();
        item.setName("Orphan Removal Test Item");
        for (int i = 1; i < 5; i++) {
            Bid bid = new Bid(item);
            bid.setAmount(i);
            item.getBids().add(bid);
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Remove the first bid
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        Bid firstBid = savedItem.getBids().iterator().next();
        savedItem.getBids().remove(firstBid);
        logger.info("--- Saved Bids Size: {}", savedItem.getBids().size());
        this.persist(savedItem).commitTransaction().closeEntityManager();

        // Load updated item
        Item updatedItem = this.find(Item.class, item.getId());
        assertEquals(3, updatedItem.getBids().size());
    }

    @Test
    public void testRemoveAll() {
        Item item = new Item();
        item.setName("Orphan Removal Test Item");
        for (int i = 1; i < 5; i++) {
            Bid bid = new Bid(item);
            bid.setAmount(i);
            item.getBids().add(bid);
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Remove all of bids
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        savedItem.getBids().clear();
        logger.info("--- Saved Bids Size: {}", savedItem.getBids().size());
        this.persist(savedItem).commitTransaction().closeEntityManager();

        // Load updated item
        Item updatedItem = this.find(Item.class, item.getId());
        assertTrue(updatedItem.getBids().isEmpty());
    }
}
