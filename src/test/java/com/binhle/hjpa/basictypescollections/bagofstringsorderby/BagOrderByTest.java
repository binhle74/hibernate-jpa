package com.binhle.hjpa.basictypescollections.bagofstringsorderby;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BagOrderByTest extends BaseTest {

    @Before
    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testBagImagesOrderBy() {
        Item item = new Item();
        item.setName("Item Test");
        for (int i = 5; i > 0; i--) {
            item.getImages().add(String.format("abc-%s.jpg", i));
        }
        Comparator<String> comp = (String::compareTo);
        String expected = item.getImages().stream().sorted(comp.reversed()).reduce("", BaseTest::concat);
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        String savedImages = savedItem.getImages().stream().reduce("", BaseTest::concat);
        assertEquals(expected, savedImages);
    }
}
