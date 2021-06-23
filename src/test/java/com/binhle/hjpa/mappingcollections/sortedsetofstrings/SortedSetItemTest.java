package com.binhle.hjpa.mappingcollections.sortedsetofstrings;

import static org.junit.Assert.assertEquals;
import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedSetItemTest extends BaseTest {

    @Before
    @Override
    public void before() {
        this.cleanData(Item.class);
    }

    @After
    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testSortNaturalImages() {
        Item item = new Item();
        item.setName("Sort Natural Images");
        for (int i = 5; i > 0; i--) {
            item.getImages().add(String.format("abc-%s.jpg", i));
        }
        String expected = item.getImages().stream().sorted().reduce("", BaseTest::concat);
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();
        Item savedItem = this.find(Item.class, item.getId());
        String actual = savedItem.getImages().stream().reduce("", BaseTest::concat);
        assertEquals(expected, actual);
    }
}
