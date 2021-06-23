package com.binhle.hjpa.basictypescollections.sortedmapofstrings;

import static org.junit.Assert.assertEquals;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedMapOfStringTest extends BaseTest {

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
    public void testSortImages() {
        Item item = new Item();
        item.setName("Sorted Map Test");
        for (int j = 1; j < 5; j++) {
            item.getImages().put(String.format("abc-%s.jpg", j), String.format("ABC-%s", j));
        }
        String expected = item.getImages().values().stream().sorted(new ReverseStringComparator()).reduce("",
                BaseTest::concat);
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        String savedImages = savedItem.getImages().values().stream().reduce("", BaseTest::concat);
        assertEquals(expected, savedImages);
    }
}
