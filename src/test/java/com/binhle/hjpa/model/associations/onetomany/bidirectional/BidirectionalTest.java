package com.binhle.hjpa.model.associations.onetomany.bidirectional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class BidirectionalTest extends BaseTest {

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
    public void testSaveItemAndBidsWithoutCascadePersist_FailBids() {
        Item item = new Item();
        item.setName("One to many item test");
        for (int i = 1; i < 5; i++) {
            Bid bid = new Bid(item);
            bid.setAmount(i);
            item.getBids().add(bid);
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        assertNotEquals(4, savedItem.getBids().size());
    }

    @Test
    public void testSaveItemAndBidsWithoutCascadePersist_SuccessBids() {
        this.beginTransaction();
        Item item = new Item();
        item.setName("One to many item test");
        this.persist(item);

        for (int i = 1; i < 5; i++) {
            Bid bid = new Bid(item);
            bid.setAmount(i);
            item.getBids().add(bid);
            this.persist(bid);
        }
        this.commitTransaction();
        this.closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        assertEquals(4, savedItem.getBids().size());
    }
}
