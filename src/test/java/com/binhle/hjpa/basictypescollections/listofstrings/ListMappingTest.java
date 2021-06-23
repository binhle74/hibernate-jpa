package com.binhle.hjpa.basictypescollections.listofstrings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.binhle.hjpa.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListMappingTest extends BaseTest {

    @Before
    @Override
    public void before() {
        cleanData(Item.class);
        closeEntityManager();
    }

    @After
    @Override
    public void after() {

    }

    @Test
    public void testAddDuplicateImages() {
        this.beginTransaction();
        for (int i = 1; i < 3; i++) {
            Item item = new Item("List Item Test " + i);
            item.getImages().add("abc-1.jpg");
            item.getImages().add("abc-1.jpg");
            item.getImages().add("abc-2.jpg");
            item.getImages().add("abc-2.jpg");
            this.persist(item);
        }
        this.commitTransaction().closeEntityManager();

        // Verify
        this.findAll(Item.class).forEach(item -> {
            assertEquals(4, item.getImages().size());
        });

        // Clean data
        this.cleanData(Item.class);
    }

    @Test
    public void testAddMiddleImages() {
        this.beginTransaction();
        Item item = new Item("List Item Test ");
        item.getImages().add("abc-1.jpg");
        item.getImages().add("abc-1.jpg");
        item.getImages().add("abc-3.jpg");
        item.getImages().add("abc-4.jpg");
        this.persist(item);
        this.commitTransaction().closeEntityManager();
        int index = 2;

        // Add an image to 3rd position
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        savedItem.getImages().add(index, "abc-2.jpg");
        this.commitTransaction().closeEntityManager();
        // Verify

        Item loadedItem = this.find(Item.class, savedItem.getId());
        assertEquals(5, loadedItem.getImages().size());
        assertEquals("abc-2.jpg", loadedItem.getImages().get(index));

        // Clean data
        this.cleanData(Item.class);
    }

    @Test
    public void testRemoveMiddleImages() {
        this.beginTransaction();
        Item item = new Item("List Item Test Remove");
        item.getImages().add("abc-1.jpg");
        item.getImages().add("abc-2.jpg");
        item.getImages().add("abc-3.jpg");
        item.getImages().add("abc-4.jpg");
        item.getImages().add("abc-5.jpg");
        this.persist(item);
        this.commitTransaction().closeEntityManager();
        int index = 2;
        Map<String, Integer> imageMap = new HashMap<>();

        // Remove an image to 3rd position
        this.beginTransaction();
        Item savedItem = this.find(Item.class, item.getId());
        savedItem.getImages().remove(index);
        for (int i = 0; i < savedItem.getImages().size(); i++) {
            imageMap.put(savedItem.getImages().get(i), i);
        }
        this.commitTransaction().closeEntityManager();

        // Verify
        Item loadedItem = this.find(Item.class, savedItem.getId());
        assertEquals(4, loadedItem.getImages().size());
        assertFalse(loadedItem.getImages().contains("abc-3.jpg"));
        for (int i = 0; i < loadedItem.getImages().size(); i++) {
            assertEquals(i, imageMap.get(loadedItem.getImages().get(i)).intValue());
        }

        // Clean data
        this.cleanData(Item.class);
    }

    @Test
    public void testImagesOrder() {
        Item item = new Item("Test Image Order");
        item.getImages().add("abc-1.jpg");
        item.getImages().add("abc-3.jpg");
        item.getImages().add("abc-2.jpg");
        item.getImages().add("abc-4.jpg");
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        logger.info("--- Saved images: {}", savedItem.getImages());
        List<String> savedImages = savedItem.getImages();
        Collections.sort(savedImages);
        logger.info("--- Sorted saved images: {}", savedItem.getImages());
        Map<String, Integer> expectedOrderMap = new HashMap<>();
        for (int i = 0; i < savedImages.size(); i++) {
            expectedOrderMap.put(savedImages.get(i), i);
        }
        this.beginTransaction().persist(savedItem).commitTransaction().closeEntityManager();

        Item loadedItem = this.find(Item.class, savedItem.getId());
        List<String> loadedImages = loadedItem.getImages();

        // Verify
        for (int i = 0; i < loadedImages.size(); i++) {
            assertEquals(expectedOrderMap.get(loadedImages.get(i)).intValue(), i);
        }
    }
}
