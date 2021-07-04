package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.entity;

import static org.junit.Assert.assertEquals;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class OrphanEntityTest extends BaseTest {

    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(Bid.class);
        this.cleanData(Item.class);
    }

    @Test
    public void testRemoveOne() {
        Item item = new Item();
        item.setName("Orphan Entity Test item");
        for (int i = 1; i < 5; i++) {
            Bid bid = new Bid(item);
            bid.setAmount(i);
            item.getBids().add(bid);
        }

        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Remove first bid
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        Bid firstBid = savedItem.getBids().iterator().next();
        savedItem.getBids().remove(firstBid);
        logger.info("--- Saved Bids Size: {}", savedItem.getBids().size());
        this.persist(savedItem).commitTransaction().closeEntityManager();

        // Load updated item
        Item updatedItem = this.find(Item.class, item.getId());
        assertEquals(4, updatedItem.getBids().size());
        this.closeEntityManager();
    }

    @Test
    public void testRemoveAll() {
        Item item = new Item();
        item.setName("Orphan Entity Test item");
        for (int i = 1; i < 5; i++) {
            Bid bid = new Bid(item);
            bid.setAmount(i);
            item.getBids().add(bid);
        }

        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Remove first bid
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        savedItem.getBids().clear();
        logger.info("--- Saved Bids Size: {}", savedItem.getBids().size());
        this.persist(savedItem).commitTransaction().closeEntityManager();

        // Load updated item, bids are still in DB, not removed.
        Item updatedItem = this.find(Item.class, item.getId());
        assertEquals(4, updatedItem.getBids().size());
        this.closeEntityManager();
    }
}
