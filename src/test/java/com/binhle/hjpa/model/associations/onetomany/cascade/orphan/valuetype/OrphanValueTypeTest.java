package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.valuetype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.binhle.hjpa.BaseTest;

import org.junit.Test;

public class OrphanValueTypeTest extends BaseTest {

    @Override
    public void before() {

    }

    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testRemoveOne() {
        Item item = new Item();
        item.setName("Orphan Value Type Test Item");
        for (int i = 1; i < 5; i++) {
            item.getImages().add(String.format("acb-%s.jpg", i));
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Remove the fist image in the item
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        String firstImage = savedItem.getImages().iterator().next();
        savedItem.getImages().remove(firstImage);
        logger.info("--- Saved Images: {}", savedItem.getImages());
        this.commitTransaction();
        this.closeEntityManager();

        // Load updated item
        Item updatedItem = this.find(Item.class, item.getId());
        assertEquals(3, updatedItem.getImages().size());
        this.closeEntityManager();
    }

    @Test
    public void testRemoveAll() {
        Item item = new Item();
        item.setName("Orphan Value Type Test Item");
        for (int i = 1; i < 5; i++) {
            item.getImages().add(String.format("acb-%s.jpg", i));
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Remove all of images
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        savedItem.getImages().clear();
        logger.info("--- Saved Images: {}", savedItem.getImages());
        this.commitTransaction();
        this.closeEntityManager();

        // Load updated item
        Item updatedItem = this.find(Item.class, item.getId());
        assertTrue(updatedItem.getImages().isEmpty());
        this.closeEntityManager();
    }
}
