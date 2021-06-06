package com.binhle.hjpa.mappingcollections.bagofstrings;

import static org.junit.Assert.assertEquals;

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

        cleanData(Item.class);
    }

    @After
    @Override
    public void after() {
       cleanData(Item.class);
    }
}
