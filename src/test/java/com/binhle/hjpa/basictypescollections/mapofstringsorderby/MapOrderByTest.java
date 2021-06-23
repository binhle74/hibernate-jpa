package com.binhle.hjpa.basictypescollections.mapofstringsorderby;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import com.binhle.hjpa.BaseTest;

import org.junit.Test;

public class MapOrderByTest extends BaseTest {

    @Override
    public void before() {

    }

    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testMapImagesOrderBy() {
        Item item = new Item();
        item.setName("Test Item");
        for (int i = 5; i > 0; i--) {
            item.getImages().put(String.format("abc-%s.jpg", i), String.format("ABC-%s", i));
        }
        Comparator<String> comp = (String::compareTo);
        String expected = item.getImages().keySet().stream().sorted(comp.reversed()).reduce("", BaseTest::concat);
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        String savedImages = savedItem.getImages().keySet().stream().reduce("", BaseTest::concat);
        assertEquals(expected, savedImages);
    }
}