package com.binhle.hjpa.basictypescollections.bagofstrings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.BaseTest;

public class BagMappingTest extends BaseTest {

    @Before
    @Override
    public void before() {
        cleanData(Item.class);
        closeEntityManager();
    }

    /**
     * Using additional primary key IMAGE_ID to allow the duplicate FILENAME for
     * each ITEM_ID
     */
    @Test
    public void testBagOfImages() {
        int numOfItems = 5;
        int numOfImages = 5;
        // Create new item
        beginTransaction();
        for (int i = 1; i <= numOfItems; i++) {
            Item item = new Item();
            item.setName("Item " + i);
            for (int j = 1; j <= numOfImages; j++) {
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
            assertEquals(numOfImages * 2, item.getImages().size());
        }
    }

    @Test
    public void testImagesOrder() {
        beginTransaction();
        Item item = new Item();
        item.setName("Item with Bag of images");
        item.getImages().add("fbc-1.jpg");
        item.getImages().add("abc-3.jpg");
        item.getImages().add("abc-1.jpg");
        item.getImages().add("ods-1.jpg");
        String expectedImages = item.getImages().stream().reduce("", (s, i) -> s + (s == "" ? "" : ", ") + i);

        this.persist(item).commitTransaction().closeEntityManager();
        Item loadedItem = getEntityManager().find(Item.class, item.getId());

        // Verify
        String loadedImages = loadedItem.getImages().stream().reduce("", (s, i) -> s + (s == "" ? "" : ", ") + i);
        assertEquals(expectedImages, loadedImages);
    }

    @Test
    public void testRemoveImages() {
        this.beginTransaction();
        Item item = new Item();
        item.setName("Bag Item Test 1");
        item.getImages().add("abc-1.jpg");
        item.getImages().add("abc-3.jpg");
        item.getImages().add("abc-2.jpg");
        item.getImages().add("abc-4.jpg");
        item.getImages().add("abc-5.jpg");
        this.persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        savedItem.getImages().remove("abc-2.jpg");
        this.beginTransaction().persist(savedItem).commitTransaction().closeEntityManager();

        // Verify
        assertNotNull(savedItem);
    }

    @After
    @Override
    public void after() {
        cleanData(Item.class);
    }
}
