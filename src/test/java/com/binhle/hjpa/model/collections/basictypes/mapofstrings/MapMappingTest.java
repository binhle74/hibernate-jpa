package com.binhle.hjpa.model.collections.basictypes.mapofstrings;

import static org.junit.Assert.assertEquals;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class MapMappingTest extends BaseTest {

    @Override
    public void before() {
    }

    @After
    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testMapMappingImages() {
        Item item = new Item();
        item.setName("Item Test");
        item.getImages().put("abc-1.jpg", "ABC-1");
        item.getImages().put("abc-2.jpg", "ABC-2");
        item.getImages().put("abc-3.jpg", "ABC-3");
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        logger.info("--- Saved Images: {}", savedItem.getImages());
        assertEquals(3, savedItem.getImages().size());
    }
}
