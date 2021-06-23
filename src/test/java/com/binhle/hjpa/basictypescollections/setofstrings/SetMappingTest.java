package com.binhle.hjpa.basictypescollections.setofstrings;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.BaseTest;

public class SetMappingTest extends BaseTest {

    @Before
    @Override
    public void before() {
        cleanData(Item.class);
        closeEntityManager();
    }

    @After
    @Override
    public void after() {
        closeEntityManager();
    }

    @Test
    public void testSetOfImages() {
        int numOfItems = 5;
        int numOfImages = 5;
        // Create new item
        beginTransaction();
        for (int i = 1; i <= numOfItems; i++) {
            Item item = new Item();
            item.setName("Item " + i);
            for (int j = 1; j <= numOfImages; j++) {
                item.getImages().add("foo-" + j + ".jpg");
            }
            persist(item);
            logger.info("--- Create item: {}", item);
        }
        commitTransaction();
        closeEntityManager();

        // Open to load item
        List<Item> items = findAll(Item.class);
        logger.info("--- Loaded items: {}", items);

        // Verify
        assertEquals(numOfItems, items.size());
        for (Item item : items) {
            assertEquals(numOfImages, item.getImages().size());
        }

        cleanData(Item.class);
    }

    /**
     * IMAGE table has composite primary key of both ITEM_ID and FILENAME columns
     */
    @Test
    public void testDuplicatedImages() {
        int numOfItems = 2;
        int numOfImages = 2;
        // Create new item
        beginTransaction();
        for (int i = 1; i <= numOfItems; i++) {
            Item item = new Item();
            item.setName("Item " + i);
            for (int j = 1; j <= numOfImages; j++) {
                item.getImages().add("foo-" + j + ".jpg");

                // Can not add duplicated images
                item.getImages().add("foo-" + j + ".jpg");
                item.getImages().add("foo-" + j + ".jpg");
                item.getImages().add("foo-" + j + ".jpg");
            }
            persist(item);
            logger.info("--- Create item: {}", item);
        }
        commitTransaction();
        closeEntityManager();

        // Open to load item
        List<Item> items = findAll(Item.class);
        logger.info("--- Loaded items: {}", items);

        // Verify
        assertEquals(numOfItems, items.size());
        for (Item item : items) {
            assertEquals(numOfImages, item.getImages().size());
        }

        cleanData(Item.class);
    }

    @Test
    public void testImageOrder() {
        beginTransaction();
        Item item = new Item();
        item.setName("Item Test Order 1");
        item.getImages().add("fbc-1.jpg");
        item.getImages().add("abc-1.jpg");
        item.getImages().add("ods-1.jpg");
        persist(item);
        commitTransaction().closeEntityManager();
        Item loadedItem = getEntityManager().find(Item.class, item.getId());
        String expectedImagesOrder = "abc-1.jpg, ods-1.jpg, fbc-1.jpg";

        // Verify
        String loadedImagesOrder = loadedItem.getImages().stream().reduce("", (s, i) -> s + (s == "" ? "" : ", ") + i);
        assertEquals(expectedImagesOrder, loadedImagesOrder);

        cleanData(Item.class);
    }
}
