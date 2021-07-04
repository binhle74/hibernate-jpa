package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class OrphanComponentTest extends BaseTest {

    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {

    }

    @Test
    public void testRemoveOne() {
        Item item = new Item();
        item.setName("Orphan component test item");
        for (int i = 1; i < 5; i++) {
            Image image = new Image();
            image.setFilename(String.format("abc-%s.jpg", i));
            image.setTitle(String.format("ABC-%s", i));
            item.getImages().add(image);
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Remove first image
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        Image firstImage = savedItem.getImages().iterator().next();
        savedItem.getImages().remove(firstImage);
        this.persist(savedItem).commitTransaction().closeEntityManager();

        // Load item
        Item updatedItem = this.find(Item.class, item.getId());
        assertEquals(3, updatedItem.getImages().size());
    }

    @Test
    public void testRemoveAll() {
        Item item = new Item();
        item.setName("Orphan component test item");
        for (int i = 1; i < 5; i++) {
            Image image = new Image();
            image.setFilename(String.format("abc-%s.jpg", i));
            image.setTitle(String.format("ABC-%s", i));
            item.getImages().add(image);
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Remove all of images
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        savedItem.getImages().clear();
        this.persist(savedItem).commitTransaction().closeEntityManager();

        // Load item
        Item updatedItem = this.find(Item.class, item.getId());
        assertTrue(updatedItem.getImages().isEmpty());
    }
}
