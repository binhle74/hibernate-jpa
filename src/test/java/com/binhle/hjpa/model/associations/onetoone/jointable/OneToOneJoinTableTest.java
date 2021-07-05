package com.binhle.hjpa.model.associations.onetoone.jointable;

import static org.junit.Assert.assertNotNull;

import com.binhle.hjpa.BaseTest;

import org.junit.Test;

public class OneToOneJoinTableTest extends BaseTest {

    @Override
    public void before() {
    }

    @Override
    public void after() {
    }

    @Test
    public void testOneToOneWithJoinTable() {
        this.beginTransaction();
        Shipment someShipment = new Shipment();
        this.persist(someShipment);

        Item auctionItem = new Item();
        auctionItem.setName("Item 1");
        this.persist(auctionItem);

        Shipment auctionShipment = new Shipment();
        auctionShipment.setAuction(auctionItem);
        this.persist(auctionShipment);

        this.commitTransaction().closeEntityManager();

        Shipment savedSomeShipment = this.find(Shipment.class, someShipment.getId());
        Shipment savedAuctionShipment = this.find(Shipment.class, auctionShipment.getId());

        // Verify
        assertNotNull(savedSomeShipment);
        assertNotNull(savedAuctionShipment);
        assertNotNull(savedAuctionShipment.getAuction());

        // Clean data
        this.cleanData(Shipment.class);
        this.cleanData(Item.class);
    }
}
