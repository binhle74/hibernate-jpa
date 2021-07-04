package com.binhle.hjpa.model.associations.onetomany.cascade.remove;

import static org.junit.Assert.assertTrue;

import java.util.List;

import com.binhle.hjpa.BaseTest;

import org.junit.Test;

public class CascadeRemoveTest extends BaseTest {

    @Override
    public void before() {
        this.cleanData(Bid.class);
        this.cleanData(Item.class);
    }

    @Override
    public void after() {

    }

    @Test
    public void testCascadeRemoveItemAndBids() {
        Item item = new Item();
        item.setName("Cascade remove test item");
        for (int i = 1; i < 5; i++) {
            Bid bid = new Bid(item);
            bid.setAmount(i);
            item.getBids().add(bid);
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());

        // Bids are also removed along with item
        this.remove(savedItem).closeEntityManager();

        // Verify
        List<Bid> savedBids = this.findAll(Bid.class);
        assertTrue(savedBids.isEmpty());
    }
}
